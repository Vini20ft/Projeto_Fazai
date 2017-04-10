package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import fazai.com.br.fazai.http.EstabelecimentosTask;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.ui.adapter.EstabelecimentoAdapter;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<List<Estabelecimento>>{

    private GoogleApiClient googleApiClient;

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private EstabelecimentoAdapter adapter;
    private List<Estabelecimento> mEstabelecimentoList;

    private LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, EstabelecimentosMapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id. drawer_layout);
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
        return new EstabelecimentosTask(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Estabelecimento>> loader, final List<Estabelecimento> data) {
        if (data != null) {
            mEstabelecimentoList = data;
            adapter = new EstabelecimentoAdapter(this, mEstabelecimentoList);
            recyclerView.setAdapter(adapter);

            /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data.size()-1){
                        EstabelecimentosTask estabelecimentosTask = new EstabelecimentosTask(getApplicationContext());
                        estabelecimentosTask.loadInBackground();
                    }
                }
            });*/
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Estabelecimento>> loader) {

    }
}
