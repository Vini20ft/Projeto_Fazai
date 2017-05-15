// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AvaliarEstabelecimentoActivity_ViewBinding<T extends AvaliarEstabelecimentoActivity> implements Unbinder {
  protected T target;

  private View view2131624076;

  @UiThread
  public AvaliarEstabelecimentoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_avaliar, "field 'mAvaliar' and method 'onClick'");
    target.mAvaliar = Utils.castView(view, R.id.btn_avaliar, "field 'mAvaliar'", Button.class);
    view2131624076 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mAvaliar = null;

    view2131624076.setOnClickListener(null);
    view2131624076 = null;

    this.target = null;
  }
}
