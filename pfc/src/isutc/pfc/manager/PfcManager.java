package isutc.pfc.manager;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.bean.Pfc;
import isutc.pfc.bean.Tema;
import isutc.pfc.security.User;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class PfcManager extends DbSpringManager
{
	private static Logger logger = Logger.getLogger(PfcManager.class);
	
	private TemaManager temaManager;
	
	public void setTemaManager(TemaManager temaManager) 
	{
		this.temaManager = temaManager;
	}

	public List<Pfc> listAllPfc()
	{
		PfcMapper mapper = new PfcMapper();
		return (List<Pfc>)jdbc.query("SELECT * FROM PFC ", mapper);
	}
	
	public boolean hasPFC(Tema tema)
	{
		PfcIdMapper mapper = new PfcIdMapper();
		int count = jdbc.query("SELECT COUNT(tema_id) FROM PFC WHERE tema_id = "+ tema.getTema_id(), mapper).size();
		return count > 0 ? true : false;
	}
	
	public boolean hasUserPFC(Pfc pfc)
	{
		PfcMapper mapper = new PfcMapper();
		int count = jdbc.query("SELECT COUNT(tema_id) FROM PFC WHERE tema_id = "+ pfc.getTema_id(), mapper).size();
		return count > 0 ? true : false;
	}
	
	@Transactional
	public Pfc submitPfc(User studant,Pfc pfc) throws SQLException
	{
		Tema tema = temaManager.studantTheme(studant.getStudentNumber());
		String titulo = tema.getTitulo();
		String supervisor = tema.getSupervisorName();
		int temaId = tema.getTema_id();
		int userId = studant.getStudentNumber();
		
		boolean aprovado = tema.getSituation().equalsIgnoreCase("APROVADO");
		
		ByteArrayOutputStream compressedData = new ByteArrayOutputStream();
		try
		{
			GZIPOutputStream compressor = new GZIPOutputStream(compressedData);
            
            //os dados crus podem ser do text ou do stream
            byte[] rawData = pfc.getText();
            if(rawData == null)  //procuramos o stream, devem ser dados grandes
            {
                int read = -1;
                while((read = pfc.getTextStream().read()) != -1)
                {
                    compressor.write(read);
                }
                pfc.getTextStream().close();
            }
            else
            {                
                compressor.write(rawData);
            }
 
			compressor.close();
		}
		catch(IOException convertX)
		{
			throw new RuntimeException(pfc.getFormat() + "GZIP" + convertX);
		}
		
		Object [] params = {studant.getFullName(),supervisor,titulo,pfc.getDescription(),compressedData.toByteArray(),temaId,userId};
		if(aprovado && verifyPfc(userId))
		{
			//TODO: Verificar as premissas
			jdbc.update("INSERT INTO PFC(data_inicio,data_fim,fullName,supervisor_name,titulo,description,document,tema_id,user_id) VALUES (110611,111111,?,?,?,?,?,?,?)", params); //Inserir o PFC
			List<Pfc> allPfc = jdbc.query("SELECT * FROM PFC WHERE pfc_id=(SELECT MAX(pfc_id) FROM PFC)",new PfcMapper());
			Pfc lasPfc = allPfc.get(0);
			
			return lasPfc;
			
		}else return null;
	}
	
	public boolean verifyPfc(int userId)
	{
		PfcMapper pfcMapper = new PfcMapper();
				
		List pfcs = jdbc.query("SELECT * FROM PFC WHERE user_id="+userId,pfcMapper);
		return pfcs.isEmpty();
	}
	
	public void deletePfc(Pfc pfc)
	{
		int pfcId = pfc.getPfc_id();
		jdbc.update("DELETE FROM PFC WHERE pfc_id = "+pfcId);
	}
	
	public Pfc updatePfc(Pfc pfc)
	{
		Tema tema = temaManager.studantTheme(pfc.getUser_id());
		
		int id = pfc.getTema_id();
		int pfcId = pfc.getPfc_id();
		String titulo = pfc.getTitulo();
		String description = pfc.getDescription();
		String supervisor = pfc.getSupervisorName();
		String oponente = pfc.getOponente();
		String presidente = pfc.getPresidente();
		int nota = pfc.getNota();
		String situation = pfc.getSituation();
		
		 double plagio = pfc.getPlagium();
		  String fase = pfc.getFase();
		
		Object [] params = {titulo,description,supervisor,nota,situation,id};
		Object [] params1 = {titulo};
		Object [] params2 = {description};
		Object [] params3 = {supervisor};
		Object [] params4 = {nota};
		Object [] params5 = {situation};
		Object [] params6 = {oponente};
		Object [] params7 = {presidente};
		Object [] params8 = {plagio};
		Object [] params9 = {fase};
		
		
		
		
		
		
		if(!isClosedPFC(pfc) && canUpdate(tema))
		{
			//jdbc.update("UPDATE PFC SET titulo = ? WHERE pfc_id = "+pfcId,params1);
			jdbc.update("UPDATE PFC SET description = ? WHERE pfc_id = "+pfcId,params2);
			jdbc.update("UPDATE PFC SET supervisor_name = ? WHERE pfc_id = "+pfcId,params3);
			jdbc.update("UPDATE PFC SET nota = ? WHERE pfc_id = "+pfcId,params4);
			jdbc.update("UPDATE PFC SET situation = ? WHERE pfc_id = "+pfcId,params5);
			
			jdbc.update("UPDATE PFC SET oponente = ? WHERE pfc_id = "+pfcId,params6);
			jdbc.update("UPDATE PFC SET presidente = ? WHERE pfc_id = "+pfcId,params7);
			jdbc.update("UPDATE PFC SET plagium = ? WHERE pfc_id = "+pfcId,params8);
			jdbc.update("UPDATE PFC SET fase = ? WHERE pfc_id = "+pfcId,params9);
			
			List<Pfc> allPfc = jdbc.query("SELECT * FROM PFC WHERE pfc_id= "+pfc.getPfc_id(),new PfcMapper());
			Pfc updatedPfc = allPfc.get(0);
			
			return updatedPfc;
		}
		return null;
	}
	
	private boolean isClosedPFC(Pfc pfc)
	{		
		return pfc.getFase().trim().equalsIgnoreCase("Fechado");
	}
	
	private boolean isValidJuri(Pfc pfc)
	{
		return pfc.getOponente().equalsIgnoreCase(pfc.getSupervisorName()) ||
			   pfc.getOponente().equalsIgnoreCase(pfc.getPresidente())		||
			   pfc.getOponente().equalsIgnoreCase(pfc.getSupervisorName()) ? false : true;
	}
	
	private boolean canUpdate(Tema tema)
	{
		return tema.getSituation().equalsIgnoreCase("APROVADO") ? false : true;
	}
	
	
	//Mapeadores	
	
	private class PfcMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			 //1. descomprimir os dados binarios 
            ByteArrayOutputStream realData = new ByteArrayOutputStream(); 
            ByteArrayInputStream compressedData = new ByteArrayInputStream(rs.getBytes("document"));
			Pfc pfc = new Pfc();
			
			try
	        {
				InputStream decompressor = new BufferedInputStream(new GZIPInputStream(compressedData));
				
				int read = -1;
				while((read = decompressor.read()) != -1)
				{
					realData.write(read);
				}
			
				pfc.setPfc_id(rs.getInt("pfc_id"));
				pfc.setTema_id(rs.getInt("tema_id"));
				pfc.setData_inicio(rs.getDate("data_inicio"));
				pfc.setData_fim(rs.getDate("data_fim"));	
				pfc.setNota(rs.getInt("nota"));
				pfc.setSituation(rs.getString("situation"));
				pfc.setSupervisorName(rs.getString("supervisor_name"));			
				pfc.setTitulo(rs.getString("titulo"));
				pfc.setUser_id(rs.getInt("user_id"));
				pfc.setFullName(rs.getString("fullName"));
				pfc.setDescription(rs.getString("description"));
				pfc.setText(realData.toByteArray());	//Document
				pfc.setFase(rs.getString("fase"));
				pfc.setPlagium(rs.getDouble("plagium"));
				pfc.setPresidente(rs.getString("presidente"));
				pfc.setOponente(rs.getString("oponente"));
			
				return pfc;
			
	        }catch(IOException convertX)
			{
				throw new RuntimeException("GZIP " + rs.getString("format_code"), convertX);
			}   
		}
	
	}
	
	private class PfcIdMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			
			Pfc pfc = new Pfc();
			
				pfc.setTema_id(rs.getInt("tema_id"));
				
			
				return pfc;
			
	          
		}
	
	}
}
