package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


public class Endereco {

    @SerializedName("estado")
    public String estado;
    @SerializedName("bairro")
    public String bairro;
    @SerializedName("cidade")
    public String cidade;
    @SerializedName("rua")
    public String rua;
    @SerializedName("numero")
    public int numero;
    @SerializedName("telefone")
    public String telefone;

    public Localizacao localizacao;

    public Endereco() {
        localizacao = new Localizacao();
    }

    public Endereco(String estado, String bairro, String cidade, String rua, int numero, String telefone) {
        this.estado = estado;
        this.bairro = bairro;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
    }
}
