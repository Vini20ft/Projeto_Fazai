package fazai.com.br.fazai.http;

/**
 * Created by Ricardo on 20/04/2017.
 */
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RotaHttp {

    public static List<LatLng> carregarRota(LatLng oriLng,LatLng destLatLng){

        List<LatLng> posicoes = new ArrayList<>();
        try{
           String urlRota = String.format(Locale.US,"http://maps.googleapis.com/maps/api/directions/json?"+
                                          "origin=%f,%f&destination=%f,%f&" +
                                          "sensor=true&mode=driving",oriLng.latitude,oriLng.longitude,
                                           destLatLng.latitude,destLatLng.longitude);
            URL url = new URL(urlRota);
            String result = bytesParaString(url.openConnection().getInputStream());

            JSONObject json = new JSONObject(result);
            JSONObject jsonRoute = json.getJSONArray("routes").getJSONObject(0);
            JSONObject leg = jsonRoute.getJSONArray("legs").getJSONObject(0);

            JSONArray steps = leg.getJSONArray("steps");
            final int numSteps = steps.length();

            JSONObject step;
            for (int i=0;i< numSteps;i++){
                step = steps.getJSONObject(i);
                String pontos =step.getJSONObject("polyline").getString("points");
                posicoes.addAll(PolyUtil.decode(pontos));
            }

        }catch (Exception e){
           e.printStackTrace();
        }
        return posicoes;
    }

    private static String bytesParaString(InputStream inputStream) throws IOException {
          byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int byteslidos;
        while ((byteslidos = inputStream.read(buffer)) != -1){
            bufferzao.write(buffer,0,byteslidos);
        }
        return new String(bufferzao.toByteArray(),"UTF-8");

    }


}
