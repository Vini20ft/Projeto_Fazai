package fazai.com.br.fazai.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.model.Estabelecimento;

public class SobreFragment extends Fragment{
    CollapsingToolbarLayout appBarLayout;
    private LoaderManager mLoaderManager;

    public SobreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sobre, container, false);


        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_sobre);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout_sobre);

        if (!verificaConexao()) {
            Toast.makeText(getActivity(), "Falha na conexão com a internet.",
                    Toast.LENGTH_LONG).show();
        }

        return view;
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

    public static SobreFragment newInstance(String sobreId) {
        Bundle bundle = new Bundle();
        bundle.putString("id_sobre", sobreId);
        SobreFragment fragment = new SobreFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}