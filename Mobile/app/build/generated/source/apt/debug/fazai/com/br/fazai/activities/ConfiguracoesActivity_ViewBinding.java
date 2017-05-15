// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConfiguracoesActivity_ViewBinding<T extends ConfiguracoesActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ConfiguracoesActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mImageBrazil = Utils.findRequiredViewAsType(source, R.id.imageBrazil, "field 'mImageBrazil'", ImageButton.class);
    target.mImageEua = Utils.findRequiredViewAsType(source, R.id.imageEua, "field 'mImageEua'", ImageButton.class);
    target.mImageSpain = Utils.findRequiredViewAsType(source, R.id.imageSpain, "field 'mImageSpain'", ImageButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mImageBrazil = null;
    target.mImageEua = null;
    target.mImageSpain = null;

    this.target = null;
  }
}
