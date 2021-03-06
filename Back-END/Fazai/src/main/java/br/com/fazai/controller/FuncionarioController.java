package br.com.fazai.controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.model.Funcionario;
import br.com.fazai.service.FuncionarioService;

 
/**
 * 
 * @author vinicius.santana
 *
 *Essa � a classe que o Spring vai gerenciar (Controller para o funcionario)
 *
 *@Controller => informa que a classe � um controller a ser gerenciado pelo Spring
 *
 * @RequestMapping => caminho para acessar o controller
 */
@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
 

	@Autowired
    private FuncionarioService funcionarioServ;
	
	public void setFuncionarioService(
			FuncionarioService funcionarioServ) {
		this.funcionarioServ = funcionarioServ;
	}

	/**
	 * 
	 * @RequestMapping => value  => Defini o caminho para a chamada da view. 
	 * @RequestMapping => method => Defini o o m�todo http que o m�todo vai responder.
	 */
	@RequestMapping(value="/salvar", method= RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public @ResponseBody String Salvar(@RequestBody Funcionario funcionario){
 
		try {
 
			if (this.funcionarioServ != null) {
				this.funcionarioServ.salvarFuncionario(funcionario);
				return HttpStatus.CREATED.name().toString();
			    } else {
				return HttpStatus.CONFLICT.name().toString();
			    }
			
			

 
		} catch (Exception e) {
		    return HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name().toString();
		}

	    }
 
	@RequestMapping(value="/alterar", method= RequestMethod.PUT)
	public @ResponseBody void Alterar(@RequestBody Funcionario funcionario){
 
		try {
 
			this.funcionarioServ.alterarFuncionario(funcionario);

 
		} catch (Exception e) {
 
		}
 
	}
	
	@RequestMapping(value="/login", method= RequestMethod.PUT)
	public @ResponseBody void loginFuncionario(@RequestBody Funcionario funcionario){
 
		try {
			
			this.funcionarioServ.loginFuncionario(funcionario);

 
		}catch (NoResultException e){
			
			System.out.print("Email Invalido!!! " + e);
			
		}
 
	}
	
	@RequestMapping(value="/esquecisenha", method= RequestMethod.PUT)
	public @ResponseBody void esqueciSenhaFuncionario(@RequestBody Funcionario funcionario) throws EmailException{
 
		try {
			
			this.funcionarioServ.esqueciSenhaFuncionario(funcionario);;

 
		}catch (NoResultException e){
			
			System.out.print("Email Invalido!!! " + e);
			
		}
 
	}
 
 
	@RequestMapping(value="/consultarTodos", method= RequestMethod.GET)
	public @ResponseBody List<Funcionario> ConsultarTodos(){
 
		return 	this.funcionarioServ.TodosFuncionarios();
 
	}
 

	@RequestMapping(value="/excluirRegistro/{codigo}", method= RequestMethod.DELETE)
	public @ResponseBody void ExcluirRegistro(@PathVariable int codigo){
 
		this.funcionarioServ.Excluir(codigo);
 
	}
 
}