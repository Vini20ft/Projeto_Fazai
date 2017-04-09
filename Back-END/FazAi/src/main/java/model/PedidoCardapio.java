package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PedidoCardapio {

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "codigo")
	private Pedido pedido;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo")
	private Cardapio cardapios;
	
	@Column(name = "quantidade", nullable = false)
	private int Quantidade;
	
	
}
