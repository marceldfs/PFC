package novo.isutc.pfc.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.*;

@Entity
@Table(name="estudante")
public class Estudante {

	
    @Id
    @Column(name="numeropessoa")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="pessoa"))
    private Integer numeropessoa;
     
    @OneToOne
    @PrimaryKeyJoinColumn
    private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="codigocurso",referencedColumnName="codigocurso", updatable = false)
	private Curso curso;
     
     
	@OneToMany
	@JoinColumn(name="numeropessoa")
    private Set<Pfc> pfc;
	
	public Estudante()
	{
		
	}
	
	public Estudante(Curso curso, Pessoa pessoa) {
		this.curso = curso;
		this.pessoa = pessoa;
	}
	
	public Integer getNumeropessoa() {
		return numeropessoa;
	}

	public void setNumeropessoa(Integer numeropessoa) {
		this.numeropessoa = numeropessoa;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	
}
