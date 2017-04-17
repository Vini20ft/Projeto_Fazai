package fazai.com.br.fazai.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
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
import fazai.com.br.fazai.http.EstabelecimentoByIdTask;
import fazai.com.br.fazai.model.Estabelecimento;

public class DetalheEstabelecimentoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Estabelecimento> {

    LoaderManager mLoaderManager;

    @BindView(R.id.nomeEstabelecimento)
    TextView nomeEstabelecimento;

    @Nullable
    @BindView(R.id.imageEstabelecimentoDetalhe)
    ImageView imageEstabelecimento;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    private Unbinder unbinder;

    Estabelecimento estabelecimento;

    public DetalheEstabelecimentoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalhe_estabelecimento, container, false);
        unbinder = ButterKnife.bind(this, view);
        verificaConexao();

        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(0, null, this);

        return view;
    }

    @Override
    public Loader<Estabelecimento> onCreateLoader(int id, Bundle args) {
        return new EstabelecimentoByIdTask(getActivity(), 1);
    }

    @Override
    public void onLoadFinished(Loader<Estabelecimento> loader, Estabelecimento data) {
        if (data != null) {
            estabelecimento = data;
            setViewDetalheEstabelecimento(estabelecimento);
        } else {
            Toast.makeText(getActivity(), "Erro ao carregar informações.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Estabelecimento> loader) {

    }

    public void setViewDetalheEstabelecimento(Estabelecimento estabelecimento) {
        nomeEstabelecimento.setText(estabelecimento.nome);
        Glide.with(getActivity()).load(estabelecimento.foto).into(imageEstabelecimento);
        ratingBar.setNumStars(estabelecimento.rating);
    }

    public void verificaConexao() {
        ConnectivityManager conectivtyManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
        } else {
            Toast.makeText(getActivity(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
