package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.http.parser.EstabelecimentoParser;
import fazai.com.br.fazai.model.Estabelecimento;


public class EstabelecimentosTask extends AsyncTaskLoader<List<Estabelecimento>> {
    private List<Estabelecimento> mEstabelecimentos;

    public EstabelecimentosTask(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mEstabelecimentos == null) {
            forceLoad();
        } else {
            deliverResult(mEstabelecimentos);
        }
    }

    @Override
    public List<Estabelecimento> loadInBackground() {
        try {
            mEstabelecimentos = EstabelecimentoParser.searchAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEstabelecimentos;
    }
}
