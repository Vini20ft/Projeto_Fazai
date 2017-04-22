package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


public class Localizacao {

    @SerializedName("latitude")
   // public long latitude;
     public double latitude;
    @SerializedName("longitude")
   // public long longitude;
    public double longitude;

    public Localizacao() {
    }

    public Localizacao(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
