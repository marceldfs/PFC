package novo.isutc.pfc.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "juri")
public class Juri {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "posicao")
	private String posicao;
	
	@ManyToOne
	@JoinColumn(name="codigocurso",referencedColumnName="codigocurso")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name="numeropessoa",referencedColumnName="numeropessoa")
	private Docente docente;

	public Juri()
	{
		
	}
	
	public Juri(String posicao, Curso curso, Docente docente) 
	{
		this.posicao = posicao;
		this.curso = curso;
		this.docente = docente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	
	
}
