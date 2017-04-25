package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


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
    @SerializedName("telefone")
    public String telefone;
    @SerializedName("rating")
    public float rating;

    public Estabelecimento() {
        endereco = new Endereco();
    }

    public Estabelecimento(int id, String cnpj, String nome, String razaoSocial, String especialidade, String foto, String telefone, float rating) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.especialidade = especialidade;
        this.foto = foto;
        this.telefone = telefone;
        this.rating = rating;
    }
}
