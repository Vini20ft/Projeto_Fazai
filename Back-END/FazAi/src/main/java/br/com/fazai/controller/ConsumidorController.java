package br.com.fazai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import br.com.fazai.model.Consumidor;
import br.com.fazai.service.ConsumidorService;


@Controller
@RequestMapping("/consumidor")
public class ConsumidorController {
	
	@Autowired
	private ConsumidorService consumidorServ;
	

	public void setCardapioService(
			ConsumidorService consumidorServ) {
		this.consumidorServ = consumidorServ;
	}
	
	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public @ResponseBody void Salvar(@RequestBody Consumidor consumidor){
 
		try {
 
			this.consumidorServ.salvarConsumidor(consumidor);

 
		} catch (Exception e) {
 
		}
 
	}
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody Consumidor consumidor){
 
		try {
 
			this.consumidorServ.alterarConsumidor(consumidor);

 
		} catch (Exception e) {
 
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<Consumidor> ConsultarTodos(){
 
		return this.consumidorServ.todosConsumidores();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.consumidorServ.Excluir(codigo);
 
	}
	

}
