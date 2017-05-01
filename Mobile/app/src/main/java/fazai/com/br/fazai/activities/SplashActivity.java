package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.model.Constantes;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
            }
        }, Constantes.TEMPO_SPLASH);
    }
}
