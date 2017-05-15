// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  @UiThread
  public LoginActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mLoginButton = Utils.findRequiredViewAsType(source, R.id.login_button, "field 'mLoginButton'", LoginButton.class);
    target.signInButton = Utils.findRequiredViewAsType(source, R.id.signInButton, "field 'signInButton'", SignInButton.class);
    target.mFb = Utils.findRequiredViewAsType(source, R.id.fb, "field 'mFb'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mLoginButton = null;
    target.signInButton = null;
    target.mFb = null;

    this.target = null;
  }
}
