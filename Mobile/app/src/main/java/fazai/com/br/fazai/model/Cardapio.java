package fazai.com.br.fazai.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    @SerializedName("id")
    public String id;
    @SerializedName("foto")
    public String foto;
    @SerializedName("descricao")
    public String descricao;
    @SerializedName("itens_cardapio")
    public List<ItemCardapio> itemCardapios;
    @SerializedName("estabelecimento")
    public Estabelecimento estabelecimento;

    public Cardapio() {
        itemCardapios = new ArrayList<>();
        estabelecimento = new Estabelecimento();
    }

    public Cardapio(String foto, String titulo, String descricao, Float preco, List<ItemCardapio> itemCardapios, Estabelecimento estabelecimento) {
        this.foto = foto;
        this.descricao = descricao;
        this.itemCardapios = itemCardapios;
        this.estabelecimento = estabelecimento;
    }
}
