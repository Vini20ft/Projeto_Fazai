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
import android.support.v4.widget.SwipeRefreshLayout;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.ItemCardapioTask;
import fazai.com.br.fazai.interfaces.OnItemCardapioClick;
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.ui.adapter.ItemCardapioAdapter;

public class ItensCardapioActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener, OnItemCardapioClick,
        LoaderManager.LoaderCallbacks<List<ItemCardapio>>, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.listItemCardapio)
    ListView mListItemCardapio;

    @BindView(R.id.swipeItemCardapio)
    SwipeRefreshLayout mSwipe;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private ItemCardapioAdapter mAdapter;
    private LoaderManager mLoaderManager;
    private List<ItemCardapio> mItemCardapioList;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_cardapio);
        ButterKnife.bind(this);
        initToolBar();
        verificaConexao();

        mListItemCardapio.setOnItemClickListener(this);
        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        mSwipe.setColorSchemeResources(R.color.colorPrimary);
        mSwipe.setOnRefreshListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        VerifyCurrentUser();
    }

    public void initToolBar() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void verificaConexao() {
        ConnectivityManager conectivtyManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
        } else {
            Toast.makeText(getApplicationContext(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showProgress() {
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }

    private void signOutFacebook() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    private void VerifyCurrentUser() {
        if (AccessToken.getCurrentAccessToken() == null && (mGoogleApiClient == null && !mGoogleApiClient.isConnected())) {
            Toast.makeText(getApplicationContext(), "O usuário está deslogado!", Toast.LENGTH_SHORT).show();
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

    private void signOutGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

    @Override
    public Loader<List<ItemCardapio>> onCreateLoader(int id, Bundle args) {
        showProgress();
        //int idEstabelecimento = args != null ? args.getInt("idEstabelecimento") : null;
        return new ItemCardapioTask(getApplicationContext(), /*idEstabelecimento*/ 1);
    }

    @Override
    public void onLoadFinished(Loader<List<ItemCardapio>> loader, List<ItemCardapio> data) {
        if (data != null) {
            mItemCardapioList = data;
            mAdapter = new ItemCardapioAdapter(this, mItemCardapioList);
            mAdapter.notifyDataSetChanged();
            mListItemCardapio.setAdapter(mAdapter);
            mSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ItemCardapio>> loader) {

    }

    @Override
    public void onRefresh() {
        mLoaderManager.restartLoader(0, null, this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ItemCardapio itemCardapio = (ItemCardapio) mListItemCardapio.getItemAtPosition(i);
        this.onItemCardapioClick(itemCardapio);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onItemCardapioClick(ItemCardapio itemCardapio) {
        /*Intent it = new Intent(this, ItensCardapioCompraActivity.class);
        it.putExtra("id_item_cardapio", itemCardapio.id);
        startActivity(it);*/

        Toast.makeText(getApplicationContext(), "Abrir tela de compra do item",
                Toast.LENGTH_LONG).show();
    }
}
