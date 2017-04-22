package br.com.fazai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.model.Estabelecimento;
import br.com.fazai.service.EstabelecimentoService;

 
/**
 * 
 * @author vinicius.santana
 *
 *Essa � a classe que o Spring vai gerenciar (Controller para o estabelecimento)
 *
 *@Controller => informa que a classe � um controller a ser gerenciado pelo Spring
 *
 * @RequestMapping => caminho para acessar o controller
 */
@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {
 
	@Autowired
	private EstabelecimentoService estabelecimentoService;

	public void setEstabelecimentoService(
			EstabelecimentoService estabelecimentoService) {
		this.estabelecimentoService = estabelecimentoService;
	}

	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST)
	public @ResponseBody void Salvar(@RequestBody Estabelecimento estabelecimento){
 
		try {
 
			this.estabelecimentoService.salvarEstabelecimento(estabelecimento);

 
		} catch (Exception e) {
 
		}
 
	}
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody Estabelecimento estabelecimento){
 
		try {
 
			this.estabelecimentoService.alterarEstabelecimento(estabelecimento);

 
		} catch (Exception e) {
 
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<Estabelecimento> ConsultarTodos(){
 
		return this.estabelecimentoService.TodosEstabelecimentos();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.estabelecimentoService.Excluir(codigo);
 
	}
 
}