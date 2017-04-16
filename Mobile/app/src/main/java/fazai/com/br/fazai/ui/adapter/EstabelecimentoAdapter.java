package fazai.com.br.fazai.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fazai.com.br.fazai.R;
import fazai.com.br.fazai.interfaces.EstabelecimentoClickListener;
import fazai.com.br.fazai.model.Estabelecimento;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolder> {

    private Context mContext;
    private List<Estabelecimento> mEstabelecimentoList;
    private static EstabelecimentoClickListener itemListener;

    public EstabelecimentoAdapter(Context context, List<Estabelecimento> estabelecimentoList) {
        this.mContext = context;
        this.mEstabelecimentoList = estabelecimentoList;
    }

    public void setClickListener(EstabelecimentoClickListener itemListener) {
        EstabelecimentoAdapter.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.description.setText(mEstabelecimentoList.get(position).nome);
        Glide.with(mContext).load(mEstabelecimentoList.get(position).foto).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mEstabelecimentoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            description = (TextView) itemView.findViewById(R.id.description);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v)
        {
            if (itemListener != null) {
                itemListener.estabelecimentoClickListener(v, this.getLayoutPosition());
            }
        }
    }
}