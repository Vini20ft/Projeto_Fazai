package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Estabelecimento {
    @SerializedName("id")
    public int id;
    @SerializedName("cnpj")
    public String cnpj;
    @SerializedName("nome")
    public String nome;
    @SerializedName("razaoSocial")
    public String razaoSocial;
    @SerializedName("especialidade")
    public String especialidade;
    @SerializedName("endereco")
    public Endereco endereco;
    @SerializedName("foto")
    public String foto;
    @SerializedName("pratos")
    public List<Prato> pratoList;

    public Estabelecimento() {
        endereco = new Endereco();
        pratoList = new ArrayList<>();
    }

    public Estabelecimento(int id, String cnpj, String nome, String razaoSocial, String especialidade, String foto, List<Prato> pratos) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.especialidade = especialidade;
        this.foto = foto;
        this.pratoList = pratos;
    }
}
