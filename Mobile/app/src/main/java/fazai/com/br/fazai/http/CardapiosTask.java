package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.http.parser.CardapioParser;
import fazai.com.br.fazai.model.Cardapio;

/**
 * Created by Caio Ernandes on 20/04/2017.
 */

public class CardapiosTask extends AsyncTaskLoader<List<Cardapio>> {
    private List<Cardapio> mCardapios;
    int idEstabelecimento;

    public CardapiosTask(Context context, int idEstabelecimento) {
        super(context);
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (idEstabelecimento <= 0) return;

        if (mCardapios == null) {
            forceLoad();
        } else {
            deliverResult(mCardapios);
        }
    }

    @Override
    public List<Cardapio> loadInBackground() {
        try {
            mCardapios = CardapioParser.searchAllById(/*idEstabelecimento*/);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCardapios;
    }
}