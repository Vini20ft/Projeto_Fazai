package controller;

import http.ConsumidorHttp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Consumidor;
import dao.ConsumidorDAO;

@Path("/service")
public class ConsumidorServiceController {
	
	private final ConsumidorDAO cd = new ConsumidorDAO();
	
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
	public String Cadastrar(ConsumidorHttp ch ){
 
		Consumidor con = new Consumidor();
 
		try {
 
			con.setNome(ch.getNome());
			con.setCpf(ch.getCpf());
			con.setEmail(ch.getEmail());
			con.setTelefone(ch.getTelefone());
			cd.inserirConsumidor(con);
 
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
	public String Alterar(Consumidor ch){
 
		Consumidor c = new Consumidor();
 
		try {
						
			c.setNome(ch.getNome());
			c.setCpf(ch.getCpf());
			c.setEmail(ch.getEmail());
			c.setTelefone(ch.getTelefone());
			cd.alterarConsumidor(c);
 
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
