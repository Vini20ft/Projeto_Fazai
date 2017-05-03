package fazai.com.br.fazai.model;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by 01 on 02/05/2017.
 */

public class VerifyConnection {

    public Activity activity;

    public VerifyConnection(Activity activity) {
        this.activity = activity;
    }

    public boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }


}
