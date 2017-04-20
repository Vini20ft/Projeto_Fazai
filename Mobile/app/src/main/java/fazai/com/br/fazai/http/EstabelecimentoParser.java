package fazai.com.br.fazai.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.Localizacao;
import fazai.com.br.fazai.model.searchResult.EstabelecimentoSearchResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class EstabelecimentoParser {

    protected static Estabelecimento searchDetailById(/*int id*/) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        String urlApi = String.format("https://dl.dropboxusercontent.com/s/ymj0kr2u7ayauzk/estabelecimentoCardapio");
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            EstabelecimentoSearchResult result = gson.fromJson(json, EstabelecimentoSearchResult.class);

            if (result != null)
                return result.estabelecimento;
        }

        return null;
    }

    protected static List<Estabelecimento> searchByTitle(String q) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        String urlApi = String.format("url do servico");
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            EstabelecimentoSearchResult result = gson.fromJson(json, EstabelecimentoSearchResult.class);

            if (result != null)
                return result.estabelecimentos;
        }

        return null;
    }

    protected static List<Estabelecimento> searchAll() throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        String urlApi = String.format("https://dl.dropboxusercontent.com/s/frjpvau0617exia/estabelecimentosList");
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            EstabelecimentoSearchResult result = gson.fromJson(json, EstabelecimentoSearchResult.class);

            if (result != null)
                return result.estabelecimentos;
        }

        return null;
    }

    protected static List<Estabelecimento> searchAllByLocation(Localizacao localizacao) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        long latitude = localizacao.latitude;
        long longitude = localizacao.longitude;

        String urlApi = String.format("https://dl.dropboxusercontent.com/s/frjpvau0617exia/estabelecimentosList.json");
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            EstabelecimentoSearchResult result = gson.fromJson(json, EstabelecimentoSearchResult.class);

            if (result != null)
                return result.estabelecimentos;
        }

        return null;
    }

    protected static Estabelecimento searchByLocation(Localizacao localizacao) throws IOException {
        //estabelece a conexão com o servidor
        OkHttpClient client = new OkHttpClient();

        //fazendo requisicao ao servidor
        long latitude = localizacao.latitude;
        long longitude = localizacao.longitude;

        String urlApi = String.format("url do servico");
        Request request = new Request.Builder().url(urlApi).build();

        //resposta do servidor
        Response response = client.newCall(request).execute();

        //verificando se não houve erro de conexão
        if (response.networkResponse().code() == HttpURLConnection.HTTP_OK) {
            String json = response.body().string();

            //converter o result json em obj java
            Gson gson = new Gson();
            EstabelecimentoSearchResult result = gson.fromJson(json, EstabelecimentoSearchResult.class);

            if (result != null)
                return result.estabelecimento;
        }

        return null;
    }
}