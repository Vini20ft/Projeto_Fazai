// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemCardapioDetalheFragment_ViewBinding<T extends ItemCardapioDetalheFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ItemCardapioDetalheFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mTituloItemCardapio = Utils.findRequiredViewAsType(source, R.id.txtTituloItemCardapio, "field 'mTituloItemCardapio'", TextView.class);
    target.mDescricaoItemCardapio = Utils.findRequiredViewAsType(source, R.id.txtDescricaoItemCardapio, "field 'mDescricaoItemCardapio'", TextView.class);
    target.mValorItemCardapio = Utils.findRequiredViewAsType(source, R.id.txtValorItemCardapio, "field 'mValorItemCardapio'", TextView.class);
    target.mImageItemCardapio = Utils.findOptionalViewAsType(source, R.id.image_foto_item_cardapio, "field 'mImageItemCardapio'", ImageView.class);
    target.mFab = Utils.findRequiredViewAsType(source, R.id.fab_item_cardapio, "field 'mFab'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTituloItemCardapio = null;
    target.mDescricaoItemCardapio = null;
    target.mValorItemCardapio = null;
    target.mImageItemCardapio = null;
    target.mFab = null;

    this.target = null;
  }
}
