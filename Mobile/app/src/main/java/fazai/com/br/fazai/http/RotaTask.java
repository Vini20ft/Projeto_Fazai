package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Ricardo on 20/04/2017.
 */

public class RotaTask extends AsyncTaskLoader<List<LatLng>> {

    List<LatLng> mRota;
    LatLng mOrigem;
    LatLng mDestino;

    public RotaTask(Context context,LatLng orig,LatLng dest){
        super(context);
        mOrigem = orig;
        mDestino = dest;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mRota == null){
            forceLoad();
        }else{
            deliverResult(mRota);
        }
    }

    @Override
    public List<LatLng> loadInBackground() {
        mRota = RotaHttp.carregarRota(mOrigem,mDestino);
        return  mRota;
    }



}
