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
import org.springframework.security.userdetails.ldap.LdapUserDetailsImpl;

import isutc.pfc.base.DbSpringManager;

public class SecurityUtil  extends DbSpringManager
{
	private String _groupPrefix = "ROLE_";
	private static Logger logger = Logger.getLogger(SecurityUtil.class);
	
	public org.springframework.security.userdetails.User getUserLoggedIn() throws NamingException
	{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof UserDetails)
		{
			LdapUserDetailsImpl realUser=(LdapUserDetailsImpl)obj;
			BasicAttributes attrs=(BasicAttributes)realUser.getAttributes();
			
			String user_name = (String)attrs.get("cn").get();          
	        String fullName = (String)attrs.get("displayName").get();
	        int user_id = Integer.parseInt((String)attrs.get("uidNumber").get());  //TODO
	        String classId = attrAsString(attrs, "departmentNumber");
	        String email = attrAsString(attrs, "mail");
	        GrantedAuthority [] authorities = realUser.getAuthorities();
	          
	        User user = new User(user_name, fullName,user_id, classId, email,authorities);  
	          
	       /* GrantedAuthority[]authoritiesLdap={new GrantedAuthorityImpl(getRole(user_id))};
	  		//user.setAuthorities(authoritiesLdap);
	        if (classId == null) classId = ""; //TODO: evitar isto, para o caso de isupac3.professor vem null, dificultado os testes
	        User user = new User(user_name, fullName,user_id, classId, email,authoritiesLdap);
			*/
			return user;
		}
		return null;
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