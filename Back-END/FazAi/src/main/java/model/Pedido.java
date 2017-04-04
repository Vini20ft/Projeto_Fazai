package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="pedido")
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo", 
				insertable = true,
				updatable = true)
	@Fetch(FetchMode.JOIN)
	Pagamento pagamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cpf", 
				insertable = true,
				updatable = true)
	@Fetch(FetchMode.JOIN)
	Consumidor consumidor;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pedidoCardapio", 
            joinColumns = @JoinColumn(name="codigo"),
            inverseJoinColumns = @JoinColumn(name= "codigo"))
    @Column (name = "pedido")
	List<Cardapio> cardapios;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cnpj", 
				insertable = true,
				updatable = true)
	@Fetch(FetchMode.JOIN)
	Estabelecimento estabelecimento;
	
		
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
