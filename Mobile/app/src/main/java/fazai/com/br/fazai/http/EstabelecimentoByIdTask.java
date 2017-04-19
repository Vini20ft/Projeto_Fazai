package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.model.Estabelecimento;


public class EstabelecimentoByIdTask extends AsyncTaskLoader<Estabelecimento> {
    private Estabelecimento mEstabelecimento;
    private int mId;

    public EstabelecimentoByIdTask(Context context, int id) {
        super(context);
        mId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mId <= 0) return;

        if (mEstabelecimento == null) {
            forceLoad();
        } else {
            deliverResult(mEstabelecimento);
        }
    }

    @Override
    public Estabelecimento loadInBackground() {
        try {
            mEstabelecimento = EstabelecimentoParser.searchDetailById();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEstabelecimento;
    }
}
