package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.model.Estabelecimento;
import fazai.com.br.fazai.model.Localizacao;


public class EstabelecimentosByLocationTask extends AsyncTaskLoader<Estabelecimento> {
    private Estabelecimento mEstabelecimento;
    private Localizacao mLocalizacao;

    public EstabelecimentosByLocationTask(Context context, Localizacao localizacao) {
        super(context);
        this.mLocalizacao = localizacao;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mLocalizacao == null) return;

        if (mEstabelecimento == null) {
            forceLoad();
        } else {
            deliverResult(mEstabelecimento);
        }
    }

    @Override
    public Estabelecimento loadInBackground() {
        try {
            mEstabelecimento = EstabelecimentoParser.searchByLocation(mLocalizacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEstabelecimento;
    }
}
