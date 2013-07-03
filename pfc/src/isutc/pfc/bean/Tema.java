package isutc.pfc.bean;

import java.sql.Date;

public class Tema 
{
	protected int tema_id;
	protected Date data_inicio;
	protected Date data_fim;
	protected String titulo;
	protected int nota;
	protected String description;
	protected int user_id;
	protected String fullName;
	protected String situation;
	protected String supervisorName;
	protected int supervisorId;
	
	
	public Tema( String titulo, String description)
	{
		this.titulo = titulo;
		this.description = description;
	}
	
	public Tema()
	{
		
	}
	
	public Date getData_fim()
	{
		return data_fim;
	}
	public void setData_fim(Date data_fim)
	{
		this.data_fim = data_fim;
	}
	public Date getData_inicio()
	{
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio)
	{
		this.data_inicio = data_inicio;
	}
	public int getTema_id()
	{
		return tema_id;
	}
	public void setTema_id(int tema_id)
	{
		this.tema_id = tema_id;
	}
	public String getTitulo()
	{
		return titulo;
	}
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public int getNota() 
	{
		return nota;
	}
	public void setNota(int nota)
	{
		this.nota = nota;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}