package fazai.com.br.fazai.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.fragments.EstabelecimentoDetalheFragment;
import fazai.com.br.fazai.fragments.ItemCardapioDetalheFragment;
import fazai.com.br.fazai.model.Constantes;

public class DetalheItemCardapioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_item_cardapio);

        //carregando fragment em tempo de execução
        String cardapioId = getIntent().getStringExtra(Constantes.CARDAPIO_ID);
        ItemCardapioDetalheFragment fragment = ItemCardapioDetalheFragment.newInstance(cardapioId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_item_cardapio_detalhe, fragment, "detalhe").commit();
    }
}
