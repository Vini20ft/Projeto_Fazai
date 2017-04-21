package br.com.fazai.model;



import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;



@Entity
@Table(name="TB_ESTABELECIMENTO")
public class Estabelecimento{
	
	//Atributos de Estabelecimento.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_estabelecimento_pk")
	private int id_estabelecimento;
	@Column(name="st_cnpj",length=18,nullable=false)
	private String cnpj_estabelecimento;
	@Column(name="st_nome", length=50, nullable=false)
	private String nome_estabelecimento;
	@Column(name="st_razaoSocial", length=80, nullable=false)
	private String razaoSocial_estabelecimento;
	@Column(name="st_especialidade", length=120, nullable=false)
	private String especialidade_estabelecimento;
	@Column(name="st_latitude", length=120, nullable=false)
	private String latitude_estabelecimento;
	@Column(name="st_longitude", length=120, nullable=false)
	private String longitude_estabelecimento;
	
	public String getLatitude_estabelecimento() {
		return latitude_estabelecimento;
	}
	public void setLatitude_estabelecimento(String latitude_estabelecimento) {
		this.latitude_estabelecimento = latitude_estabelecimento;
	}
	public String getLongitude_estabelecimento() {
		return longitude_estabelecimento;
	}
	public void setLongitude_estabelecimento(String longitude_estabelecimento) {
		this.longitude_estabelecimento = longitude_estabelecimento;
	}
	public List<Funcionario> getFuncionario_estabelecimento() {
		return funcionario_estabelecimento;
	}
	public void setFuncionario_estabelecimento(
			List<Funcionario> funcionario_estabelecimento) {
		this.funcionario_estabelecimento = funcionario_estabelecimento;
	}
	
	@OneToMany(mappedBy = "estabelecimento", targetEntity = Funcionario.class, 
			fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	    private List<Funcionario> funcionario_estabelecimento;
	
	
	public int getId_estabelecimento() {
		return id_estabelecimento;
	}
	public void setId_estabelecimento(int id_estabelecimento) {
		this.id_estabelecimento = id_estabelecimento;
	}
	public String getCnpj_estabelecimento() {
		return cnpj_estabelecimento;
	}
	public void setCnpj_estabelecimento(String cnpj_estabelecimento) {
		this.cnpj_estabelecimento = cnpj_estabelecimento;
	}
	public String getNome_estabelecimento() {
		return nome_estabelecimento;
	}
	public void setNome_estabelecimento(String nome_estabelecimento) {
		this.nome_estabelecimento = nome_estabelecimento;
	}
	public String getRazaoSocial_estabelecimento() {
		return razaoSocial_estabelecimento;
	}
	public void setRazaoSocial_estabelecimento(String razaoSocial_estabelecimento) {
		this.razaoSocial_estabelecimento = razaoSocial_estabelecimento;
	}
	public String getEspecialidade_estabelecimento() {
		return especialidade_estabelecimento;
	}
	public void setEspecialidade_estabelecimento(
			String especialidade_estabelecimento) {
		this.especialidade_estabelecimento = especialidade_estabelecimento;
	}
	

	@OneToMany
    private Collection<Pedido> historico_pedido_foodtruck;
	
	@OneToMany
    private Collection<Cardapio> lista_cardapio;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="FuncionarioFoodTruck", joinColumns= {@JoinColumn(name="id_estabelecimento_pk"),},
	 inverseJoinColumns=
	  {@JoinColumn(name="id_funcionario_pk")})
	private Collection<Funcionario> estabelecimento_funcionario;

	
	
}
