package novo.isutc.pfc.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.*;

@Entity
@Table(name="docente")
public class Docente {
	
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="numeropessoa")
    private int numeropessoa;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Juri> juris;
	
	@OneToMany(mappedBy="supervisor",fetch = FetchType.LAZY)
	private Set<Pfc> pfcs;
	
	public Docente()
	{
		
	}
	
	public Docente(String nome, String email) {
		this.numeropessoa = numeropessoa;
		this.nome = nome;
		this.email = email;
	}

	public int getNumeropessoa() {
		return numeropessoa;
	}

	public void setNumeropessoa(int numeropessoa) {
		this.numeropessoa = numeropessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
