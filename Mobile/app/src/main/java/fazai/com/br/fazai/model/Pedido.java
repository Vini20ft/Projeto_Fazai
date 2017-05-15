package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 14/05/2017.
 */

public class Pedido {
    @SerializedName("id")
    public int id;
    @SerializedName("observacao")
    public String observacao;
    @SerializedName("status")
    public String status;
    @SerializedName("listaItensPedido")
    public List<ItemPedido> listaItensPedido;

    public int getId() {
        return id;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getStatus() {
        return status;
    }

    public List<ItemPedido> getListaItensPedido() {
        return listaItensPedido;
    }

    public Pedido(){}

    public Pedido(int id, String observacao, String status , ArrayList<ItemPedido> listaItensPedido){
        this.id = id;
        this.observacao = observacao;
        this.status = status;
        this.listaItensPedido = listaItensPedido;
    }




}
