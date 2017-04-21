package fazai.com.br.fazai.model.searchResult;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fazai.com.br.fazai.model.Estabelecimento;


public class EstabelecimentoSearchResult {

    @SerializedName("estabelecimentosList")
    public List<Estabelecimento> estabelecimentos;

    @SerializedName("estabelecimento")
    public Estabelecimento estabelecimento;
}
