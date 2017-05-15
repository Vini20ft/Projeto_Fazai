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

public class EstabelecimentoAdapter$ViewHolder_ViewBinding<T extends EstabelecimentoAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public EstabelecimentoAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imgFoto = Utils.findRequiredViewAsType(source, R.id.item_estab_foto, "field 'imgFoto'", ImageView.class);
    target.txtTitulo = Utils.findRequiredViewAsType(source, R.id.item_estab_titulo, "field 'txtTitulo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgFoto = null;
    target.txtTitulo = null;

    this.target = null;
  }
}
