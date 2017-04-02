package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcionario")
public class Funcionario {
	
	//Atributos da Classe Funcionário.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private int id;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	@Column(name="nome", length=50, nullable=false)
	private String email;
	@Column(name="login", length=50, nullable=false)
	private String login;
	@Column(name="senha", length=50, nullable=false)
	private String senha;
	@Column(name="estado", length=50, nullable=false)
	private String estado;
	@Column(name="bairro", length=50, nullable=false)
	private String bairro;
	@Column(name="cidade", length=50, nullable=false)
	private String cidade;
	@Column(name="rua", length=50, nullable=false)
	private String rua;
	@Column(name="numero", length=50, nullable=false)
	private int numero;
	@Column(name="telefone", length=50, nullable=false)
	private String telefone;
	@Column(name="perfil", length=50, nullable=false)
	private String perfil;
	
	//Construtor da Classe Funcionário.
	
	public Funcionario(){}
	
	//Gets e Sets de Funcionário.
	
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
}
