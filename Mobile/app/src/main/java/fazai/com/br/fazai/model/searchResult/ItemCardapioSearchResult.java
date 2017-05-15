package fazai.com.br.fazai.model.searchResult;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fazai.com.br.fazai.model.ItemCardapio;


public class ItemCardapioSearchResult {
    @SerializedName("itensCardapio")
    public List<ItemCardapio> itensCardapio;

    @SerializedName("itemCardapio")
    public ItemCardapio itemCardapio;
}
