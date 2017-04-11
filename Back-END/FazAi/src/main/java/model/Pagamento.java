package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pagamento")
public class Pagamento {
	
	//Atributos da classe Pagamento
	@Id
	@Column(name = "codigo", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "numero_Pgto", nullable = false, unique=true)
	private int  numero_Pgto;
	@Column(name = "valor_Total", nullable = false)
	private double valor_Total;
	@Column(name = "Data_Pgto", nullable = false)
	private Date data_Pgto;
	@Column(name = "Hora_Pgto", nullable = false)
	private Date hora_Pgto;
	
	//Contrtutor da Classe Pagamento.
	public Pagamento() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumero_Pgto() {
		return numero_Pgto;
	}

	public void setNumero_Pgto(int numero_Pgto) {
		this.numero_Pgto = numero_Pgto;
	}

	public double getValor_Total() {
		return valor_Total;
	}

	public void setValor_Total(double valor_Total) {
		this.valor_Total = valor_Total;
	}

	public Date getData_Pgto() {
		return data_Pgto;
	}

	public void setData_Pgto(Date data_Pgto) {
		this.data_Pgto = data_Pgto;
	}

	public Date getHora_Pgto() {
		return hora_Pgto;
	}

	public void setHora_Pgto(Date hora_Pgto) {
		this.hora_Pgto = hora_Pgto;
	}
}
