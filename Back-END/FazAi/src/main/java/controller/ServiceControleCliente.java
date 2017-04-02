package controller;

import http.ClienteHttp;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Consumidor;
import dao.ConsumidorDAO;

@Path("/service")
public class ServiceControleCliente {
	
	private final ConsumidorDAO cliDAO = new ConsumidorDAO();
	
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse método cadastra uma nova pessoa
	 * */
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String Cadastrar(ClienteHttp cliHttp ){
 
		Consumidor cli = new Consumidor();
 
		try {
 
			cli.setId(cliHttp.getId());
			cli.setNome(cliHttp.getNome());
			cli.setLogin(cliHttp.getLogin());
			cli.setSenha(cliHttp.getSenha());			
 
			cliDAO.inserir(cli);
 
			return "Registro cadastrado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao cadastrar um registro " + e.getMessage();
		}
		
	}
	
	/**
	 * Essse método altera uma pessoa já cadastrada
	 * **/
	
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String Alterar(Consumidor cliHttp){
 
		Consumidor cli = new Consumidor();
 
		try {
			
			cli.setId(cliHttp.getId());
			cli.setNome(cliHttp.getNome());
			cli.setLogin(cliHttp.getLogin());
			cli.setSenha(cliHttp.getSenha());
 
			cliDAO.alterar(cli);
 
			return "Registro alterado com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao alterar o registro " + e.getMessage();
 
		}
 
	}
	
	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	
	/*@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todasClientes")
	public List<Pessoa> TodasPessoas(){
 
		List<Pessoa> pessoas =  new ArrayList<Pessoa>();
 
		List<PessoaEntity> listaEntityPessoas = repository.TodasPessoas();
 
		for (PessoaEntity entity : listaEntityPessoas) {
 
			pessoas.add(new Pessoa(entity.getCodigo(), entity.getNome(),entity.getSexo()));
		}
 
		return pessoas;
	}*/
	
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	
	/*@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getCliente/{codigo}")
	public Pessoa GetPessoa(@PathParam("codigo") Integer codigo){
 
		PessoaEntity entity = repository.GetPessoa(codigo);
 
		if(entity != null)
			return new Pessoa(entity.getCodigo(), entity.getNome(),entity.getSexo());
 
		return null;
	}*/
	
	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	
	/*@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String Excluir(@PathParam("codigo") Integer codigo){
 
		try {
 
			repository.Excluir(codigo);
 
			return "Registro excluido com sucesso!";
 
		} catch (Exception e) {
 
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}*/

}
