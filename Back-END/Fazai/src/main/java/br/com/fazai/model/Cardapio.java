package br.com.fazai.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CARDAPIO")
public class Cardapio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cardapio_pk")
	private int id_cardapio;
	@Column(name = "st_tipo", length = 50, nullable = false)
	private String tipo_cardapio;
	@Column(name = "st_descricao", length = 500, nullable = true)
	private String descricao_cardapio;

	// relacionamento com itens

	@ManyToOne(targetEntity = Estabelecimento.class)
	private Estabelecimento _estabelecimento_fk;

	@OneToMany(mappedBy = "cardapio", targetEntity = ItemCardapio.class, cascade = CascadeType.ALL)
	private Collection<ItemCardapio> lista_de_item;

	public int getId_cardapio() {
		return id_cardapio;
	}

	public void setId_cardapio(int id_cardapio) {
		this.id_cardapio = id_cardapio;
	}

	public String getTipo_cardapio() {
		return tipo_cardapio;
	}

	public void setTipo_cardapio(String tipo_cardapio) {
		this.tipo_cardapio = tipo_cardapio;
	}

	public String getDescricao_cardapio() {
		return descricao_cardapio;
	}

	public void setDescricao_cardapio(String descricao_cardapio) {
		this.descricao_cardapio = descricao_cardapio;
	}

	public Collection<ItemCardapio> getLista_de_item() {
		return lista_de_item;
	}

	public void setLista_de_item(Collection<ItemCardapio> lista_de_item) {
		this.lista_de_item = lista_de_item;
	}

	public Estabelecimento getEstabelecimento() {
		return _estabelecimento_fk;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this._estabelecimento_fk = estabelecimento;
	}
	

}
