package fazai.com.br.fazai.model.searchResult;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fazai.com.br.fazai.model.Cardapio;

/**
 * Created by Caio Ernandes on 20/04/2017.
 */

public class CardapioSearchResult {
    @SerializedName("cardapioList")
    public List<Cardapio> cardapios;
}
