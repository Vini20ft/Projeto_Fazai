package fazai.com.br.fazai.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.model.Estabelecimento;

public class EstabelecimentosMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener {


  /*  private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Marker marker;
    private LoaderManager mLoaderManager;
    private boolean needsInit = false;*/

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    LatLng latLng;
    private Marker marker;
    GoogleMap mMap;
    SupportMapFragment mFragment;
    Marker currLocationMarker;


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
        //GOOGLE MAPS


        setUpMap();


      /*  if (savedInstanceState == null) {
            needsInit = true;
        }

        if (!verificaConexao()) {
            Toast.makeText(getApplicationContext(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);*/

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
      /*  if (googleMap != null) {
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
        });*/
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        buildGoogleApiClient();

        mGoogleApiClient.connect();




    }

    private void setUpMap() {
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
    }
/*
    private void setUpMapIfNeeded(){
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
    }*/


  /*  public  boolean verificaConexao() {
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
    }*/

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
    public void pesquisarFoodTruck(View view) {
        EditText localizacaoFoodTruck = (EditText) findViewById(R.id.editTextFoodTruck);
        String foodTruckInformado = localizacaoFoodTruck.getText().toString();
        List<Address> enderecosList = null;

        if (localizacaoFoodTruck != null || !localizacaoFoodTruck.equals("")) {
            if (foodTruckInformado != null && !foodTruckInformado.equals("")) {
                //Mudar depois para consumir o webservice e pesquisar a LAt e Long dos objetos do
                //json com o nome do foodtruck informado
                Geocoder geocoder = new Geocoder(this);
                try {
                    enderecosList = geocoder.getFromLocationName(foodTruckInformado, 1);
                } catch (IOException e) {
                    //teste
                    e.printStackTrace();
                }
                Address endereco = enderecosList.get(0);
                LatLng latitude = new LatLng(endereco.getLatitude(), endereco.getLongitude());
                customAddMarker(latitude, "FoodTruck", "Aqui!!");
                // mMap.addMarker(new MarkerOptions().position(latitude).title("Marker"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latitude));
            } else {
                Toast.makeText(this, R.string.msgCampoPesquisaFoodVazio, Toast.LENGTH_SHORT).show();
            }
        }

    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            currLocationMarker = mMap.addMarker(markerOptions);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        centerMyObjectLocation();
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

       // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);



    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"onConnectionSuspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this,"onConnectionFailed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

       //place marker at current position
      mMap.clear();
       if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        currLocationMarker = mMap
                .addMarker(markerOptions);

        Toast.makeText(this,"Location Changed",Toast.LENGTH_SHORT).show();

        //zoom to current position:
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));

        //If you only need one location, unregister the listener
        //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

    }
   //centraliza e aplica zoom na localização atual
    private void centerMyObjectLocation(){
        Location location = mMap.getMyLocation();
        if(location != null){
            latLng = new LatLng(location.getLatitude(),
                    location.getLongitude());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17.0f));

    }


}