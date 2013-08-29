package novo.isutc.pfc.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "pfc")
public class Pfc {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "numeropfc")
	private Integer numeropfc;
	
	@Column(name="tema")
	private String tema;
	
	@ManyToOne(targetEntity=Estudante.class)  
	@JoinColumn(name="estudante",referencedColumnName="numeropessoa") 
	private Estudante estudante;

	@ManyToOne(targetEntity=Docente.class) 
	@JoinColumn(name="supervisor",referencedColumnName="numeropessoa")
	private Docente supervisor;
	
	@ManyToOne(targetEntity=Docente.class) 
	@JoinColumn(name="oponente",referencedColumnName="numeropessoa")
	private Docente oponente;
	
	@ManyToOne(targetEntity=Docente.class) 
	@JoinColumn(name="presidente",referencedColumnName="numeropessoa")
	private Docente presidente;
	
	@Column(name="publicado")
	private boolean publicado;
	
	@OneToMany(mappedBy="pfc",fetch = FetchType.LAZY)
    private Set<Exemplar> exemplares;

	public Pfc()
	{
		
	}
	
	public Pfc(String tema, Estudante estudante, Docente supervisor) {
		this.tema = tema;
		this.estudante = estudante;
		this.supervisor = supervisor;
	}

	public Integer getNumeropfc() {
		return numeropfc;
	}

	public void setNumeropfc(Integer numeropfc) {
		this.numeropfc = numeropfc;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Docente getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Docente supervisor) {
		this.supervisor = supervisor;
	}

	public Docente getOponente() {
		return oponente;
	}

	public void setOponente(Docente oponente) {
		this.oponente = oponente;
	}

	public Docente getPresidente() {
		return presidente;
	}

	public void setPresidente(Docente presidente) {
		this.presidente = presidente;
	}

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	public Set<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(Set<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	
	public String getTemaStudent()
	{
		return this.getEstudante().getPessoa().getNome()+tema.substring(0, 20);
	}
	
	
}
