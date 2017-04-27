package br.com.fazai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.model.ItemCardapio;
import br.com.fazai.service.ItemCardapioService;



@Controller
@RequestMapping("/itemcardapio")
public class ItemCardapioController {

	
	@Autowired
	private ItemCardapioService itemcardapioServ;
	
	
	public void setItemCardapioService(
			ItemCardapioService itemcardapioServ) {
		this.itemcardapioServ = itemcardapioServ;
	}
	
	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public @ResponseBody void Salvar(@RequestBody ItemCardapio itemcardapio){
 
		try {
 
			this.itemcardapioServ.salvarItemCardapio(itemcardapio);

 
		} catch (Exception e) {
 
		}
 
	}
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody ItemCardapio itemcardapio){
 
		try {
 
			this.itemcardapioServ.alterarItemCardapio(itemcardapio);

 
		} catch (Exception e) {
 
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<ItemCardapio> ConsultarTodos(){
 
		return this.itemcardapioServ.TodosItens();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.itemcardapioServ.Excluir(codigo);
 
	}
 
	
}
