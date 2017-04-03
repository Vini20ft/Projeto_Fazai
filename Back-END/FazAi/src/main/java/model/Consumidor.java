package model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="consumidor")
public class Consumidor{
	
	@Column(name="cpf", length=50, nullable=false)
	private String cpf;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	@Column(name="email", length=50, nullable=false)
	private String email;
	@Column(name="telefone", length=20, nullable=false)
	private String telefone;
	
	public Consumidor(){}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
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
