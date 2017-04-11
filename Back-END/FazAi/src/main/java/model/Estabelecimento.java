package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Estabelecimento")
public class Estabelecimento{
	
	//Atributos de Estabelecimento.
	
	@Id
	@Column(name="cnpj")
	private int cnpj;
	@Column(name="nome", length=50, nullable=false, unique=true)
	private String nome;
	@Column(name="razaoSocial", length=50, nullable=false)
	private String razaoSocial;
	@Column(name="especialidade", length=50, nullable=false)
	private String especialidade;
	@Column(name="endereco", length=50, nullable=false)
	private String endereco;
	@Column(name="latitude", length=50, nullable=false)
	private String latitude;
	@Column(name="longetude", length=50, nullable=false)
	private String longetude;
	
	
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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String reazaoSocial) {
		this.razaoSocial = reazaoSocial;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongetude() {
		return longetude;
	}

	public void setLongetude(String longetude) {
		this.longetude = longetude;
	}
		
}
