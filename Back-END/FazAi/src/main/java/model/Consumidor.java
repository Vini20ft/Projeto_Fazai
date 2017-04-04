package model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Table(name="consumidor")
public class Consumidor{
	@Id
	@Column(name="cpf", length=50, nullable=false)
	private String cpf;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	@Column(name="email", length=50, nullable=false)
	private String email;
	@Column(name="telefone", length=20, nullable=false)
	private String telefone;
	@OneToMany(mappedBy = "consumidor",
			fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Pedido> pedido;
	
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
