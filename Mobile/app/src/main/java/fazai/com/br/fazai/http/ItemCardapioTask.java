package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.http.parser.ItensCardapioParser;
import fazai.com.br.fazai.model.ItemCardapio;


public class ItemCardapioTask extends AsyncTaskLoader<List<ItemCardapio>> {
    private List<ItemCardapio> mItemCardapios;
    private int mId;

    public ItemCardapioTask(Context context, int id) {
        super(context);
        mId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mId <= 0) return;

        if (mItemCardapios == null || mItemCardapios.size() <= 0) {
            forceLoad();
        } else {
            deliverResult(mItemCardapios);
        }
    }

    @Override
    public List<ItemCardapio> loadInBackground() {
        try {
            mItemCardapios = ItensCardapioParser.searchAllByIdCardapio(/*id do cardapio -> mId*/);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mItemCardapios;
    }
}

