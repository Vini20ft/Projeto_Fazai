package fazai.com.br.fazai.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.http.RotaTask;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.util.MapStateManager;

public class EstabelecimentosMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int REQUEST_ERRO_PLAY_SERVICES = 1;
    private static final int LOADER_ROTA = 2;
    private static final String EXTRA_ROTA = "rota";
    private static final String EXTRA_MAP = "map";

    private ArrayList<LatLng> mRota;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LoaderManager mLoaderManager;
    private boolean needsInit = false;
    private LocationRequest mLocationRequest;
    private LatLng mOrigem;
    private LatLng mDestino;

    private Marker marker;
    private List<Estabelecimento> dadosEstabelecimentos;
    private Polyline polylineFinal;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimentos_maps);

        if (savedInstanceState == null) {
            needsInit = true;
        }
        setUpMap();

        if (!verificaConexao()) {
            Toast.makeText(getApplicationContext(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        MapStateManager mgr = new MapStateManager(this);
        mgr.saveMapState(mMap);
        // Toast.makeText(this,"Estado do mapa foi salvo ?  PAUSE",Toast.LENGTH_LONG);

    }



    @Override
    protected void onPause() {
        super.onPause();
        MapStateManager mgr = new MapStateManager(this);
        mgr.saveMapState(mMap);
        // Toast.makeText(this,"Estado do mapa foi salvo ?  PAUSE",Toast.LENGTH_LONG);
    }

    @Override
    protected  void onResume(){
        super.onResume();
        setUpMap();
    }

    //Rotas
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_ROTA,mRota);
        getSupportFragmentManager().putFragment(outState,"com.google.android.gms.maps.SupportMapFragment",mapFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mRota = savedInstanceState.getParcelableArrayList(EXTRA_ROTA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ERRO_PLAY_SERVICES && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }
    }


    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentosTask(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, List<Estabelecimento> data) {
        if (data != null) {
            //modificando latitude e longitude em tempo de Execução apenas para de desenvolvimento
            if(data != null){
                dadosEstabelecimentos = data;
            }
            PreencherMarkers(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //modo salvo
        MapStateManager mgr = new MapStateManager(this);
        CameraPosition position = mgr.getSavedCameraPosition();
        if(position != null){
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            // Toast.makeText(this,"entrando no resume",Toast.LENGTH_LONG).show();
            mMap.moveCamera(update);
            mMap.setMapType(mgr.getSavedMapType());
        }


        //modo normal
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
            mMap = googleMap;
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                String name = marker.getTitle().toString();
                mDestino = marker.getPosition();
                desenharRota();
                carregarRota();
                if(mRota == null) {
                    mLoaderManager.restartLoader(LOADER_ROTA, null, mRotaCallBack);
                }
                return false;
            }
        });


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                double latitude = marker.getPosition().latitude;
                double longitude = marker.getPosition().longitude;
                String titulo = marker.getTitle();

                Estabelecimento estabelecimento = buscarEstabelecimento(latitude,longitude,titulo);

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EstabelecimentosMapsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_confirmacao_detalhe_maps,null);
                //Componentes do Dialog
                final TextView mTxtNomeEstabelecimento = (TextView) mView.findViewById(R.id.txtNomeEstabelecimentoDialogDetalheMap);
                final Button mBtnDetalheEstabelecimentoMap = (Button) mView.findViewById(R.id.btnDialogDetalheEstabelecimentoMap);
                final Button mBtnCloseDialog = (Button) mView.findViewById(R.id.btnCloseDialogDetalheMap);

                //Carregar nome do Estabelecimento no Dialog
                mTxtNomeEstabelecimento.setText(estabelecimento.nome);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.closeOptionsMenu();
                dialog.show();




                //Evento de Click do Dialog
                mBtnDetalheEstabelecimentoMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(EstabelecimentosMapsActivity.this,"Abrir Detalhe",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EstabelecimentosMapsActivity.this,DetalheEstabelecimentoActivity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                });



                mBtnCloseDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });




                if(estabelecimento != null){
                    Intent intent = new Intent();
                    //Chamar Detalhe Estabelecimento
                }

              /*  AlertDialog.Builder mBuilder = new AlertDialog.Builder(EstabelecimentosMapsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_login,null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();*/

                //Buscar
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Habilite a permissão de localização do  app em seu aparelho!!", Toast.LENGTH_SHORT).show();
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleApiClient.connect();
    }

    private void setUpMap() {
        if(mMap == null) {
            mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
            mapFragment.getMapAsync(this);
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

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

                double latitude = estabelecimento.endereco.localizacao.latitude;
                double longitude = estabelecimento.endereco.localizacao.longitude;

                customAddMarker(new LatLng(latitude, longitude), estabelecimento.nome, estabelecimento.endereco.rua,false);
            }
        }
    }

    public void customAddMarker(LatLng latLng, String title, String snippet,boolean isSearch) {
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
        if(isSearch){
            marker.showInfoWindow();

        }
        /*mMarker = latLng;*/

    }

    //OnClick botão busca FoodTruck Maps
    public void pesquisarFoodTruck(View view) {
        EditText localizacaoFoodTruck = (EditText) findViewById(R.id.editTextFoodTruck);
        String foodTruckInformado = localizacaoFoodTruck.getText().toString();
        if(!foodTruckInformado.trim().equals("") && foodTruckInformado != null ) {
            Estabelecimento estabelecimento = this.getEstabelecimentoFoodTruckPorNome(foodTruckInformado);
            if(estabelecimento != null){
                LatLng posicao = new LatLng(estabelecimento.endereco.localizacao.latitude,estabelecimento.endereco.localizacao.longitude);
                //Apagando rota anterior
                if(polylineFinal != null){
                    polylineFinal.remove();
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicao,17.0f));
                customAddMarker(posicao, estabelecimento.nome, estabelecimento.endereco.rua,true);

            }
        }else{
            Toast.makeText(this, R.string.msgCampoPesquisaFoodVazio, Toast.LENGTH_SHORT).show();
        }

     /*   List<Address> enderecosList = null;

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
        }*/

    }


    @Override
    public void onConnected(Bundle bundle) {
        obterUltimaLocalizacao();
        if(estaCarregando(LOADER_ROTA)&& mRota == null){
            mLoaderManager.initLoader(LOADER_ROTA, null, mRotaCallBack);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, REQUEST_ERRO_PLAY_SERVICES);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private void obterUltimaLocalizacao() {
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
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(location != null){
            mOrigem = new LatLng(location.getLatitude(),location.getLongitude());
            atualizarMapa();
        }
    }

    private void atualizarMapa(){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mOrigem,17.0f));
        mMap.clear();

        mMap.addMarker(new MarkerOptions()
                .position(mOrigem)
                .title("Local atual"));


    }

    private void desenharRota(){
        if(mRota != null && mRota.size() > 0){

            if(polylineFinal != null){   polylineFinal.remove();}

            PolylineOptions polylineOptions = new PolylineOptions()
                    .addAll(mRota)
                    .width(5)
                    .color(Color.RED)
                    .visible(true);
            polylineFinal = mMap.addPolyline(polylineOptions);

        }
    }


    private void carregarRota(){
        mRota = null;
        mLoaderManager.initLoader(LOADER_ROTA, null, mRotaCallBack);
    }

    LoaderManager.LoaderCallbacks<List<LatLng>> mRotaCallBack = new LoaderManager.LoaderCallbacks<List<LatLng>>(){

        @Override
        public Loader<List<LatLng>> onCreateLoader(int id, Bundle args) {
            return new RotaTask(EstabelecimentosMapsActivity.this,mOrigem,mDestino);
        }

        @Override
        public void onLoadFinished(final Loader<List<LatLng>> listLoader,final List<LatLng> latLngs) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    mRota = new ArrayList<LatLng>(latLngs);
                    desenharRota();
                }
            });
        }

        @Override
        public void onLoaderReset(Loader<List<LatLng>> loader) {}

    };


    private boolean estaCarregando(int id){
        Loader<?> loader = mLoaderManager.getLoader(id);
        if(loader != null && loader.isStarted()){
            return true;
        }
        return false;
    }


    //Metodo de consulta de estabelecimentos por longitude e latitude
    private Estabelecimento buscarEstabelecimento(double latitude, double longitude , String nomeFoodTruck) {
        Estabelecimento estabelecimento = new Estabelecimento();
        if (dadosEstabelecimentos != null) {
            for (Estabelecimento e : dadosEstabelecimentos) {
                if (e.endereco.localizacao != null && e.nome != null) {
                    if (e.endereco.localizacao.latitude == latitude &&
                            e.endereco.localizacao.longitude == longitude &&
                            e.nome.trim().equals(nomeFoodTruck.trim())) {
                        estabelecimento = e;

                    }
                }
            }
        }
        return estabelecimento;
    }

    private Estabelecimento getEstabelecimentoFoodTruckPorNome(String nomeFoodTruck){
        Estabelecimento estabelecimento = new Estabelecimento();
        if(dadosEstabelecimentos != null){
            for (Estabelecimento e : dadosEstabelecimentos){
                if(e.nome.trim().toUpperCase().equals(nomeFoodTruck.trim().toUpperCase())){
                    estabelecimento = e;
                }
            }
        }
        return  estabelecimento;
    }




}