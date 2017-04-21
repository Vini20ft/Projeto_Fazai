package fazai.com.br.fazai.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.model.Cardapio;


public class CardapioAdapter extends ArrayAdapter<Cardapio> {

    public CardapioAdapter(Context context, List<Cardapio> cardapioList) {
        super(context, 0, cardapioList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cardapio cardapio = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cardapio, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (cardapio != null) {
            Glide.with(getContext()).load(cardapio.foto).into(viewHolder.imgFoto);
            viewHolder.txtTitulo.setText(cardapio.descricao);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_foto_cardapio)
        ImageView imgFoto;
        @BindView(R.id.item_cardapio_titulo)
        TextView txtTitulo;

        ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}
