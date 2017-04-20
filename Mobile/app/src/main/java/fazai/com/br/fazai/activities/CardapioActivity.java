package fazai.com.br.fazai.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.CardapiosTask;
import fazai.com.br.fazai.interfaces.OnCardapioClick;
import fazai.com.br.fazai.model.Cardapio;
import fazai.com.br.fazai.ui.adapter.CardapioAdapter;

public class CardapioActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener, OnCardapioClick,
        LoaderManager.LoaderCallbacks<List<Cardapio>>,
        AdapterView.OnItemClickListener {

    ListView listViewCardapio;

    protected CardapioAdapter adapter;
    LoaderManager mLoaderManager;

    List<Cardapio> mCardapioList;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!verificaConexao()) {
            Toast.makeText(getApplicationContext(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        listViewCardapio = (ListView) findViewById(R.id.listCardapio);
        listViewCardapio.setOnItemClickListener(this);
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        VerifyCurrentUser();
    }

    @Override
    public void onCardapioClick(Cardapio cardapio) {
        /*Intent it = new Intent(this, ItensCardapioActivity.class);
        it.putExtra("id_cardapio", cardapio.id);
        startActivity(it);*/

        Toast.makeText(getApplicationContext(), "Abrir tela de itens.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public Loader<List<Cardapio>> onCreateLoader(int id, Bundle args) {
        //int idEstabelecimento = args != null ? args.getInt("idEstabelecimento") : null;
        return new CardapiosTask(getApplicationContext(), 1);
    }

    @Override
    public void onLoadFinished(Loader<List<Cardapio>> loader, List<Cardapio> data) {
        if (data != null) {
            mCardapioList = data;
            adapter = new CardapioAdapter(this, mCardapioList);
            listViewCardapio.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Cardapio>> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cardapio cardapio = (Cardapio) listViewCardapio.getItemAtPosition(i);
        this.onCardapioClick(cardapio);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_mapa) {
            Intent intent = new Intent(this, EstabelecimentosMapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sair) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id. drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signOut() {
        if (AccessToken.getCurrentAccessToken() != null) {
            signOutFacebook();
        } else {
            signOutGoogle();
        }
    }

    private void signOutFacebook() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void VerifyCurrentUser() {
        if (AccessToken.getCurrentAccessToken() == null && (googleApiClient == null && !googleApiClient.isConnected())) {
            Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_SHORT).show();
            goLoginScreen();
        }
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void signOutGoogle() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            goLoginScreen();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.error_logout, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
