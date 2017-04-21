package br.com.fazai.model;

import java.io.Serializable;
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
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="TB_ITEMCARDAPIO")
public class ItemCardapio implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_itemcard", length=14, nullable=false)
	private int id_item;
	

	@Column(name="nome", length=80, nullable=false)
	private String nome;
	@Column(name="descricao", length=400, nullable=false)
	private String descricao;
	@Column(name="categoria", length=30, nullable=false)
	private String categoria;
	@Column(name="valor", length=20, nullable=false)
	private Double valor;
	@Column(name="imagem", length=400, nullable=false)
	private String imagem;
	@Column(name="tempo_estimado", length=14, nullable=false)
	private String tempo_estimado;
	
	
	

	
	public ItemCardapio() {}


	
	public Set<Pedido_Cardapio_Item> getPedido_cardapio_item() {
		return pedido_cardapio_item;
	}





	public void setPedido_cardapio_item(
			Set<Pedido_Cardapio_Item> pedido_cardapio_item) {
		this.pedido_cardapio_item = pedido_cardapio_item;
	}



	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemcardapio")
	private Set<Pedido_Cardapio_Item> pedido_cardapio_item = new
	HashSet<Pedido_Cardapio_Item>(0);

	@ManyToOne
    @JoinColumn(name="id_cardapio_pk", referencedColumnName="id_cardapio_pk")
    private Cardapio cardapio; 
	
	
	
	public int getId_item() {
		return id_item;
	}





	public void setId_item(int id_item) {
		this.id_item = id_item;
	}





	public String getNome() {
		return nome;
	}





	public void setNome(String nome) {
		this.nome = nome;
	}





	public String getDescricao() {
		return descricao;
	}





	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}





	public String getCategoria() {
		return categoria;
	}





	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}





	public Double getValor() {
		return valor;
	}





	public void setValor(Double valor) {
		this.valor = valor;
	}





	public String getImagem() {
		return imagem;
	}





	public void setImagem(String imagem) {
		this.imagem = imagem;
	}





	public String getTempo_estimado() {
		return tempo_estimado;
	}





	public void setTempo_estimado(String tempo_estimado) {
		this.tempo_estimado = tempo_estimado;
	}





	public Cardapio getCardapio() {
		return cardapio;
	}





	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}





}
