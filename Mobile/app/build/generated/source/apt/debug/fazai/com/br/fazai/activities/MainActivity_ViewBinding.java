// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mListEstabelecimentos = Utils.findRequiredViewAsType(source, R.id.listEstabelecimentos, "field 'mListEstabelecimentos'", ListView.class);
    target.mSwipe = Utils.findRequiredViewAsType(source, R.id.swipeMain, "field 'mSwipe'", SwipeRefreshLayout.class);
    target.mNavigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'mNavigationView'", NavigationView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mListEstabelecimentos = null;
    target.mSwipe = null;
    target.mNavigationView = null;
    target.toolbar = null;
    target.drawer = null;

    this.target = null;
  }
}
