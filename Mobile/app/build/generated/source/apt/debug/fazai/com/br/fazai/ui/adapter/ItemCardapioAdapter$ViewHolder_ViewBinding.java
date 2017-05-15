// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemCardapioAdapter$ViewHolder_ViewBinding<T extends ItemCardapioAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ItemCardapioAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imgFoto = Utils.findRequiredViewAsType(source, R.id.item_foto_itens_cardapio, "field 'imgFoto'", ImageView.class);
    target.txtNome = Utils.findRequiredViewAsType(source, R.id.item_nome_prato, "field 'txtNome'", TextView.class);
    target.txtDescricao = Utils.findRequiredViewAsType(source, R.id.txtValorItemCardapio, "field 'txtDescricao'", TextView.class);
    target.txtValor = Utils.findRequiredViewAsType(source, R.id.item_valor_itens_cardapio, "field 'txtValor'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgFoto = null;
    target.txtNome = null;
    target.txtDescricao = null;
    target.txtValor = null;

    this.target = null;
  }
}
