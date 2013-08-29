package novo.isutc.pfc.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "versao")
public class Versao {

	@Id
	@Column(name = "numeroversao")
	private int numeroversao;

	@Column(name = "nome")
	private String nome;
	
	@OneToMany(mappedBy="versao",fetch = FetchType.LAZY)
	private Set<Exemplar> exemplares;
	
	public Versao()
	{
		
	}

	public Versao(int numeroversao, String nome, Set<Exemplar> exemplares) {
		this.numeroversao = numeroversao;
		this.nome = nome;
		this.exemplares = exemplares;
	}

	public int getNumeroversao() {
		return numeroversao;
	}

	public void setNumeroversao(int numeroversao) {
		this.numeroversao = numeroversao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(Set<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	
	
}
