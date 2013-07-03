package isutc.pfc.manager;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.bean.Document;
import isutc.pfc.bean.Supervisor;
import isutc.pfc.security.SecurityUtil;
import isutc.pfc.security.User;
/*
 * Classe responsável pela gestão dos utilizadores do sistema
 * 
 */
public class UserManager extends DbSpringManager
{	
	private static Logger logger = Logger.getLogger(UserManager.class);
	
	public User loadUserByUsername(String username) throws UsernameNotFoundException,DataAccessException
	{
		List<User> users = (List<User>) ldap.search("ou=Users", "(uid=" + username + ")", new UserMapperLdap());
		if(users.size() == 0){ return null; }
		
		User ldapUser = users.get(0);
		System.out.println("User: "+ldapUser.getFullName()+" método loadUserByUsername");
		
		return ldapUser;
	}
	
	//Utilizadores de uma turma
	public List<User> getUsersFromClass(String selectedClass) throws SecurityException
	{   
		List<User> users= (List<User>) ldap.search("ou=Users", "(departmentNumber=" + selectedClass + ")", new UserMapperLdap());	
		return users;
	}
	
	public List<User> getFinalistasDetails()
	{
		List<String> usernames = getFialistas();
		List<User> users = new ArrayList<User>();
		for(String username : usernames)
		{
			if(username != null || username.trim().equals(""))
			{
				users.add(loadUserByUsername(username));
				logger.info("Finalista: " +loadUserByUsername(username).getFullName()+" Turma: "+ loadUserByUsername(username).getClassId());
			}
		}		
		return users;
	}
	
	//	Turmas Finalistas
	public List<String> getFialistas()
	{		
		List<String> members = new ArrayList<String>();
		
		List<String> lGF = ldap.search("ou=LGF,ou=Groups", "(cn=4 Ano)", new MemberRoleMapper(members));
		List<String> lEIT  = ldap.search("ou=LEIT,ou=Groups", "(cn=5 Ano)", new MemberRoleMapper(members));
		List<String> lEMT = ldap.search("ou=LEMT,ou=Groups", "(cn=5 Ano)", new MemberRoleMapper(members));
		List<String> lECT = ldap.search("ou=LECT,ou=Groups", "(cn=5 Ano)", new MemberRoleMapper(members));	
		
		return members;
	}
	
	public List<User> getDocentsDetails()
	{
		List<String> usernames = getDocentes();
		
		List<User> docentes  = new ArrayList<User>();
		for(String username : usernames)
		{
			if(username != null || username.trim().equals(""))
			{
				docentes.add(loadUserByUsername(username));
				logger.info("Docente: " +loadUserByUsername(username).getFullName());
			}
		}
		return docentes;
	}
	
	
	public List<String> getDocentes()
	{
		List<String> members = new ArrayList<String>();
		List<String> docente = ldap.search("ou=DPG,ou=Groups", "(cn=Docentes)", new MemberRoleMapper(members));	
		return members;
	}
	
	//	vai buscar um utilizador no LDAP tenha um certo id
	public User getUserWithId(int id) 
	{	
		//Buscar Utilizador no LDAP
		User user=(User)ldap.search("ou=Users", "(uidNumber=" + id + ")", new UserMapperLdap()).get(0);
		return user; 
	}
	
	
	
	//Mappeadores
	private class DocenteMapper implements AttributesMapper
	{
		public Object mapFromAttributes(Attributes attributes) throws NamingException
		{
			String username = (String) attributes.get("memberUid").get();			
			GrantedAuthority [] authorities = (GrantedAuthority[]) new GrantedAuthority[]{ new GrantedAuthorityImpl((String) attributes.get("cn").get())};
			
			User user;
			
			user = (User) loadUserByUsername(username);	        
	        return user;
		}		
	}
	
	
	
	private class UserMapperLdap implements AttributesMapper
	{

		public Object mapFromAttributes(Attributes attributes) throws NamingException
		{
			String username = (String) attributes.get("cn").get();
			String fullName = (String)attributes.get("displayName").get();
			int studentNumber = Integer.parseInt((String)attributes.get("uidNumber").get());
			String turma = attrAsString(attributes, "departmentNumber");
			String email = attrAsString(attributes, "mail");
			//TODO: Pegar os roles do user
			GrantedAuthority [] authorities = null; //(GrantedAuthority[]) attributes.get("authorities");
			
			User user;
			
			if(authorities != null)
			{
				user = new User(username,fullName,studentNumber,turma,email,authorities);//new User(id, fullName,studentNumber, classId, email);
			}
			else user = new User(username,fullName,studentNumber,turma,email,new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_ESTUDANTE")});
			
	        
	        return user;
		}

		private String attrAsString(Attributes attributes, String attrName) throws NamingException 
		{
			Attribute attr = attributes.get(attrName);
			return attr == null ? null : (String)attr.get();
		}
		
		
	}
	
	 /**
	    * Junta todos os membros de todos os grupos apanhados pelo query
	    */
	   class MemberRoleMapper implements AttributesMapper
	   {
	       private List<String> _all;  //onde guardamos nossos dados

	       MemberRoleMapper(List<String> all) { _all = all; }

	       public Object mapFromAttributes(Attributes attrs) throws NamingException
	       {
	           //simplesmente guardar o departmentNumber nao queremos mais nada
	           NamingEnumeration allNames = attrs.get("memberUid").getAll();
	           while(allNames.hasMore())
	           { 
	               Object next = allNames.next();
	               _all.add(next.toString());
	           }

	           return "DO NOT USE THIS VALUE";  //temos que devolver algo embora o importante era botar no _all
	       }
	   }
	
	
}
