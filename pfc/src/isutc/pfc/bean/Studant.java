package isutc.pfc.bean;

import org.springframework.security.GrantedAuthority;

import isutc.pfc.security.User;


public class Studant extends User
{
	
	public Studant(String userName, GrantedAuthority[] authorities)
	{
		super(userName, authorities);
		// TODO Auto-generated constructor stub
	}
	private String curso;
	
	
	
	public String getCurso() 
	{
		return curso;
	}
	
	public void setCurso(String turma)
	{
		String  curso = null;
		if(turma.charAt(0) == 'I')
		{
			curso = "LEIT";
		}else if(turma.charAt(0) == 'C')
		{
			curso = "LECT";
		}else if(turma.charAt(0) == 'M')
		{
			curso = "LEMT";
		}else if(turma.charAt(0) == 'G')
		{
			curso = "LGF";
		}
		else if(turma.charAt(0) == 'A')
		{
			curso = "LEIT";
		}
		
		this.curso = curso;
	}	
}
