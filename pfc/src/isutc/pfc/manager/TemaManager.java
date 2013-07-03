package isutc.pfc.manager;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.bean.Pfc;
import isutc.pfc.bean.Tema;
import isutc.pfc.security.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

public class TemaManager extends DbSpringManager
{
	private static Logger logger = Logger.getLogger(TemaManager.class);
	
	public List<Tema> listAllThemes()
	{
		ThemeMapper mapper = new ThemeMapper();
		return (List<Tema>)jdbc.query("SELECT * FROM TEMA ", mapper);
	}
	
	public List listThemes(String curso)
	{		
		ThemeMapper mapper = new ThemeMapper();
		Object[] params = { curso };
		return jdbc.query("SELECT * FROM TEMA WHERE tema_id IN (SELECT tema_id FROM tema_curso WHERE curso_id= ? )", params,mapper);
	}
	
	public Tema studantTheme(int id)
	{
		ThemeMapper mapper = new ThemeMapper();
		List<Tema> temas = jdbc.query("SELECT * FROM TEMA WHERE user_id="+id, mapper);
		return temas.get(0);
	}
		
	public List listThemeByCriteria(String criteria, String value)
	{		
		ThemeMapper mapper = new ThemeMapper();
		String sql = "";
		
		if(criteria.equals("title"))
		{
			sql = "SELECT * FROM TEMA WHERE titulo LIKE '%"+value+"%'";
		}
		else if(criteria.equals("description"))
		{
			sql = "SELECT * FROM TEMA WHERE description LIKE "+"'%"+value+"%'";
		}
		else if(criteria.equals("situation")) //TODO Acrescentar este campo na DB para saber em que ponto está o tema(Em Analise, devolvido,Reprovado...)
		{
			 sql = "SELECT * FROM TEMA WHERE situation LIKE "+"'%"+value+"%'";
		}
		else if(criteria.equals("supervisor"))
		{
			sql = "SELECT * FROM TEMA WHERE supervisorName LIKE "+"'%"+value+"%'";
		}
		return jdbc.query(sql,mapper);
	}
	
	@Transactional
	public Tema submitTheme(User studant,String titulo, String description, String supervidor) throws SQLException
	{
		if(verifyTheme(studant.getStudentNumber()))
		{
			//Outras variaveis relevantes para actualizar as relações do tema
			String turma_id = studant.getClassId();
			int estudante_nr = studant.getStudentNumber();
			String fullName = studant.getFullName();
			String curso_id = getCursoId(turma_id);
			LocalDate nowDate = new DateTime().toLocalDate();
			
			String now = nowDate.toString();
			
			logger.info("Data: "+nowDate);
			logger.info("Curso: "+ curso_id);
			
			//TODO: Verificar se o tema Existe na DB  e se o aluno já tem um tema: Já está OK
			
			@SuppressWarnings("unused")
			ThemeMapper mapper = new ThemeMapper();
			ThemeIdMapper temaIdMapper = new ThemeIdMapper();
			Object [] params = {titulo,description,estudante_nr,fullName,supervidor};				
			
			String insetTheme = "INSERT INTO TEMA(data_inicio,data_fim,titulo,description,user_id,fullName,supervisorName) VALUES (110611,111111,?,?,?,?,?)"; 
			//TODO: As datas deverao ser null na dB(Não introduzir ao submeter), sendo os parametros:
			        
			//String allThemes = "SELECT * FROM TEMA"; //commented at 30/07/2012
			//TODO:Verificar se existe se o estudante já submeteu algum tema ou se o tema já existe? 
			jdbc.update(insetTheme, params); //Inserir o tema
			//jdbc.execute("ALTER TABLE TEMA ORDER BY tema_id");
			
			//String allThemes = "SELECT * FROM TEMA"; //ADD(Moved) at 30/07/2012
			
			//pegar o id do tema criado
			//List<Tema> temaIds = jdbc.query(allThemes, temaIdMapper);
			//Tema lastTheme = temaIds.get((temaIds.size()-1));
			
			
			
			List<Tema> allThemes = jdbc.query("SELECT * FROM TEMA WHERE tema_id=(SELECT MAX(tema_id) FROM TEMA)", mapper); //30/07/2012
			Tema lastTheme = allThemes.get(0);
			
			int themeId = lastTheme.getTema_id(); //id do tema criado
					
			Object [] paramStudant = {turma_id,estudante_nr,curso_id,themeId,now};
			Object [] paramCourse = {themeId,curso_id};
			
			String tema_studant = "INSERT INTO tema_studant(turma_id,estudante_nr,curso_id,tema_id,data_submit) VALUES(?,?,?,?,?)";//data_submit tem que ser now
			String tema_curso = "INSERT INTO tema_curso(tema_id,curso_id) VALUES(?,?)";
			
			jdbc.update(tema_studant, paramStudant); //Associar o tema ao estudante
			jdbc.update(tema_curso, paramCourse); //Associar o tema ao curso
					
			return lastTheme;
		}
		else return null;
	}
	
