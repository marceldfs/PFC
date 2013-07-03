package isutc.pfc.bean;

import isutc.pfc.security.User;

import org.springframework.security.GrantedAuthority;

public class Presidente  extends User
{
	private String telefone;
	private String Instituition;
	
	public Presidente(String userName, GrantedAuthority[] authorities) 
	{
		super(userName, authorities);
		// TODO Auto-generated constructor stub
	}

	public String getInstituition() {
		return Instituition;
	}

	public void setInstituition(String instituition) {
		Instituition = instituition;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
