package fazai.com.br.fazai.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Carrinho
{
    public Map<ItemCardapio, Integer> mCarrinho;
    public double mValor = 0.0;

    public Carrinho()
    {
        mCarrinho = new LinkedHashMap<>();
    }

    public void addToCart(ItemCardapio itemCardapio)
    {
        if (mCarrinho.containsKey(itemCardapio)) {
            mCarrinho.put(itemCardapio, mCarrinho.get(itemCardapio) + 1);
        } else {
            mCarrinho.put(itemCardapio, 1);
        }
        mValor += itemCardapio.valor;
    }

    public int getQuantity(ItemCardapio itemCardapio)
    {
        return mCarrinho.get(itemCardapio);
    }

    public Set getProducts()
    {
        return mCarrinho.keySet();
    }

    public void empty()
    {
        mCarrinho.clear();
        mValor = 0;
    }

    public double getValue()
    {
        return mValor;
    }

    public int getSize()
    {
        return mCarrinho.size();
    }
}
