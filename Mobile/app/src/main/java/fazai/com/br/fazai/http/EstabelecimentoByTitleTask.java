package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.model.Estabelecimento;


public class EstabelecimentoByTitleTask extends AsyncTaskLoader<List<Estabelecimento>> {
    private List<Estabelecimento> mEstabelecimentos;
    private String mQuery;

    public EstabelecimentoByTitleTask(Context context, String q) {
        super(context);
        mQuery = q;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mQuery == null) return;

        if (mEstabelecimentos == null) {
            forceLoad();
        } else {
            deliverResult(mEstabelecimentos);
        }
    }

    @Override
    public List<Estabelecimento> loadInBackground() {
        try {
            mEstabelecimentos = EstabelecimentoParser.searchByTitle(mQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEstabelecimentos;
    }
}
