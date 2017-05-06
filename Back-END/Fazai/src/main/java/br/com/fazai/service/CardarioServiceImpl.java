package br.com.fazai.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.CardapioDAO;
import br.com.fazai.model.Cardapio;
import br.com.fazai.model.ItemCardapio;

@Service ("cardapioService")
@Transactional(readOnly = true)
public class CardarioServiceImpl implements CardapioService{
	
	@Autowired
    private CardapioDAO cardapiodao;
	
	public void setCardapioDAO(CardapioDAO cardapiodao) {
		this.cardapiodao = cardapiodao;
	}


	@Override
	@Transactional(readOnly = false)
	public void salvarCardapio(Cardapio cardapio) {
		this.cardapiodao.salvarCardapio(cardapio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarCardapio(Cardapio cardapio) {
		this.cardapiodao.alterarCardapio(cardapio);
	}

	@Override
	public Cardapio consultarCardapioPorCodigo(int codigo) {
		return this.cardapiodao.consultarCardapioPorCodigo(codigo);
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.cardapiodao.Excluir(codigo);
		
	}

	@Override
	public List<Cardapio> todosCardapios(int codigo_estabelecimento) {
	    // TODO Auto-generated method stub
	    return this.cardapiodao.todosCardapios(codigo_estabelecimento);
	}


	@Override
	public List<ItemCardapio> todosItemsPorCardapio(int codigo_cardapio) {
	    // TODO Auto-generated method stub
	    return this.cardapiodao.todosItemsPorCardapio(codigo_cardapio);
	}
	
}
