package br.com.fazai.dao;

import java.util.List;

import br.com.fazai.model.ItemCardapio;

public interface ItemCardapioDAO {

	void salvarItemCardapio(ItemCardapio itemcardapio);
	void alterarItemCardapio(ItemCardapio itemcardapio);
	ItemCardapio consultarItemCardapioPorCodigo(int codigo);
	void Excluir(int codigo);
	List<ItemCardapio> TodosItems();

}
