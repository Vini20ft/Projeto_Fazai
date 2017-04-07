package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estabelecimento")
public class Estabelecimento{
	
	//Atributos de Estabelecimento.
	
	@Id
	@Column(name="cnpj")
	private int cnpj;
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	@Column(name="razaoSocial", length=50, nullable=false)
	private String reazaoSocial;
	@Column(name="especialidade", length=50, nullable=false)
	private String especialidade;
	
	//Contrtutor de Estabelecimento;
	
	public Estabelecimento(){}
	
	//Gets e Sets de Estabelecimento.
	
	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getReazaoSocial() {
		return reazaoSocial;
	}

	public void setReazaoSocial(String reazaoSocial) {
		this.reazaoSocial = reazaoSocial;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
		
}
