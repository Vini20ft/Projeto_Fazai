package br.com.fazai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.ConsumidorDAO;
import br.com.fazai.model.Consumidor;


@Service ("consumidorService")
@Transactional(readOnly = true)
public class ConsumidorSericeImpl implements ConsumidorService{
	
	
	@Autowired
	private ConsumidorDAO consumidordao;
	
	public void setConsumidorDAO(ConsumidorDAO consumidordao) {
		this.consumidordao = consumidordao;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void salvarConsumidor(Consumidor consumidor) {
		this.consumidordao.salvarConsumidor(consumidor);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarConsumidor(Consumidor consumidor) {
		this.consumidordao.alterarConsumidor(consumidor);
	}

	@Override
	public Consumidor consultarConsumidorPorCodigo(int codigo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.consumidordao.Excluir(codigo);
		
	}

	@Override
	public List<Consumidor> todosConsumidores() {	
		return this.consumidordao.TodosConsumidores();
	}
	
	
}
