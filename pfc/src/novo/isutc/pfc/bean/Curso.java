package novo.isutc.pfc.bean;

import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "curso")
public class Curso {

	@Id
	@Column(name = "codigocurso")
	private String codigocurso;

	@Column(name = "designacao")
	private String designacao;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Juri> juris;
	
	@OneToMany(mappedBy="curso",fetch = FetchType.LAZY)
	private Set<Estudante> estudantes;
	

	@Column(name = "codigocurso")
	public String getCodigoCurso() {
		return codigocurso;
	}

	public void setCodigoCurso(String codigocurso) {
		this.codigocurso = codigocurso;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

}
