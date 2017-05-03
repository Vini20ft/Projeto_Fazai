package br.com.fazai.dominion;

import java.util.List;

public class CardapioMobile {
    
    private int id;
    private String foto;
    private String descricao;
    private List<ItemMobile> itens_cardapio;
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<ItemMobile> getItens_cardapio() {
        return itens_cardapio;
    }
    public void setItens_cardapio(List<ItemMobile> itens_cardapio) {
        this.itens_cardapio = itens_cardapio;
    }
   
}
