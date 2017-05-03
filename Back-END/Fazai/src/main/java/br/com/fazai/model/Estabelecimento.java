package br.com.fazai.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ESTABELECIMENTO")
public class Estabelecimento {

    // Atributos de Estabelecimento.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estabelecimento_pk")
    private int id_estabelecimento;
    @Column(name = "st_cnpj", length = 18, nullable = false)
    private String cnpj_estabelecimento;
    @Column(name = "st_nome", length = 50, nullable = false)
    private String nome_estabelecimento;
    @Column(name = "st_razaoSocial", length = 80)
    private String razaoSocial_estabelecimento;
    @Column(name = "st_especialidade", length = 120, nullable = false)
    private String especialidade_estabelecimento;
    @Column(name = "st_urlImage", length = 120, nullable = false)
    private String url_image_Estabelecimento;
    @Column(name = "st_latitude", length = 120, nullable = false)
    private String latitude_estabelecimento;
    @Column(name = "st_longitude", length = 120, nullable = false)
    private String longitude_estabelecimento;
    @Column(name = "st_cidade", length = 50, nullable = false)
    private String cidade_estabelecimento;
    @Column(name = "st_estado", length = 50, nullable = false)
    private String estado_estabelecimento;
    @Column(name = "dc_rating")
    private float rating_estabelecimento;

    @OneToMany(mappedBy = "estabelecimento", targetEntity = Pedido.class, cascade = CascadeType.ALL)
    private Collection<Pedido> historico_pedido_foodtruck;

    @OneToMany(mappedBy = "cardapio_estabelecimento_fk", targetEntity = Cardapio.class, cascade = CascadeType.ALL)
    private Collection<Cardapio> lista_cardapio;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "FuncionarioFoodTruck", joinColumns = { @JoinColumn(name = "id_estabelecimento_pk"), }, inverseJoinColumns = { @JoinColumn(name = "id_funcionario_pk") })
    private Collection<Funcionario> estabelecimento_funcionario;

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

    public void setEspecialidade_estabelecimento(String especialidade_estabelecimento) {
	this.especialidade_estabelecimento = especialidade_estabelecimento;
    }

    public String getUrl_image_Estabelecimento() {
	return url_image_Estabelecimento;
    }

    public void setUrl_image_Estabelecimento(String url_image_Estabelecimento) {
	this.url_image_Estabelecimento = url_image_Estabelecimento;
    }

    public float getRating_estabelecimento() {
	return rating_estabelecimento;
    }

    public void setRating_estabelecimento(float rating_estabelecimento) {
	this.rating_estabelecimento = rating_estabelecimento;
    }

    public String getCidade_estabelecimento() {
        return cidade_estabelecimento;
    }

    public void setCidade_estabelecimento(String cidade_estabelecimento) {
        this.cidade_estabelecimento = cidade_estabelecimento;
    }

    public String getEstado_estabelecimento() {
        return estado_estabelecimento;
    }

    public void setEstado_estabelecimento(String estado_estabelecimento) {
        this.estado_estabelecimento = estado_estabelecimento;
    }
 
}
