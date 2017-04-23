package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fazai.model.Cardapio;
@Repository
public class CardapioDAOImpl extends UtilJpaSpring implements CardapioDAO{

	
	@Override
	public void salvarCardapio(Cardapio cardapio) {
		getManager().persist(cardapio);
		
	}

	@Override
	public void alterarCardapio(Cardapio cardapio) {	
		getManager().merge(cardapio);
	}

	@Override
	public Cardapio consultarCardapioPorCodigo(int codigo) {
		return getManager().find(Cardapio.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		Cardapio cardapio = this.consultarCardapioPorCodigo(codigo);
		 
		getManager().remove(cardapio);
 
		
	}

	@Override
	public List<Cardapio> TodosCardapios() {
		return getManager().createQuery("SELECT c FROM Cardapio c ORDER BY c.tipo_cardapio ", Cardapio.class).getResultList();
	}

}
