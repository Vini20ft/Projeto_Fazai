package fazai.com.br.fazai.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.activities.CardapioActivity;
import fazai.com.br.fazai.http.EstabelecimentoByIdTask;
import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.Estabelecimento;


public class EstabelecimentoDetalheFragment extends Fragment implements LoaderManager.LoaderCallbacks<Estabelecimento> {

    CollapsingToolbarLayout appBarLayout;

    @BindView(R.id.txtTituloEstabelecimento)
    TextView mTituloEstabelecimento;

    @BindView(R.id.ratingEstabelecimento)
    RatingBar mRatingBarEstabelecimento;

    @Nullable @BindView(R.id.image_foto)
    ImageView mImageEstabelecimento;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private LoaderManager mLoaderManager;
    private Estabelecimento mEstabelecimento;
    private Unbinder unbinder;

    public EstabelecimentoDetalheFragment() {

    }

    public static EstabelecimentoDetalheFragment newInstance(String estabelecimentoId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.ESTABELECIMENTO_ID, estabelecimentoId);
        EstabelecimentoDetalheFragment fragment = new EstabelecimentoDetalheFragment();
        fragment.setArguments(bundle);

        return fragment;
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
                Toast.makeText(getActivity(), "Abrir cardápio", Toast.LENGTH_SHORT).show();
            }
        });

        if (!verificaConexao()) {
            Toast.makeText(getActivity(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        /* ------ABRIR ACTIVITY DE CARDAPIOS APENAS PARA TESTE ------- */
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CardapioActivity.class);
                startActivity(intent);
            }
        });

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(1, getArguments(), this);

        return view;
    }

    public void updateUI(Estabelecimento data) {
        appBarLayout.setTitle(data.nome);
        mTituloEstabelecimento.setText(data.nome);
        mRatingBarEstabelecimento.setRating(data.rating);

        if (mImageEstabelecimento != null)
            Glide.with(getActivity()).load(data.foto).into(mImageEstabelecimento);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Loader<Estabelecimento> onCreateLoader(int id, Bundle args) {
        int estabelecimentoId = args.getInt(Constantes.ESTABELECIMENTO_ID);
        return new EstabelecimentoByIdTask(getActivity(), /*estabelecimentoId*/ 1);
    }

    @Override
    public void onLoadFinished(Loader<Estabelecimento> loader, Estabelecimento data) {
        if (data != null) {
            mEstabelecimento = data;
            updateUI(mEstabelecimento);
        } else {
            Toast.makeText(getActivity(), "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Estabelecimento> loader) {

    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
