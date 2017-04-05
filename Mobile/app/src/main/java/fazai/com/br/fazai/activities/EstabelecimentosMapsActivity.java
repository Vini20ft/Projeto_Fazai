package fazai.com.br.fazai.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.model.Estabelecimento;

public class EstabelecimentosMapsActivity extends FragmentActivity implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<List<Estabelecimento>> {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimentos_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney;
        MarkerOptions marker;

        for (int i = 0; i < 5; i++) {

            double latitude = -34 + i;
            double longitude = 151 + i;

            sydney = new LatLng(latitude, longitude);
            marker = new MarkerOptions().position(sydney).title("Hello Maps " + i);

            mMap.addMarker(marker);
            mMap.addMarker(marker.position(sydney).title("Marker in Sydney" + i));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }

    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, List<Estabelecimento> data) {
        if (data != null) {

        }
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }
}
