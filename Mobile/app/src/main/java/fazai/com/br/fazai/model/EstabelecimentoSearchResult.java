package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caio.e.nascimento on 4/3/2017.
 */

public class EstabelecimentoSearchResult {

    @SerializedName("estabelecimentosList")
    public List<Estabelecimento> estabelecimentos;

    @SerializedName("estabelecimento")
    public Estabelecimento estabelecimento;
}
