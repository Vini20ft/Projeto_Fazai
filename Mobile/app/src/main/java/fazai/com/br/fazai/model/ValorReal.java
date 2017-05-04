package fazai.com.br.fazai.model;

import java.text.NumberFormat;
import java.util.Locale;

public class ValorReal {

    public String ConverterValorReal(Float valor) {

        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(valor);
        System.out.println(valorString);

        return valorString;
    }
}
