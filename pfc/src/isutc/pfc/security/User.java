package isutc.pfc.security;

import isutc.pfc.framawork.NotConfiguredException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

public class User extends org.springframework.security.userdetails.User implements UserDetails
{
	private int studentNumber;
	private String classId;
	private String email;
	private String password;
	private String username;
	private String fullName;
	private GrantedAuthority[] authorities;
	
	//Para variáveis de UserDetais
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	public final static String ROLE_PREFIX = "ROLE_"; 
	
	//caso extenda do User do spring TODO: Ver as vantagens reais disso
	public User(String userName, GrantedAuthority[] authorities)
	{
		super(userName,"",true,authorities);
		this.username = userName;
		this.authorities = authorities;
	}
	
	
	public User(String id, String fullName, int studentNumber, String classId, String email, GrantedAuthority[] authorities)
	{
		   //1. call superconstructor
		   super(id, "", true, true, true, true, authorities);

		   //2. basic details
		   this.fullName = fullName;  //nome completo pode ser null
		   this.studentNumber = studentNumber;
		   this.classId = classId;
		   this.email = email;   		
		   this.authorities = authorities;
		   
		   //3. role		   
		   if(authorities.length < 1) { throw new NotConfiguredException("Cannot construct a User with no authorities: ", id); }
	}

	
	public GrantedAuthority[] getAuthorities() 
	{
		return authorities;
	}
	public void setAuthorities(GrantedAuthority[] authorities) 
	{
		this.authorities = authorities;
	}
	public String getClassId() 
	{
		return classId;
	}
	public void setClassId(String classId) 
	{
		this.classId = classId;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public int getStudentNumber() 
	{
		return studentNumber;
	}
	public void setStudentNumber(int studentNumber) 
	{
		this.studentNumber = studentNumber;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	//Metodos do UserDatals
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String toString()
	{
		return "User "+fullName+" com o ID "+studentNumber;
	}
	
	public boolean hasRole(String role) 
	{
		return ArrayUtils.contains(getAuthorities(), new GrantedAuthorityImpl(ROLE_PREFIX + role));
	}
	
	public List<String> getGroups()
	{
		List<String> groups = new ArrayList<String>();
		
		for(GrantedAuthority ga : getAuthorities())
		{
			if (ga.getAuthority().startsWith(ROLE_PREFIX)) groups.add(ga.getAuthority());
		}
		
		return groups;
	}
}