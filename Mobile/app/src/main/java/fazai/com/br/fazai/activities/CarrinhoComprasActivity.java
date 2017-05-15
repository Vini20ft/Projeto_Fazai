package fazai.com.br.fazai.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fazai.com.br.fazai.R;
import fazai.com.br.fazai.http.ItemCardapioTask;
import fazai.com.br.fazai.interfaces.OnItemCardapioClick;
import fazai.com.br.fazai.model.Carrinho;
import fazai.com.br.fazai.model.Constantes;
import fazai.com.br.fazai.model.ItemCardapio;
import fazai.com.br.fazai.model.ValorReal;
import fazai.com.br.fazai.model.VerifyConnection;
import fazai.com.br.fazai.ui.adapter.ItemCardapioAdapter;

public class CarrinhoComprasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnItemCardapioClick, LoaderManager.LoaderCallbacks<List<ItemCardapio>> {

    public static Carrinho mCarrinho;

    @BindView(R.id.listViewProdutos)
    ListView mListItemCardapio;
    private LoaderManager mLoaderManager;
    private List<ItemCardapio> mItemCardapioList;
    private ItemCardapioAdapter mAdapter;
    ValorReal valorReal = new ValorReal();
    TextView mResponse;

    private VerifyConnection verifyConnection;
    PayPalConfiguration mConfiguration;
    // the id is the link to the paypal account, we have to create an app and get its id
    //String mPaypalClientId = "9A4RFY89XFG8A";
    String mPaypalClientId = Constantes.PAYPAL_CLIENT_ID;
    Intent mService;
    int mPaypalRequestCode = 999; // or any number you want

    List<ItemCardapio> mItemCardapio;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_compras);

        ButterKnife.bind(this);

        verifyConnection = new VerifyConnection(this);
        verifyConnection.verificaConexao();

        mConfiguration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // sandbox for test, production for real
                .clientId(mPaypalClientId);

        mService = new Intent(this, PayPalService.class);
        mService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, mConfiguration); // configuration above
        startService(mService); // paypal service, listening to calls to paypal app

        mListItemCardapio.setOnItemClickListener(this);

        mResponse = (TextView) findViewById(R.id.response);

        mCarrinho = new Carrinho();

        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(0, null, this);
    }

    public void pay(View view)
    {
        PayPalPayment cart = new PayPalPayment(new BigDecimal(mCarrinho.getValue()), "USD", "Cart",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, mConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, cart);
        startActivityForResult(intent, mPaypalRequestCode);
    }

    public void viewCart(View view)
    {
        Intent intent = new Intent(this, ItensCarrinhoActivity.class);
        mCarrinho = mCarrinho;
        startActivity(intent);
    }

    public void reset(View view)
    {
        mResponse.setText(R.string.total_valor + " = " + valorReal.ConverterValorReal(mCarrinho.mValor));
        mCarrinho.empty();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        boolean isPagamentoEfetuado = false;
        if(requestCode == mPaypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                // we have to confirm that the payment worked to avoid fraud
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if(confirmation != null) {
                    String state = confirmation.getProofOfPayment().getState();

                    if (state.equals("approved")) { // if the payment worked, the state equals approved
                        mResponse.setText(R.string.pagamento_aprovado);
                        isPagamentoEfetuado = true;
                    } else {
                        mResponse.setText(R.string.erro_no_pagamento);
                    }
                }else {
                    mResponse.setText(R.string.confirmação_nula);
                }
            }
        }

        //Verifica se o pagamento foi aprovado

    }

    @Override
    public Loader<List<ItemCardapio>> onCreateLoader(int id, Bundle args) {
        int idCardapio = 0;

        if (args != null) {
            idCardapio = args.getInt(Constantes.CARDAPIO_ID);
        }

        return new ItemCardapioTask(getApplicationContext(), /*idCardapio*/ 1);
    }

    @Override
    public void onLoadFinished(Loader<List<ItemCardapio>> loader, List<ItemCardapio> data) {
        if (data != null) {
            mItemCardapioList = data;
            mAdapter = new ItemCardapioAdapter(this, mItemCardapioList);
            mAdapter.notifyDataSetChanged();
            mListItemCardapio.setAdapter(mAdapter);
        } else {
            Toast.makeText(this, getText(R.string.nao_ha_produtos), Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ItemCardapio>> loader) {

    }

    @Override
    public void onItemCardapioClick(ItemCardapio itemCardapio) {

        mResponse.setText(getResources().getString(R.string.total_valor) + " = " + valorReal.ConverterValorReal(mCarrinho.mValor));
        mCarrinho.addToCart(itemCardapio);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ItemCardapio itemCardapio = (ItemCardapio) mListItemCardapio.getItemAtPosition(i);
        this.onItemCardapioClick(itemCardapio);
    }
}
