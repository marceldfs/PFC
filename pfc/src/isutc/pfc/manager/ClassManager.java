package isutc.pfc.manager;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.bean.Departamento;
import isutc.pfc.bean.Document;
import isutc.pfc.bean.Tema;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

public class ClassManager extends DbSpringManager
{
	private static Logger logger = Logger.getLogger(ClassManager.class);
	
	public void storeClass()
	{
//		 inserir valores
		String code="Teste";
		Object[] params = {code};
		jdbc.update(" INSERT INTO departamento(designacao)"+ "VALUES(?)", params);
		System.out.println("Inserted");
	}
	
	public void storeDoc(Document doc)
	{
		// inserir valores
		
		String title = doc.getTitle();
		String description = doc.getDescription();
		
		logger.info("Description: "+description);
		logger.info("Titolo: "+title);

		ByteArrayOutputStream compressedData = new ByteArrayOutputStream();
		try
		{
			GZIPOutputStream compressor = new GZIPOutputStream(compressedData);
            
            //os dados crus podem ser do text ou do stream
            byte[] rawData = doc.getText();
            if(rawData == null)  //procuramos o stream, devem ser dados grandes
            {
                int read = -1;
                while((read = doc.getTextStream().read()) != -1)
                {
                    compressor.write(read);
                }
                doc.getTextStream().close();
            }
            else
            {                
                compressor.write(rawData);
            }
 
			compressor.close();
		}
		catch(IOException convertX)
		{
			throw new RuntimeException(doc.getFormat() + "GZIP" + convertX);
		}
		
		
		Object [] params = {doc.getTitle(), compressedData.toByteArray(), doc.getDescription()};
    	
    	jdbc.update("INSERT INTO test_doc( title, ficheiro, description ) VALUES(?,?,?)", params);
	}
	
	public List<Tema> listClass()
	{
		ThemeMapper mapper = new ThemeMapper();
		return jdbc.query("SELECT * FROM TEMA ", mapper);
	}
	
	public List<Document> listPfc()
	{
		DocMapper maper = new DocMapper();
		return jdbc.query("SELECT id,description,title FROM test_doc ", maper);
	}
	
	private class ThemeMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			// preparar os dados basicos do anexo  data_inicio,data_fim,titulo,nota,description
			Tema tema = new Tema();
			tema.setData_inicio(rs.getDate("data_inicio"));
			tema.setData_fim(rs.getDate("data_fim"));			
			tema.setTitulo(rs.getString("titulo"));
			tema.setNota(rs.getInt("nota"));
			tema.setDescription(rs.getString("description"));
			return tema;
		}
	
	}
	
	private class DocMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			// preparar os dados basicos do anexo
			Document docItem = new Document();
			docItem.setId(rs.getInt("id"));
			docItem.setDescription(rs.getString("description"));
			docItem.setTitle(rs.getString("title"));
			return docItem;
		}
	
	}
	
	private class ClassMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			// preparar os dados basicos do anexo
			Departamento depItem = new Departamento();
			depItem.setId(rs.getInt("id"));
			depItem.setDesignacao(rs.getString("designacao"));
			return depItem;
		}
	
	}
}
