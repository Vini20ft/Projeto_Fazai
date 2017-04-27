package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Cardapio;

public interface CardapioService {

	List<Cardapio> todosCardapios();
	void Excluir(int codigo);
	Cardapio consultarCardapioPorCodigo(int codigo);
	void salvarCardapio(Cardapio cardapio);
	void alterarCardapio(Cardapio cardapio);

}
