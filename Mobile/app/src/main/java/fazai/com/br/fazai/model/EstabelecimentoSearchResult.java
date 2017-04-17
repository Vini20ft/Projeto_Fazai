package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EstabelecimentoSearchResult {

    @SerializedName("estabelecimentosList")
    public List<Estabelecimento> estabelecimentos;

    @SerializedName("estabelecimento")
    public Estabelecimento estabelecimento;
}
