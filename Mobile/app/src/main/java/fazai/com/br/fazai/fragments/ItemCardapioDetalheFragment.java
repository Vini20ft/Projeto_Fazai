package fazai.com.br.fazai.fragments;


import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.activities.CarrinhoComprasActivity;
import fazai.com.br.fazai.http.ItemCardapioByIdTask;
import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.model.ValorReal;
import fazai.com.br.fazai.model.VerifyConnection;


public class ItemCardapioDetalheFragment extends Fragment implements LoaderManager.LoaderCallbacks<ItemCardapio> {

    CollapsingToolbarLayout appBarLayout;

    @BindView(R.id.txtTituloItemCardapio)
    TextView mTituloItemCardapio;

    @BindView(R.id.txtDescricaoItemCardapio)
    TextView mDescricaoItemCardapio;

    @BindView(R.id.txtValorItemCardapio)
    TextView mValorItemCardapio;

    @Nullable
    @BindView(R.id.image_foto_item_cardapio)
    ImageView mImageItemCardapio;

    @BindView(R.id.fab_item_cardapio)
    FloatingActionButton mFab;

    private LoaderManager mLoaderManager;
    private ItemCardapio mItemCardapio;
    private Unbinder unbinder;
    private VerifyConnection verifyConnection;
    private ValorReal valorReal;

    public ItemCardapioDetalheFragment() {
        // Required empty public constructor
    }

    public static ItemCardapioDetalheFragment newInstance(String cardapioId) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.CARDAPIO_ID, cardapioId);
        ItemCardapioDetalheFragment fragment = new ItemCardapioDetalheFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_cardapio_detalhe, container, false);
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
                //Toast.makeText(getActivity(), R.string.abrir_tela_de_compra, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), CarrinhoComprasActivity.class));
            }
        });

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(1, getArguments(), this);

        verifyConnection = new VerifyConnection(getActivity());
        verifyConnection.verificaConexao();

        if (!verifyConnection.verificaConexao()) {
            Toast.makeText(getActivity(), R.string.falha_na_conexão_com_a_internet,
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Loader<ItemCardapio> onCreateLoader(int id, Bundle args) {
        int cardapioId = args.getInt(Constantes.CARDAPIO_ID);
        return new ItemCardapioByIdTask(getActivity(), /*cardapioId*/ 1);
    }

    @Override
    public void onLoadFinished(Loader<ItemCardapio> loader, ItemCardapio data) {
        if (data != null) {
            mItemCardapio = data;
            updateUI(mItemCardapio);
        } else {
            Toast.makeText(getActivity(), R.string.erro_ao_carregar_informações, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUI(ItemCardapio data) {

        valorReal = new ValorReal();

        appBarLayout.setTitle(data.nome);
        mTituloItemCardapio.setText(data.nome);
        mDescricaoItemCardapio.setText(data.descricao);
        mValorItemCardapio.setText(valorReal.ConverterValorReal(data.valor));

        if (mImageItemCardapio != null)
            Glide.with(getActivity()).load(data.imagem).into(mImageItemCardapio);
    }

    @Override
    public void onLoaderReset(Loader<ItemCardapio> loader) {

    }
}
