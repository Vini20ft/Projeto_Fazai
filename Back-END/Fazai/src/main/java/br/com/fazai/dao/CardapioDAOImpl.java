package br.com.fazai.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.fazai.model.Cardapio;
import br.com.fazai.model.ItemCardapio;

@Repository
public class CardapioDAOImpl extends UtilJpaSpring implements CardapioDAO {

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
    public List<Cardapio> todosCardapios(int codigo_estabelecimento) {

	Query query = getManager()
		.createQuery(
			"SELECT c FROM Cardapio c WHERE cardapio_estabelecimento_fk.id_estabelecimento = :codigo_estabelecimento ORDER BY c.id_cardapio");
	query.setParameter("codigo_estabelecimento", codigo_estabelecimento);
	List<Cardapio> todosCardapios = query.getResultList();

	return todosCardapios;
    }

    @Override
    public List<ItemCardapio> todosItemsPorCardapio(int codigo_cardapio) {
	Query query = getManager().createQuery(
		"SELECT c FROM ItemCardapio c WHERE cardapio.id_cardapio = :codigo_cardapio");
	query.setParameter("codigo_cardapio", codigo_cardapio);
	List<ItemCardapio> todosItensCardapio = query.getResultList();

	return todosItensCardapio;

    }

}
