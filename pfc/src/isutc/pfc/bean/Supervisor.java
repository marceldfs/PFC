package isutc.pfc.bean;

import org.springframework.security.GrantedAuthority;

import isutc.pfc.security.User;

public class Supervisor extends User
{
	private String telefone;
	private String Instituition;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Supervisor(String userName, GrantedAuthority[] authorities) 
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
