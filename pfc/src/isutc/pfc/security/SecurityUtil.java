package isutc.pfc.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.ldap.LdapUserDetails;
import org.springframework.security.userdetails.ldap.LdapUserDetailsImpl;

import isutc.pfc.base.DbSpringManager;

public class SecurityUtil  extends DbSpringManager
{
	private String _groupPrefix = "ROLE_";
	private static Logger logger = Logger.getLogger(SecurityUtil.class);
	private String secRole;
	private String gpfcRole;
	private String studentRole;
	
	

	public String getSecRole() {
		return secRole;
	}

	public void setSecRole(String secRole) {
		this.secRole = secRole;
	}

	public String getGpfcRole() {
		return gpfcRole;
	}

	public void setGpfcRole(String gpfcRole) {
		this.gpfcRole = gpfcRole;
	}

	public String getStudentRole() {
		return studentRole;
	}

	public void setStudentRole(String studentRole) {
		this.studentRole = studentRole;
	}

	public org.springframework.security.userdetails.User getUserLoggedIn() throws NamingException
	{
		try 
		{
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
			return user;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private String attrAsString(Attributes attrs, String attrName) throws NamingException
	{
	    Attribute attr = attrs.get(attrName);
	    return attr == null ? null : (String)attr.get();       
	}

	public boolean hasAuthorithy(User user, String authorithy)
	{
		for(GrantedAuthority auth: user.getAuthorities())
		{
			if( auth.getAuthority().equals(authorithy) )
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Adicionar Roles aos Professores para que possam ser supervisores e oponentes
	 * 
	 */
	public GrantedAuthority [] setTeacherAthorities(GrantedAuthority role)
	{
		
		GrantedAuthority [] newAuthorities = {  new GrantedAuthorityImpl("ROLE_SUPERVISOR"),
												new GrantedAuthorityImpl("ROLE_OPONENTE"),
												new GrantedAuthorityImpl("ROLE_PRESIDENTE")};
		logger.info("Novos Roles do usuário: "+newAuthorities.toString());
		return newAuthorities;
	}
	
	
	/*/////////////////////////////////////////////////////////////
	/**
     * Should be, ROLE_*. Ex. ROLE_ESTUDANTE 
     *
    public List<GrantedAuthority> getGarantedAuthoritiesForGroup(String group)
    {
		String code = getCodeForGroup(group);  
		if(code == null) 
		{
			return new ArrayList<GrantedAuthority>();
		}
		
		Object[] params = new Object[]{code};	
		return jdbc.query("SELECT * FROM user_role WHERE role_id = ? ", params, new GrantedAuthorityMapper());
	}
    
	
	private String getCodeForGroup(String group)
	{
		//0. validar contra params burros
		if(group.startsWith(_groupPrefix))
		{
			Object[] params = new Object[]{StringUtils.substringAfter(group, _groupPrefix)};
			try 
			{
				return (String)jdbc.queryForObject("SELECT group_code FROM sec_group WHERE group_name = ? ", params, String.class);
			} 
			catch (IncorrectResultSizeDataAccessException noGroupMappingX)
			{
				return null;
			}
		}
		else { return null; } 
	}
	*/
	
	/**
	 * Adds GrantedAuthority, after prefixing with _funcPrefix
	 *
	private class GrantedAuthorityMapper implements RowMapper
	{
				
		public Object mapRow(ResultSet data, int row) throws SQLException
		{
			String perm = data.getString("role_id");
			return new GrantedAuthorityImpl("REL_" + data.getString("rel_rule_id") + "_" + perm);//TODO: dynamci REL_
    	}
	}
	*/
}