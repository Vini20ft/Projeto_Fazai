package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import br.com.fazai.model.Funcionario;
import br.com.fazai.model.ItemCardapio;


@Repository
public class ItemCardapioDAOImpl extends UtilJpaSpring implements ItemCardapioDAO {
	

	@Override

	public void salvarItemCardapio(ItemCardapio itemcardapio) {
		getManager().persist(itemcardapio);
		
	}

	@Override

	public void alterarItemCardapio(ItemCardapio itemcardapio) {	
		getManager().merge(itemcardapio);
	}

	@Override
	public ItemCardapio consultarItemCardapioPorCodigo(int codigo) {
		return getManager().find(ItemCardapio.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		ItemCardapio itemcardapio = this.consultarItemCardapioPorCodigo(codigo);
		 
		getManager().remove(itemcardapio);
 
		
	}

	@Override
	public List<ItemCardapio> TodosItems() {
		return getManager().createQuery("SELECT c FROM ItemCardapio c ORDER BY c.nome ", ItemCardapio.class).getResultList();
	}
	

}
