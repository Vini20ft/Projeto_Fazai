package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Cardapio;
import br.com.fazai.model.ItemCardapio;

public interface CardapioService {

        List<Cardapio> todosCardapios(int codigo_estabelecimento);
	void Excluir(int codigo);
	Cardapio consultarCardapioPorCodigo(int codigo);
	void salvarCardapio(Cardapio cardapio);
	void alterarCardapio(Cardapio cardapio);
	List<ItemCardapio> todosItemsPorCardapio(int codigo_cardapio);

}
