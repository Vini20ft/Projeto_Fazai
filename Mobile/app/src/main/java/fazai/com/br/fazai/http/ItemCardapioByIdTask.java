package fazai.com.br.fazai.http;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import fazai.com.br.fazai.http.parser.ItensCardapioParser;
import fazai.com.br.fazai.model.ItemCardapio;



public class ItemCardapioByIdTask extends AsyncTaskLoader<ItemCardapio> {
    private ItemCardapio mItemCardapio;
    private int mId;

    public ItemCardapioByIdTask(Context context, int id) {
        super(context);
        mId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mId <= 0) return;

        if (mItemCardapio == null) {
            forceLoad();
        } else {
            deliverResult(mItemCardapio);
        }
    }

    @Override
    public ItemCardapio loadInBackground() {
        try {
            mItemCardapio = ItensCardapioParser.searchByIdCardapio(/*id do cardapio -> mId*/);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mItemCardapio;
    }
}

