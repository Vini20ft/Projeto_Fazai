// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CarrinhoComprasActivity_ViewBinding<T extends CarrinhoComprasActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CarrinhoComprasActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mListItemCardapio = Utils.findRequiredViewAsType(source, R.id.listViewProdutos, "field 'mListItemCardapio'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mListItemCardapio = null;

    this.target = null;
  }
}
