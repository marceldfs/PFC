package isutc.pfc.manager;

import isutc.pfc.base.DbSpringManager;
import isutc.pfc.bean.Studant;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;

public class StudantManager extends DbSpringManager
{
	
	
	public List<Studant> listStudants()
	{
		System.out.println("TEstinManager");
		return null;		
	}
	
	//Estudantes de uma turma
	public List<Studant> listTurma( String turma)
	{
		StudantMapper mapper = new StudantMapper();
		List<Studant> studants= (List<Studant>) ldap.search("ou=Users", "(departmentNumber=" + turma + ")", mapper);
		return studants;
	}
	
	private class StudantMapper  implements AttributesMapper
	{

		public Object mapFromAttributes(Attributes attributes) throws NamingException
		{
			String username = (String) attributes.get("cn").get();
			String fullName = (String)attributes.get("displayName").get();
			int studentNumber = Integer.parseInt((String)attributes.get("uidNumber").get());
			String turma = (String)attributes.get("departmentNumber").get();
			String email = (String)attributes.get("mail").get();	// preparar os dados basicos do anexo
			
			Studant studant = new Studant(username,new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_ESTUDANTE")});
			
			studant.setEmail(email);
			studant.setFullName(fullName);
			studant.setStudentNumber(studentNumber);
			studant.setClassId(turma);
			studant.setCurso(turma);
			
			return studant;
			
		}
		
	}
}
