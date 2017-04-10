package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


public class Estabelecimento {

    @SerializedName("cnpj")
    public int cnpj;
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

    public Estabelecimento() {
        endereco = new Endereco();
    }

    public Estabelecimento(int cnpj, String nome, String razaoSocial, String especialidade, String foto) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.especialidade = especialidade;
        this.foto = foto;
    }
}
