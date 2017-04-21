package br.com.fazai.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_CONSUMIDOR")
public class Consumidor implements Serializable{
	
	//Atributos de Consumidor.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pk_consumidor", length=14, nullable=false)
	private int id_consumidor;
	@Column(name="st_nome", length=50, nullable=false)
	private String nome_consumidor;
	@Column(name="st_email", length=50, nullable=false)
	private String email_consumidor;
	@Column(name="st_telefone", length=16, nullable=false)
	private String telefone_consumidor;
	
	//Construtor de Consumidor.
	public Consumidor(){}
	
	//Gets e Sets de Consumidor.
	
	@OneToMany
    private Collection<Pedido> historico_pedido_consumidor;
	

	public Collection<Pedido> getHistorico_pedido_consumidor() {
		return historico_pedido_consumidor;
	}

	public void setHistorico_pedido_consumidor(
			Collection<Pedido> historico_pedido_consumidor) {
		this.historico_pedido_consumidor = historico_pedido_consumidor;
	}

	public String getNome_consumidor() {
		return nome_consumidor;
	}

	public int getId_consumidor() {
		return id_consumidor;
	}

	public void setId_consumidor(int id_consumidor) {
		this.id_consumidor = id_consumidor;
	}

	public void setNome_consumidor(String nome_consumidor) {
		this.nome_consumidor = nome_consumidor;
	}

	public String getEmail_consumidor() {
		return email_consumidor;
	}

	public void setEmail_consumidor(String email_consumidor) {
		this.email_consumidor = email_consumidor;
	}

	public String getTelefone_consumidor() {
		return telefone_consumidor;
	}

	public void setTelefone_consumidor(String telefone_consumidor) {
		this.telefone_consumidor = telefone_consumidor;
	}

	
}
