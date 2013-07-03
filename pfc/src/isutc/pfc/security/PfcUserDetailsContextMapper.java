package isutc.pfc.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.apache.log4j.Logger;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.ldap.UserDetailsContextMapper;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.manager.TemaManager;

public class PfcUserDetailsContextMapper extends DbSpringManager implements UserDetailsContextMapper
{
	private SecurityUtil securityUtil;
	private static Logger logger = Logger.getLogger(PfcUserDetailsContextMapper.class);
	
	public void setSecurityUtil(SecurityUtil secUtil)
	{
		this.securityUtil = secUtil;
	}

	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, GrantedAuthority[] auths) 
	{
		// TODO Auto-generated method stub
		try 
		{
			//1. preparar detalhes basicos
			Attributes attrs = ctx.getAttributes();

			String user_name = (String)attrs.get("cn").get();    //Está a pegar userame e áo o ID      
			String fullName = (String)attrs.get("displayName").get();
			int userId = Integer.parseInt((String)attrs.get("uidNumber").get());  //Está a pegar o Id e não o nr de estudante
			String classId = attrAsString(attrs, "departmentNumber");
			String email = attrAsString(attrs, "mail");
			
			//2. carregar cabaz de permissoes baseado nos grupos
			List<GrantedAuthority> perms = new ArrayList(Arrays.asList(auths));  //temos que embrulhar pois Arrays.asList devolve uma lista fixa
			
			//2.1 para cada grupo TODO: duplicacao? usar SET
			for(GrantedAuthority auth : auths)
			{
				//A ideia aqui é dar o role dos PFC's a de acordo com o role do ldap o securityutil
				System.out.println("Role: "+auth.getAuthority());
				
				if(auth.getAuthority().equalsIgnoreCase("ROLE_PROFESSORES"))
				{
					/*GrantedAuthority[]authoritiesLdap = {new GrantedAuthorityImpl("ROLE_SUPERVISOR"),
														 new GrantedAuthorityImpl("ROLE_OPONENTE"),
														 new GrantedAuthorityImpl("ROLE_PRESIDENTE")};
					perms.addAll(Arrays.asList(authoritiesLdap));*/
					perms.addAll(Arrays.asList(securityUtil.setTeacherAthorities(auth)));
				}
			}
			
			//Teste, apagar
			for(GrantedAuthority auth : perms)
			{
				//A ideia aqui é dar o role dos PFC's a de acordo com o role do ldap o securityutil
				System.out.println("Role 2: "+auth.getAuthority());
			}
			
			
			//3. criar um User incluindo detalhes, grupos e cabaz de permissoes
		//	return new User(id,fullName,studentNumber,classId,email);//perms.toArray(new GrantedAuthority[0]),email);
			
//			  3. criar um User incluindo detalhes, grupos e cabaz de permissoes
			 if (classId == null) classId = "";
			//  return new User(user_name,fullName,userId,classId,email,auths);//new User(id, fullName,studentNumber, classId, email, perms.toArray(new GrantedAuthority[0]));
			  
			 return new User(user_name,fullName,userId,classId,email,perms.toArray(new GrantedAuthority[0]));
			  
			  
		} 
		catch (NamingException ldapX) 
		{
			return null;
		}
		
		
	}

	public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) 
	{
		// TODO Auto-generated method stub
		
	}
	
	private String attrAsString(Attributes attrs, String attrName) throws NamingException
	{
		Attribute attr = attrs.get(attrName);
		return attr == null ? null : (String)attr.get();       
	}
	
}