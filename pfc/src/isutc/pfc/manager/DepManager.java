package isutc.pfc.manager;

import org.springframework.jdbc.core.JdbcTemplate;

public class DepManager 
{
	private JdbcTemplate jdbc;

	public void setJdbc(JdbcTemplate jdbc)
	{
		this.jdbc = jdbc;
	}
	
public void  storeDepartment()//Departamento dep)
	{
		String a = "Test";
		Object [] params = {a};//dep.getDesignacao()};		
		jdbc.update("INSERT INTO departamento values(?)", params);
	}

}
