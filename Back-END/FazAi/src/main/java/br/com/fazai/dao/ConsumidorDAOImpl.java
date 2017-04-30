package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fazai.model.Consumidor;

@Repository
public class ConsumidorDAOImpl extends UtilJpaSpring implements ConsumidorDAO {

	
	@Override
	public void salvarConsumidor(Consumidor consumidor) {
		getManager().persist(consumidor);
		
	}

	@Override
	public void alterarConsumidor(Consumidor consumidor) {	
		getManager().merge(consumidor);
	}

	@Override
	public Consumidor consultarConsumidorPorCodigo(int codigo) {
		return getManager().find(Consumidor.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		Consumidor cardapio = this.consultarConsumidorPorCodigo(codigo);
		 
		getManager().remove(cardapio);
 
		
	}

	@Override
	public List<Consumidor> TodosConsumidores() {
		return getManager().createQuery("SELECT c FROM Consumidor c ORDER BY c.nome_consumidor ", Consumidor.class).getResultList();
	}
}
