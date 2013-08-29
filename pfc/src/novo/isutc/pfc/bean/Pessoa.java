package novo.isutc.pfc.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	
    @Id
    @Column(name="numeropessoa")
    private int numeropessoa;
 
    @OneToOne(mappedBy="pessoa", cascade=CascadeType.ALL)
    private Estudante estudante;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;

	public Pessoa()
	{
		
	}
	
	public Pessoa(int numeropessoa, String nome, String email) {
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
