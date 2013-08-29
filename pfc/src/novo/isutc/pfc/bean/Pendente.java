package novo.isutc.pfc.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pendente")
public class Pendente {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "numero")
	private Integer numero; 
	
	@ManyToOne(targetEntity=Exemplar.class)  
	@JoinColumn(name="numeroexemplar",referencedColumnName="numero")
	private Exemplar exemplar;
	
	@Column(name="copia")
	private boolean copia;
	
	@Column(name="regularizada")
	private boolean regularizada;
	
	public Pendente()
	{
		
	}

	public Pendente(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		this.exemplar = exemplar;
	}

	public boolean isCopia() {
		return copia;
	}

	public void setCopia(boolean copia) {
		this.copia = copia;
	}

	public boolean isRegularizada() {
		return regularizada;
	}

	public void setRegularizada(boolean regularizada) {
		this.regularizada = regularizada;
	}
	
	public boolean isResolvido()
	{
		boolean resolvido = copia && regularizada;
		return resolvido;
	}
	
	public void test()
	{

	}
	 
	
}
