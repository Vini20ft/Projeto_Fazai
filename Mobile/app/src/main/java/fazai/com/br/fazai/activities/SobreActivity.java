package fazai.com.br.fazai.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.fragments.EstabelecimentoDetalheFragment;
import fazai.com.br.fazai.fragments.SobreFragment;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        ButterKnife.bind(this);

        //carregando fragment em tempo de execução
        String sobreId = getIntent().getStringExtra("id_sobre");
        SobreFragment fragment = SobreFragment.newInstance(sobreId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_sobre, fragment, "sobre").commit();

    }
}
