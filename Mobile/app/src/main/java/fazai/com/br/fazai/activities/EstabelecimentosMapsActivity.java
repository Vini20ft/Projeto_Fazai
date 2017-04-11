package fazai.com.br.fazai.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.PopupAdapter;

public class EstabelecimentosMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>> {

    private GoogleMap mMap;
    private Marker marker;
    private LoaderManager mLoaderManager;
    private boolean needsInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimentos_maps);
        //Implementação temporária do Menu na tela de maps
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

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
            CameraUpdate center =
                    CameraUpdateFactory.newLatLng(new LatLng(40.76793169992044,
                            -73.98180484771729));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

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
        markerOptions.position(latLng).title(title)
                                      .snippet("Aqui!!")
                                      .draggable(true);
        Bitmap markerBitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(markerBitmap);
        Drawable shape = ResourcesCompat.getDrawable(getResources(), R.drawable.truck, null);
        shape.setBounds(0, 0, markerBitmap.getWidth(), markerBitmap.getHeight());
        shape.draw(canvas);


        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(markerBitmap));
        marker = mMap.addMarker(markerOptions);
    }

    //OnClick botão busca FoodTruck Maps
    public void pesquisarFoodTruck(View view)  {
        EditText localizacaoFoodTruck = (EditText)findViewById(R.id.editTextFoodTruck);
        String foodTruckInformado = localizacaoFoodTruck.getText().toString();
        List<Address> enderecosList = null;

        if(localizacaoFoodTruck != null || !localizacaoFoodTruck.equals("")){
            if(foodTruckInformado != null && !foodTruckInformado.equals("")){
                //Mudar depois para consumir o webservice e pesquisar a LAt e Long dos objetos do
                //json com o nome do foodtruck informado
                Geocoder geocoder = new Geocoder(this);
                try {
                    enderecosList =  geocoder.getFromLocationName(foodTruckInformado,1);
                } catch (IOException e) {
                    //teste
                    e.printStackTrace();
                }
                Address endereco = enderecosList.get(0);
                LatLng latitude = new LatLng(endereco.getLatitude(),endereco.getLongitude());
                customAddMarker(latitude,"FoodTruck","Aqui!!");
               // mMap.addMarker(new MarkerOptions().position(latitude).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latitude));
            }else{
                Toast.makeText(this, R.string.msgCampoPesquisaFoodVazio, Toast.LENGTH_SHORT).show();
            }
        }

    }


}