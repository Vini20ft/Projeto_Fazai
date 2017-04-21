package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.ItemCardapio;

public interface ItemCardapioService {

	void salvarItemCardapio(ItemCardapio itemcardapio);

	void alterarItemCardapio(ItemCardapio itemcardapio);

	ItemCardapio consultarItemCardapioPorCodigo(int codigo);

	void Excluir(int codigo);

	List<ItemCardapio> TodosItens();

}
