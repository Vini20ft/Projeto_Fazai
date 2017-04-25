package fazai.com.br.fazai.http.parser;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.model.searchResult.ItemCardapioSearchResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ItensCardapioParser {

    public static List<ItemCardapio> searchAllByIdCardapio(/*int idCardapio*/) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        String urlApi = String.format(Constantes.SERVICE_ITENS_CARDAPIO);
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            ItemCardapioSearchResult result = gson.fromJson(json, ItemCardapioSearchResult.class);

            if (result != null)
                return result.itensCardapio;
        }

        return null;

    }

    public static ItemCardapio searchByIdCardapio(/*int idCardapio*/) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        String urlApi = String.format(Constantes.SERVICE_DETALHE_ITEM_CARDAPIO);
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            ItemCardapioSearchResult result = gson.fromJson(json, ItemCardapioSearchResult.class);

            if (result != null)
                return result.itemCardapio;
        }

        return null;

    }

}
