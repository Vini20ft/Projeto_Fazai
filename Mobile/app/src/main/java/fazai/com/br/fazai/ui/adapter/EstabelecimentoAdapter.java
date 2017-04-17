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
import fazai.com.br.fazai.model.Estabelecimento;

public class EstabelecimentoAdapter extends ArrayAdapter<Estabelecimento> {
    public EstabelecimentoAdapter(Context context, List<Estabelecimento> estabelecimentoList) {
        super(context, 0, estabelecimentoList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Estabelecimento estabelecimento = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_estab, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (estabelecimento != null) {
            Glide.with(getContext()).load(estabelecimento.foto).into(viewHolder.imgFoto);
            viewHolder.txtTitulo.setText(estabelecimento.nome);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_estab_foto) ImageView imgFoto;
        @BindView(R.id.item_estab_titulo) TextView txtTitulo;

        ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}