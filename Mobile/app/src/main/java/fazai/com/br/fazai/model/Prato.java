package fazai.com.br.fazai.model;


import com.google.gson.annotations.SerializedName;

public class Prato {
    @SerializedName("foto")
    public String foto;
    @SerializedName("titulo")
    public String titulo;
    @SerializedName("descricao")
    public String descricao;
    @SerializedName("preco")
    public Float preco;

    public Prato() {
    }

    public Prato(String foto, String titulo, String descricao, Float preco) {
        this.foto = foto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
    }
}
