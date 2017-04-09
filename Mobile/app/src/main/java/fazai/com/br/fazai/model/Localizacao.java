package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;


public class Localizacao {

    @SerializedName("latitude")
    public long latitude;
    @SerializedName("longitude")
    public long longitude;

    public Localizacao() {
    }

    public Localizacao(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
