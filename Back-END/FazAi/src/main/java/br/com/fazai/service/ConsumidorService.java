package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Consumidor;

public interface ConsumidorService {

	List<Consumidor> todosConsumidores();
	void Excluir(int codigo);
	Consumidor consultarConsumidorPorCodigo(int codigo);
	void alterarConsumidor(Consumidor consumidor);
	void salvarConsumidor(Consumidor consumidor);

}
