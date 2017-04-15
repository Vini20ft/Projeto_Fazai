package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Caio Ernandes on 15/04/2017.
 */

public class ItemCardapio {
    @SerializedName("codigo")
    public String id;
    @SerializedName("nome")
    public String nome;
    @SerializedName("imagem")
    public String imagem;
    @SerializedName("descricao")
    public String descricao;
    @SerializedName("valor")
    public float valor;
    @SerializedName("tempo_estimado")
    public int tempoEstimado;

    public ItemCardapio() { }

    public ItemCardapio(String id, String nome, String imagem, String descricao, float valor, int tempoEstimado) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.descricao = descricao;
        this.valor = valor;
        this.tempoEstimado = tempoEstimado;
    }
}
