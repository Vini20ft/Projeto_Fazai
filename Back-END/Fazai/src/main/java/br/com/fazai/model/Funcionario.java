package br.com.fazai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


@Entity
@Table(name="TB_FUNCIONARIO")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_funcionario_pk",nullable=false)
	private int id_funcionario;
	@Column(name="st_nome", length=80, nullable=false)
	private String nome_funcionario;
	@Column(name="st_email", length=80, nullable=false)
	private String email_funcionario;
	@Column(name="st_login", length=30, nullable=false)
	private String login;
	@Column(name="st_senha", length=15, nullable=false)
	private String senha;
	
	@Column(name="st_telefone", length=15, nullable=false)
	private String telefone_funcionario;
	
	@Column(name="st_rua", length=120, nullable=false)
	private String rua_funcionario;
	@Column(name="st_cidade", length=50, nullable=false)
	private String cidade_funcionario;
	@Column(name="st_perfil", length=11, nullable=false)
	private String perfil_funcionario;
	@Column(name="st_bairro", length=60,nullable=false)
	private String bairro_funcionario;
	@Column(name="st_numero", length=60,nullable=false)
	private int numero_funcionario;
	
	 public int getNumero_funcionario() {
		return numero_funcionario;
	}
	public void setNumero_funcionario(int numero_funcionario) {
		this.numero_funcionario = numero_funcionario;
	}
	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
	@ManyToOne
	 @JoinColumn(name="id_estabelecimento_fk")
	 private Estabelecimento estabelecimento;
	  
	
	public int getId_funcionario() {
		return id_funcionario;
	}
	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	public String getNome_funcionario() {
		return nome_funcionario;
	}
	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}
	public String getEmail_funcionario() {
		return email_funcionario;
	}
	public void setEmail_funcionario(String email_funcionario) {
		this.email_funcionario = email_funcionario;
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
	
	public String getTelefone_funcionario() {
		return telefone_funcionario;
	}
	public void setTelefone_funcionario(String telefone_funcionario) {
		this.telefone_funcionario = telefone_funcionario;
	}
	public String getRua_funcionario() {
		return rua_funcionario;
	}
	public void setRua_funcionario(String rua_funcionario) {
		this.rua_funcionario = rua_funcionario;
	}
	public String getCidade_funcionario() {
		return cidade_funcionario;
	}
	public void setCidade_funcionario(String cidade_funcionario) {
		this.cidade_funcionario = cidade_funcionario;
	}
	public String getPerfil_funcionario() {
		return perfil_funcionario;
	}
	public void setPerfil_funcionario(String perfil_funcionario) {
		this.perfil_funcionario = perfil_funcionario;
	}
	public String getBairro_funcionario() {
		return bairro_funcionario;
	}
	public void setBairro_funcionario(String bairro_funcionario) {
		this.bairro_funcionario = bairro_funcionario;
	}
	
	
	
	
}
