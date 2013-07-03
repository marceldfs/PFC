package isutc.pfc.bean;

import java.io.InputStream;
import java.sql.Date;

public class Pfc 
{
	protected int pfc_id;
	protected int tema_id;
	protected Date data_inicio;
	protected Date data_fim;	
	protected int nota;
	protected String situation;
	protected String supervisorName;
	protected String oponente;
	protected String presidente;
	protected String titulo;
	protected int user_id;
	protected double plagium;
	protected String fullName;
	protected String description;
	protected String fase;
	
	
	
	private byte[] text;//o texto puro
	private InputStream textStream;  //texto puro como stream (para upload de dados grandes)
	private String format;
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
	}
	public InputStream getTextStream() {
		return textStream;
	}
	public void setTextStream(InputStream textStream) {
		this.textStream = textStream;
	}
	//JavaBeans
	public Date getData_fim() {
		return data_fim;
	}
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public int getPfc_id() {
		return pfc_id;
	}
	public void setPfc_id(int pfc_id) {
		this.pfc_id = pfc_id;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public int getTema_id() {
		return tema_id;
	}
	public void setTema_id(int tema_id) {
		this.tema_id = tema_id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOponente() {
		return oponente;
	}
	public void setOponente(String oponente) {
		this.oponente = oponente;
	}
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	public double getPlagium() {
		return plagium;
	}
	public void setPlagium(double plagium) {
		this.plagium = plagium;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	
	
	
}
