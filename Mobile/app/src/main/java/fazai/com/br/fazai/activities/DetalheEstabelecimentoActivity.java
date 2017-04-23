package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.fragments.EstabelecimentoDetalheFragment;

public class DetalheEstabelecimentoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_estabelecimento);
        ButterKnife.bind(this);

        //carregando fragment em tempo de execução
        String estabelecimentoId = getIntent().getStringExtra("id_estabelecimento");
        EstabelecimentoDetalheFragment fragment = EstabelecimentoDetalheFragment.newInstance(estabelecimentoId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_produto_detalhe, fragment, "detalhe").commit();

    }
}
