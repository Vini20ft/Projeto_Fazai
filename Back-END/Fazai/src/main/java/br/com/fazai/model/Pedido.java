package br.com.fazai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_PEDIDO")
public class Pedido implements Serializable{
	
	//Atributos da classe pedidos.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedido_pk") 
	private int numero_pedido;
	@Column(name="st_observacao", length=50, nullable=false)
	private String observacao_pedido;
	@Column(name="st_status", length=20, nullable=false)
	private String status_pedido;
	@Column(name="st_data", length=20, nullable=false)
	private Date data;
	@Column(name="st_hora", length=20, nullable=false)
	private String hora;

	
	
		
	//Construtor da classe pedido.
	public Pedido() {}
	
	@OneToOne
	 private Pagamento pagamento;
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	

	public Set<Pedido_Cardapio_Item> getPedido_cardapio_item() {
		return pedido_cardapio_item;
	}

	public void setPedido_cardapio_item(
			Set<Pedido_Cardapio_Item> pedido_cardapio_item) {
		this.pedido_cardapio_item = pedido_cardapio_item;
	}

	@ManyToOne
    @JoinColumn(name="id_pk_consumidor", referencedColumnName="id_pk_consumidor")
    private Consumidor consumidor; 
	
	@ManyToOne
    @JoinColumn(name="id_estabelecimento_pk", referencedColumnName="id_estabelecimento_pk")
    private Estabelecimento estabelecimento; 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private Set<Pedido_Cardapio_Item> pedido_cardapio_item =
	new HashSet<Pedido_Cardapio_Item>(0);

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}


	public int getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(int numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public String getObservacao_pedido() {
		return observacao_pedido;
	}

	public void setObservacao_pedido(String observacao_pedido) {
		this.observacao_pedido = observacao_pedido;
	}

	public String getStatus_pedido() {
		return status_pedido;
	}

	public void setStatus_pedido(String status_pedido) {
		this.status_pedido = status_pedido;
	}
	
	
}
