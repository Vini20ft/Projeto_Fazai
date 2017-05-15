package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;
import com.google.maps.android.quadtree.PointQuadTree;

/**
 * Created by Ricardo on 14/05/2017.
 */

public class ItemPedido {

    @SerializedName("id")
    public int id;
    @SerializedName("idPedido")
    public int idPedido;
    @SerializedName("idItemCardapio")
    public int idItemCardapio;
    @SerializedName("quantidade")
    public int quantidade;
    @SerializedName("ItemCardapio")
    public ItemCardapio itemCardapio;
    @SerializedName("Pedido")
    public Pedido pedido;

    public ItemPedido() {
        itemCardapio = new ItemCardapio();
        pedido = new Pedido();
    }

    public ItemPedido(int id, int idPedido, int idItemCardapio,int quantidade){

        this.id = id;
        this.idPedido = idPedido;
        this.idItemCardapio = idItemCardapio;
        this.quantidade = quantidade;
    }
}
