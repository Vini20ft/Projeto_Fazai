package br.com.fazai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.dao.CardapioDAOImpl;
import br.com.fazai.model.Cardapio;
import br.com.fazai.service.CardapioService;
import br.com.fazai.service.EstabelecimentoService;

 

@Controller
@RequestMapping("/cardapio")
public class CardapioController {
 

	/**
	 * Injetando o objeto cardapioDAO 
	 */
	@Autowired
	private CardapioService cardapioServ;

	

	public void setCardapioService(
			CardapioService cardapioServ) {
		this.cardapioServ = cardapioServ;
	}
	
	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public @ResponseBody void Salvar(@RequestBody Cardapio cardapio){
 
		try {
 
			this.cardapioServ.salvarCardapio(cardapio);

 
		} catch (Exception e) {
 
		}
 
	}
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody Cardapio cardapio){
 
		try {
 
			this.cardapioServ.alterarCardapio(cardapio);

 
		} catch (Exception e) {
 
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<Cardapio> ConsultarTodos(){
 
		return this.cardapioServ.todosCardapios();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.cardapioServ.Excluir(codigo);
 
	}
 
}