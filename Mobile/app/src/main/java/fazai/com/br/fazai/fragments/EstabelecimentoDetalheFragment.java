package fazai.com.br.fazai.fragments;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fazai.com.br.fazai.activities.AvaliarEstabelecimentoActivity;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.activities.ItensCardapioActivity;
import fazai.com.br.fazai.http.EstabelecimentoByIdTask;
import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.model.VerifyConnection;


public class EstabelecimentoDetalheFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<Estabelecimento>{

    CollapsingToolbarLayout appBarLayout;

    @Nullable @BindView(R.id.image_foto)
    ImageView mImageEstabelecimento;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.btn_avaliar_estabelecimento)
    Button mAvaliar_estabelecimento;

    @BindView(R.id.btn_ligar)
    Button mbtnLigar;

    @BindView(R.id.txtBairro)
    TextView mTxtBairro;

    @BindView(R.id.txtCidade)
    TextView mTxtCidade;

    @BindView(R.id.txtEstado)
    TextView mTxtEstado;

    @BindView(R.id.txtNumero)
    TextView mTxtNumero;

    @BindView(R.id.txtRua)
    TextView mTxtRua;

    private String mLigar;
    private LoaderManager mLoaderManager;
    private Estabelecimento mEstabelecimento;
    private Unbinder unbinder;
    private ShareActionProvider mShareActionProvider;
    private VerifyConnection verifyConnection;

    public EstabelecimentoDetalheFragment() {

    }

    public static EstabelecimentoDetalheFragment newInstance(String estabelecimentoId) {
        Bundle bundle = new Bundle();
        bundle.putString("id_estabelecimento", estabelecimentoId);
        EstabelecimentoDetalheFragment fragment = new EstabelecimentoDetalheFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
    // Compartinhar no Menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detalhe_estabelecimento, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(Intent.EXTRA_TEXT, "getNomeFoodTruck");
        mShareActionProvider.setShareIntent(it);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_estabelecimento_detalhe, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), R.string.abrir_cardapio, Toast.LENGTH_SHORT).show();
            }
        });

        verifyConnection = new VerifyConnection(getActivity());
        verifyConnection.verificaConexao();

        /* ------ABRIR ACTIVITY DE CARDAPIOS APENAS PARA TESTE ------- */
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ItensCardapioActivity.class);
                startActivity(intent);
            }
        });

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(1, getArguments(), this);

        return view;
    }

    public void updateUI(Estabelecimento data) {
        appBarLayout.setTitle(data.nome);
        mLigar = (data.telefone);
        mTxtBairro.setText(data.endereco.bairro);
        mTxtCidade.setText(data.endereco.cidade);
        mTxtEstado.setText(data.endereco.estado);
        mTxtNumero.setText(data.endereco.numero);
        mTxtRua.setText(data.endereco.rua);

        if (mImageEstabelecimento != null)
            Glide.with(getActivity()).load(data.foto).into(mImageEstabelecimento);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Loader<Estabelecimento> onCreateLoader(int id, Bundle args) {
        //int estabelecimentoId = args.getInt("id_estabelecimento");
        return new EstabelecimentoByIdTask(getActivity(), 1);
    }

    @Override
    public void onLoadFinished(Loader<Estabelecimento> loader, Estabelecimento data) {
        if (data != null) {
            mEstabelecimento = data;
            updateUI(mEstabelecimento);
        } else {
            Toast.makeText(getActivity(), R.string.erro_ao_carregar_informações, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Estabelecimento> loader) {

    }

    @OnClick({R.id.btn_ligar, R.id.btn_avaliar_estabelecimento})
    @Override
    public void onClick(View v) {
        Uri uri;
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_ligar:

                uri = Uri.parse("tel:" + mLigar);
                intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.setData(Uri.parse(String.valueOf(uri)));
                startActivity(intent);
                break;

            case R.id.btn_avaliar_estabelecimento:

                intent = new Intent(getActivity(), AvaliarEstabelecimentoActivity.class);
                startActivity(intent);
                break;
        }
    }
}