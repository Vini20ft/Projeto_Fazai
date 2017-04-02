package model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="consumidor")
public class Consumidor{
	
	//Atributos da Classe Consumidor.
	
	@Id
	@Column(name="cpf", length=50, nullable=false)
	private int cpf;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	@Column(name="email", length=50, nullable=false)
	private String email;
	@Column(name="telefone", length=20, nullable=false)
	private String telefone;
	
	//Construtor de Consumidor.
	
	public Consumidor(){}
	
	//Gets e Sets da Classe Consumidor.
	
	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
