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

public class ItensCardapioActivity_ViewBinding<T extends ItensCardapioActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ItensCardapioActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mListItemCardapio = Utils.findRequiredViewAsType(source, R.id.listItemCardapio, "field 'mListItemCardapio'", ListView.class);
    target.mSwipe = Utils.findRequiredViewAsType(source, R.id.swipeItemCardapio, "field 'mSwipe'", SwipeRefreshLayout.class);
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    target.mDrawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'mDrawer'", DrawerLayout.class);
    target.mNavigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'mNavigationView'", NavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mListItemCardapio = null;
    target.mSwipe = null;
    target.mToolbar = null;
    target.mDrawer = null;
    target.mNavigationView = null;

    this.target = null;
  }
}
