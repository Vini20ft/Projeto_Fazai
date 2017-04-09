
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Pedido")
public class Pedido {
	
	//Atributos da classe pedidos.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo") 
	private int nPedido;
	@Column(name="observacao", length=50, nullable=false)
	private String observacao;
	@Column(name="status", length=50, nullable=false)
	private String status;
		
	//Construtor da classe pedido.
	public Pedido() {}
	
	//Gets e Sets da classe pedidos.
	public int getnPedido() {
		return nPedido;
	}

	public void setnPedido(int nPedido) {
		this.nPedido = nPedido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
