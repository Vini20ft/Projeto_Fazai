package fazai.com.br.fazai.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.PopupAdapter;

public class EstabelecimentosMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Marker marker;
    private LoaderManager mLoaderManager;
    private boolean needsInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimentos_maps);
        setUpMap();

        if (savedInstanceState == null) {
            needsInit = true;
        }

        if (!verificaConexao()) {
            Toast.makeText(getApplicationContext(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentosTask(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, List<Estabelecimento> data) {
        if (data != null) {
            PreencherMarkers(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            new EstabelecimentosTask(getApplicationContext());
        }

        if (needsInit) {
            CameraUpdate center=
                    CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044,
                            -73.98180484771729));
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
        }

        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(getApplicationContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //mMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
        mMap.setOnInfoWindowClickListener(this);
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }


    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public void PreencherMarkers(List<Estabelecimento> estabelecimentoList) {

        if (estabelecimentoList != null) {
            for (Estabelecimento estabelecimento : estabelecimentoList) {

                long latitude = estabelecimento.endereco.localizacao.latitude;
                long longitude = estabelecimento.endereco.localizacao.longitude;

                customAddMarker(new LatLng(latitude, longitude), estabelecimento.nome, estabelecimento.endereco.rua);
            }
        }
    }

    public void customAddMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng).title(title).snippet(snippet).draggable(true);

        marker = mMap.addMarker(markerOptions);
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}