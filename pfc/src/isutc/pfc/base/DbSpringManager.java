package isutc.pfc.base;


import isutc.pfc.security.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.context.SecurityContextHolder;
//import org.springframework.security.userdetails.User;

public abstract class DbSpringManager 
{
	protected JdbcTemplate jdbc;
	protected LdapTemplate ldap;
	

	public void setJdbc(JdbcTemplate jdbc) 
	{
		this.jdbc = jdbc;
	}
	
	public void setLdap(LdapTemplate ldap)
	{
		this.ldap = ldap;
	}
	
	public User getCurrentUserLogin()
    {
    	return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();    	
    }	
}
