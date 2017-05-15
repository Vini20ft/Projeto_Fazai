package fazai.com.br.fazai.http.post;


import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.Pedido;
import okhttp3.FormBody;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Ricardo on 14/05/2017.
 */

public class PedidoClient {

    public String sendPosRequestPedido(Pedido pedido) {
        Response response = null;
        if(pedido != null) {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("Pedido", pedido.toString())
                    .build();

            Request request = new Request.Builder()
                    .url(Constantes.POST_REQUEST)
                    .post(requestBody)
                    .build();
            try {
                response = client.newCall(request).execute();
                String responseString = response.body().string();
                response.body().close();
                // do whatever you need to do with responseString
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.body().toString();
    }




}
