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
import fazai.com.br.fazai.model.VerifyConnection;

public class SobreFragment extends Fragment{

    public CollapsingToolbarLayout appBarLayout;
    private VerifyConnection verifyConnection;


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

        verifyConnection = new VerifyConnection(getActivity());
        verifyConnection.verificaConexao();

        if (!verifyConnection.verificaConexao()) {
            Toast.makeText(getActivity(), R.string.falha_na_conexão_com_a_internet,
                    Toast.LENGTH_LONG).show();
        }

        return view;
    }

    public static SobreFragment newInstance(String sobreId) {
        Bundle bundle = new Bundle();
        bundle.putString("id_sobre", sobreId);
        SobreFragment fragment = new SobreFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}