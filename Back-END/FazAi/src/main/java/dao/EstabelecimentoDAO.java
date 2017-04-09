package dao;

import java.io.FileWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Estabelecimento;
import util.UtilJPA;

import com.google.gson.Gson;

public class EstabelecimentoDAO {
	
	public EstabelecimentoDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Metodo de inserir cadastro de estabelecimento no banco de dados.
    public void inserirEstabelecimento(Estabelecimento e) throws  Exception {  	
    	
        try 
        {
            EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(e);
            et.commit();
            System.out.print("Estabelecimento "+e.getNome()+" Cadastrado com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Cadastrar o Estabelecimento "+e.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Metodo de procurar cadastro de estabeleciemnto por cnpj.
    public Estabelecimento procurarEstabelecimentoCnpj(int cnpj) throws  Exception {    
    	
        try 
        {    
        	Estabelecimento e = manager.find(Estabelecimento.class, cnpj);
            return e;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cnpj de Estabelecimento não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de procurar cadastro de estabelecimento por nome.
    public Estabelecimento procurarEstabelecimentoNome(String nome) throws  Exception {       
    	
    	try {
    		
            Estabelecimento e = (Estabelecimento) manager
                       .createQuery(
                  		 "SELECT e FROM Estabelecimento e WHERE nome = :nome ")
                       .setParameter("nome", nome).getSingleResult();            
            return e;
            
      } catch (NoResultException e) {
      	
      	  System.out.print("Estabelecimento Não Localizado!!! " + e);
          return null;
      }
    }
    
    //Metodo de alterar cadastro de estabelecimento.
    public void alterarEstabelecimento(Estabelecimento e) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(e);
            et.commit();
            System.out.print("Cadastro do Estabelecimento "+e.getNome()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Alterar o Estabelecimento "+e.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de excluir cadastro de estabelecimento.
    public void excluirEstabelecimento(int id) throws Exception {
    	
    	Estabelecimento cli = new Estabelecimento();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              cli = manager.getReference(Estabelecimento.class, id);
              manager.remove(cli);
              et.commit();
              System.out.print("Cadastro do Estabelecimento "+cli.getNome()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir o Estabelecimento "+cli.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
   //Metodo de listar todos os cadastro de estabelecimentos.
	public List<Estabelecimento> listarEstabelecimento()throws Exception{
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from Estabelecimento");
			@SuppressWarnings("unchecked")
			List<Estabelecimento> cli = query.getResultList();
			return cli;	
		} catch (Exception re) {
        	
    		throw new Exception("Erro ao Tentar Listar "+re);
        
    	}
		finally{
			manager.close();
		}
	}
	
	//Metodo de listar todos os cadastro de estabelecimentos, salvando em json.
		public List<Estabelecimento> listarEstabelecimentoJson()throws Exception{
	    	
			try{
				manager.getEntityManagerFactory();
				Query query = manager.createQuery("from Estabelecimento");
				@SuppressWarnings("unchecked")
				List<Estabelecimento> e = query.getResultList();
				Gson g = new Gson();
				String json = g.toJson(e);
				try {
					 
					 FileWriter fileWriter = new FileWriter("/Users/George/Desktop/listaEstabelecimento.json");
					 fileWriter.write(json);
					 fileWriter.close();
					 
					 } catch (Exception re) {
						 
						 throw new Exception("Erro ao Tentar Listar "+re);
					 }
					 	System.out.println(json);
				
				return e;
				
				
			} catch (Exception re) {
	        	
	    		throw new Exception("Erro ao Tentar Listar "+re);
	        
	    	}
			finally{
				manager.close();
			}
		}
}
