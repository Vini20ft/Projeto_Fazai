package br.com.fazai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.ItemCardapioDAO;
import br.com.fazai.model.ItemCardapio;


@Service ("itemcardapioService")
@Transactional(readOnly = true)
public class ItemCardapioServiceImpl implements ItemCardapioService{
	
	
	@Autowired
	 private ItemCardapioDAO itemcardapioDAO;

	public void setItemCardapioDAO(ItemCardapioDAO ItemCardapioDAO) {
		this.itemcardapioDAO = ItemCardapioDAO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void salvarItemCardapio(ItemCardapio itemcardapio) {
		this.itemcardapioDAO.salvarItemCardapio(itemcardapio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarItemCardapio(ItemCardapio itemcardapio) {
		this.itemcardapioDAO.alterarItemCardapio(itemcardapio);
		
	}

	@Override
	public ItemCardapio consultarItemCardapioPorCodigo(int codigo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.itemcardapioDAO.Excluir(codigo);
		
	}

	@Override
	public List<ItemCardapio> TodosItens() {	
		return this.itemcardapioDAO.todosItems();
	}

	@Override
	public List<ItemCardapio> todosItemsporCardapio(int codigo_cardapio) {
	    return this.itemcardapioDAO.todosItemsporCardapio(codigo_cardapio);
	}

}
