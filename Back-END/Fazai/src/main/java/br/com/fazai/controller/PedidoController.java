package br.com.fazai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.model.Pedido;
import br.com.fazai.service.PedidoService;



@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	

	/**
	 * Injetando o objeto cardapioDAO 
	 */
	@Autowired
	private PedidoService pedidoServ;

	

	public void setPedidoService(
			PedidoService pedidoServ) {
		this.pedidoServ = pedidoServ;
	}
	
	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public @ResponseBody void Salvar(@RequestBody Pedido pedido){
 
		try {
 
			this.pedidoServ.salvarPedido(pedido);

 
		} catch (Exception e) {
 
		}
 
	}
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody Pedido pedido){
 
		try {
 
			this.pedidoServ.alterarPedido(pedido);

 
		} catch (Exception e) {
 
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<Pedido> ConsultarTodos(){
 
		return this.pedidoServ.TodosPedidos();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.pedidoServ.Excluir(codigo);
 
	}
 

}