	public void deleteTheme(Tema tema)
	{
		int themeId = tema.getTema_id();
				System.out.println("Entrou no delete");
		jdbc.update("DELETE FROM tema_studant WHERE tema_id = "+themeId);
		jdbc.update("DELETE FROM tema_curso WHERE tema_id = "+themeId);
		jdbc.update("DELETE FROM TEMA WHERE tema_id = "+themeId+ " ORDER BY tema_id");
	}
	
	public Tema updateTheme(Tema tema)
	{
		int id = tema.getTema_id();
		String titulo = tema.getTitulo();
		String description = tema.getDescription();
		String supervisor = tema.getSupervisorName();
		int nota = tema.getNota();
		String situation = tema.getSituation();
		Object [] params = {titulo,description,supervisor,nota,situation,id};
		Object [] params1 = {titulo};
		Object [] params2 = {description};
		Object [] params3 = {supervisor};
		Object [] params4 = {nota};
		Object [] params5 = {situation};
		Object [] params6 = {titulo};
		
		/*
		 * Náo esta a funcionar TODO:Averiguar o motivo
		 * 
		 * jdbc.update("UPDATE TEMA SET titulo = ?, SET description = ? ," +
				" SET supervisorName = ?, SET nota = ?, SET situation = ? " +
				"WHERE tema_id = ?",params);
		*/
		
		jdbc.update("UPDATE TEMA SET titulo = ? WHERE tema_id = "+id,params1);
		jdbc.update("UPDATE TEMA SET description = ? WHERE tema_id = "+id,params2);
		jdbc.update("UPDATE TEMA SET supervisorName = ? WHERE tema_id = "+id,params3);
		jdbc.update("UPDATE TEMA SET nota = ? WHERE tema_id = "+id,params4);
		jdbc.update("UPDATE TEMA SET situation = ? WHERE tema_id = "+id,params5);
		
		jdbc.update("UPDATE PFC SET titulo = ? WHERE tema_id = "+id,params6);
		return tema;
	}
	
	private String getCursoId(String turma_id) 
	{
		// TODO Auto-generated method stub
		String  curso = null;
		if(turma_id.charAt(0) == 'I')
		{
			curso = "LEIT";
		}else if(turma_id.charAt(0) == 'C')
		{
			curso = "LECT";
		}else if(turma_id.charAt(0) == 'M')
		{
			curso = "LEMT";
		}else if(turma_id.charAt(0) == 'G')
		{
			curso = "LGF";
		}
		else if(turma_id.charAt(0) == 'A')
		{
			curso = "LEIT";
		}
		return curso;
	}

	public boolean verifyTheme(int userId)
	{
		ThemeMapper temaMapper = new ThemeMapper();
				
		List temas = jdbc.query("SELECT * FROM TEMA WHERE user_id="+userId,temaMapper);
		return temas.isEmpty();
	}
	
	public void supervisorTheme()
	{
		
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
			tema.setTema_id(rs.getInt("tema_id"));//Apagar
			tema.setSupervisorName(rs.getString("supervisorName"));
			tema.setSituation(rs.getString("situation"));
			tema.setUser_id(rs.getInt("user_id"));
			tema.setFullName(rs.getString("fullName"));
			return tema;
		}
	
	}
	
	private class ThemeIdMapper implements RowMapper 
	{
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			// preparar os dados basicos do anexo  data_inicio,data_fim,titulo,nota,description
			Tema tema = new Tema();
			tema.setTema_id(rs.getInt("tema_id"));
			tema.setTitulo(rs.getString("titulo"));
			return tema;
		}
	
	}
	
	//Testando API
	void tests()
	{
		
		
	}
}