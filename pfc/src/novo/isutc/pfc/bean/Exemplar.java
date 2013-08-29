package novo.isutc.pfc.bean;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;

@Entity
@Table(name="exemplar")
public class Exemplar {
	
	@Id
    @GeneratedValue
    @Column(name="numero")
	private int numero;
	
    @ManyToOne
    @JoinColumn(name="numeropfc")
	private Pfc pfc;
	
    @ManyToOne
    @JoinColumn(name="numeroversao")
	private Versao versao;
    
    @OneToMany(mappedBy="exemplar",fetch = FetchType.LAZY)
    private Set<Pendente> pendentes;
	
	@Column(name="data")
	private Date data;
	
	@Column(name="document")
	private byte[] document;
	
	@Column(name="estado")
	private boolean estado;

	@Column(name="formato")
	private String formato;

	public Exemplar()
	{
		
	}
	
	public Exemplar(Pfc pfc, Versao versao, byte[] document, String formato) {
		this.pfc = pfc;
		this.versao = versao;
		this.data = new Date();
		this.document = document;
		this.formato = formato;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Pfc getPfc() {
		return pfc;
	}

	public void setPfc(Pfc pfc) {
		this.pfc = pfc;
	}

	public Versao getVersao() {
		return versao;
	}

	public void setVersao(Versao versao) {
		this.versao = versao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
}
