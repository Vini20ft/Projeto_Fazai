package br.com.fazai.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Pedido_Cardapio_Item implements Serializable {

    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "id_item", column = @Column(name = "id_item", nullable = false)),
	    @AttributeOverride(name = "id_pedido_pk", column = @Column(name = "id_pedido_pk", nullable = false)) })
    private Id id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido_pk", nullable = false, insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item", nullable = false, insertable = false, updatable = false)
    private ItemCardapio itemcardapio;

    @Column(name = "quantidade", length = 40)
    private int quantidade;

    public Pedido getPedido() {
	return pedido;
    }

    public void setPedido(Pedido pedido) {
	this.pedido = pedido;
    }

    public ItemCardapio getItemcardapio() {
	return itemcardapio;
    }

    public void setItemcardapio(ItemCardapio itemcardapio) {
	this.itemcardapio = itemcardapio;
    }

    public int getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
    }

}
