package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Funcionario")
public class Funcionario {
	
	//Atributos da Classe Estabelecimento.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="nome", length=50, nullable=false, unique=true)
	private String nome;
	@Column(name="email", length=50, nullable=false, unique=true)
	private String email;
	@Column(name="login", length=50, nullable=false, unique=true)
	private String login;
	@Column(name="senha", length=50, nullable=false)
	private String senha;
	
	//Construtor de Funcionario.
	public Funcionario(){}
	
	//Gets e Sets de Funcionario.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
