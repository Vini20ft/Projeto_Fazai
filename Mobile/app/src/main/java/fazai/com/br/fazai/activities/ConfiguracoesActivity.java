package fazai.com.br.fazai.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import fazai.com.br.fazai.R;

public class ConfiguracoesActivity extends AppCompatActivity {

    @BindView(R.id.imageBrazil)
    ImageButton mImageBrazil;

    @BindView(R.id.imageEua)
    ImageButton mImageEua;

    @BindView(R.id.imageSpain)
    ImageButton mImageSpain;

    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        ButterKnife.bind(this);

        mImageBrazil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog("pt");
            }
        });

        mImageEua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog("en");
            }
        });

        mImageSpain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog("es");
            }
        });
    }

    public void AlertDialog(final String lang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                loadLocale(lang);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void loadLocale(String lang) {
        String langPref = lang;
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                ConfiguracoesActivity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                ConfiguracoesActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        //editor.apply();
        editor.commit();
    }
}
