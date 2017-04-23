package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.interfaces.OnEstabelecimentoClick;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.ui.adapter.EstabelecimentoAdapter;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>, AdapterView.OnItemClickListener, OnEstabelecimentoClick,
        SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.listEstabelecimentos)
    ListView mListEstabelecimentos;

    @BindView(R.id.swipeMain)
    SwipeRefreshLayout mSwipe;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private EstabelecimentoAdapter adapter;
    private List<Estabelecimento> mEstabelecimentoList;
    private LoaderManager mLoaderManager;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mListEstabelecimentos.setOnItemClickListener(this);
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        mSwipe.setColorSchemeResources(R.color.colorPrimary);
        mSwipe.setOnRefreshListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();


        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        VerifyCurrentUser();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_mapa) {
            Intent intent = new Intent(this, EstabelecimentosMapsActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_menu_principal) {

        }else if (id == R.id.nav_compartilhar) {

        }else if (id == R.id.nav_pedido) {

        }else if (id == R.id.nav_sobre) {


        } else if (id == R.id.nav_sair) {
            signOut();
                /*
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("senha", "0");
                editor.commit();
                */
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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


    @Override
    public Loader<List<Estabelecimento>> onCreateLoader(int id, Bundle args) {
        showProgress();
        return new EstabelecimentosTask(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, final List<Estabelecimento> data) {
        if (data != null) {
            mEstabelecimentoList = data;
            adapter = new EstabelecimentoAdapter(this, mEstabelecimentoList);
            adapter.notifyDataSetChanged();
            mListEstabelecimentos.setAdapter(adapter);
            mSwipe.setRefreshing(false);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Estabelecimento estabelecimento = (Estabelecimento) mListEstabelecimentos.getItemAtPosition(position);
        (this).onEstabelecimentoClick(estabelecimento);
    }

    @Override
    public void onEstabelecimentoClick(Estabelecimento estabelecimento) {
        Intent it = new Intent(this, DetalheEstabelecimentoActivity.class);
        it.putExtra("id", estabelecimento.id);
        startActivity(it);
    }

    private void showProgress() {
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void onRefresh() {
        mLoaderManager.restartLoader(0, null, this);
    }
}
