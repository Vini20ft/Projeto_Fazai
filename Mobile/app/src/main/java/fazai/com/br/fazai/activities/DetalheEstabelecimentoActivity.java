package fazai.com.br.fazai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.fragments.CardapioFragment;
import fazai.com.br.fazai.fragments.DetalheEstabelecimentoFragment;
import fazai.com.br.fazai.interfaces.OnCardapioClick;
import fazai.com.br.fazai.model.Cardapio;

public class DetalheEstabelecimentoActivity extends AppCompatActivity implements OnCardapioClick {

    Toolbar toolbar;
    ViewPager mViewPager;
    TabLayout tabLayout;
    DetalheEstabelecimentoFragment detalheEstabelecimentoFragment;
    CardapioFragment cardapioFragment;
    SelectorPageAdapter selectorPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_estabelecimento);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        buildViewPager();
    }

    private void buildViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.container);

        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(selectorPageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onCardapioClick(Cardapio cardapio) {
        Intent it = new Intent(this, ItensCardapioActivity.class);
        it.putExtra("id_cardapio", cardapio.id);
        startActivity(it);
    }

    private class SelectorPageAdapter extends FragmentPagerAdapter {
        SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (detalheEstabelecimentoFragment == null) {
                        detalheEstabelecimentoFragment = new DetalheEstabelecimentoFragment();
                    }

                    return detalheEstabelecimentoFragment;
                case 1:
                default:
                    if (cardapioFragment == null) {
                        cardapioFragment = new CardapioFragment();
                    }

                    return cardapioFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Detalhes";
                case 1:
                default:
                    return "Cardápios";
            }
        }
    }
}
