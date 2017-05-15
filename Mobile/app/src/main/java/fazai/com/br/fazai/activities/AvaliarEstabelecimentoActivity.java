package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import fazai.com.br.fazai.R;

public class AvaliarEstabelecimentoActivity extends AppCompatActivity {

    private RatingBar ratingBar;

    String txtValorAvaliacao;

    @BindView(R.id.btn_avaliar)
    Button mAvaliar;

    @OnClick(R.id.btn_avaliar)

    public void onClick(View v) {

        Intent intent = new Intent(this, DetalheEstabelecimentoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar_estabelecimento);
        addListenerOnRatingBar();
        addListenerOnButton();
    }

    public void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingEstabelecimento);

        //se o valor de avaliação mudar,
        //exiba o valor de avaliação atual no resultado (textview) automaticamente
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
               txtValorAvaliacao = String.valueOf(avaliacao);
            }
        });
    }

    public void addListenerOnButton() {
        ratingBar = (RatingBar) findViewById(R.id.ratingEstabelecimento);

        //se o botão for clicado, exiba o valor de avaliação corrente.
        mAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AvaliarEstabelecimentoActivity.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
