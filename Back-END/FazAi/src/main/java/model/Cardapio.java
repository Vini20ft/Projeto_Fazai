package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cardapio")
public class Cardapio {
	
	//Atributos da classe cardapio.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private int id;
	@Column(name="tipo", length=50, nullable=false)
	private String tipo;
	@Column(name="criador", length=50, nullable=false)
	private String criador;
	@Column(name="descricao", length=50, nullable=false)
	private String descricao;
	
	//Construtor da classe cardapio.
	public Cardapio() {}
	
	//Gets e Sets da classe cardapio.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCriador() {
		return criador;
	}

	public void setCriador(String criador) {
		this.criador = criador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
