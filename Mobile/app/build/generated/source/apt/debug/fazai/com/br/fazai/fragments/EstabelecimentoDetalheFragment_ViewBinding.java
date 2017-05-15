// Generated code from Butter Knife. Do not modify!
package fazai.com.br.fazai.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import fazai.com.br.fazai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EstabelecimentoDetalheFragment_ViewBinding<T extends EstabelecimentoDetalheFragment> implements Unbinder {
  protected T target;

  private View view2131624116;

  private View view2131624117;

  @UiThread
  public EstabelecimentoDetalheFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mImageEstabelecimento = Utils.findOptionalViewAsType(source, R.id.image_foto, "field 'mImageEstabelecimento'", ImageView.class);
    target.mFab = Utils.findRequiredViewAsType(source, R.id.fab, "field 'mFab'", FloatingActionButton.class);
    view = Utils.findRequiredView(source, R.id.btn_avaliar_estabelecimento, "field 'mAvaliar_estabelecimento' and method 'onClick'");
    target.mAvaliar_estabelecimento = Utils.castView(view, R.id.btn_avaliar_estabelecimento, "field 'mAvaliar_estabelecimento'", Button.class);
    view2131624116 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_ligar, "field 'mbtnLigar' and method 'onClick'");
    target.mbtnLigar = Utils.castView(view, R.id.btn_ligar, "field 'mbtnLigar'", Button.class);
    view2131624117 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mTxtBairro = Utils.findRequiredViewAsType(source, R.id.txtBairro, "field 'mTxtBairro'", TextView.class);
    target.mTxtCidade = Utils.findRequiredViewAsType(source, R.id.txtCidade, "field 'mTxtCidade'", TextView.class);
    target.mTxtEstado = Utils.findRequiredViewAsType(source, R.id.txtEstado, "field 'mTxtEstado'", TextView.class);
    target.mTxtNumero = Utils.findRequiredViewAsType(source, R.id.txtNumero, "field 'mTxtNumero'", TextView.class);
    target.mTxtRua = Utils.findRequiredViewAsType(source, R.id.txtRua, "field 'mTxtRua'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mImageEstabelecimento = null;
    target.mFab = null;
    target.mAvaliar_estabelecimento = null;
    target.mbtnLigar = null;
    target.mTxtBairro = null;
    target.mTxtCidade = null;
    target.mTxtEstado = null;
    target.mTxtNumero = null;
    target.mTxtRua = null;

    view2131624116.setOnClickListener(null);
    view2131624116 = null;
    view2131624117.setOnClickListener(null);
    view2131624117 = null;

    this.target = null;
  }
}
