package br.com.fazai.dao;

import java.util.List;

import br.com.fazai.model.Cardapio;
import br.com.fazai.model.Consumidor;

public interface ConsumidorDAO {

	void Excluir(int codigo);

	Consumidor consultarConsumidorPorCodigo(int codigo);

	

	void salvarConsumidor(Consumidor consumidor);



	void alterarConsumidor(Consumidor consumidor);

	List<Consumidor> TodosConsumidores();



}
