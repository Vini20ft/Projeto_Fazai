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
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.model.ValorReal;


public class ItemCardapioAdapter extends ArrayAdapter<ItemCardapio> {

    public ItemCardapioAdapter(Context context, List<ItemCardapio> itemCardapios) {
        super(context, 0, itemCardapios);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemCardapio itemCardapio = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_itens_cadapio, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ValorReal valorReal = new ValorReal();
        if (itemCardapio != null) {
            Glide.with(getContext()).load(itemCardapio.imagem).into(viewHolder.imgFoto);
            viewHolder.txtNome.setText(itemCardapio.nome);
            viewHolder.txtDescricao.setText(itemCardapio.descricao);
            viewHolder.txtValor.setText(valorReal.ConverterValorReal(itemCardapio.valor));
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_foto_itens_cardapio)
        ImageView imgFoto;
        @BindView(R.id.item_nome_prato)
        TextView txtNome;
        @BindView(R.id.txtValorItemCardapio)
        TextView txtDescricao;
        @BindView(R.id.item_valor_itens_cardapio)
        TextView txtValor;

        ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}

