package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Consumidor;
import util.UtilJPA;

public class ConsumidorDAO {
	
	public ConsumidorDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Metodo de cadastrar consumidor.
    public void inserirConsumidor(Consumidor c) throws  Exception {  	
    	
        try 
        {
        	EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(c);
            et.commit();
            System.out.print("Consumidor "+c.getNome()+" Cadastrado com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Cadastrar Consumidor "+c.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Metodo de procurar consumidor por codigo.
    public Consumidor procurarConsumidorId(int cpf) throws  Exception {    
    	
        try 
        {    
        	Consumidor cli = manager.find(Consumidor.class, cpf);
            return cli;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cpf de Consumidor não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de procurar consumidor por nome.
    public Consumidor procurarConsumidorNome(String nome) throws  Exception {       
    	
    	try {
    		
            Consumidor c = (Consumidor) manager
                       .createQuery(
                  		 "SELECT c FROM Consumidor c WHERE nome = :nome ")
                       .setParameter("nome", nome).getSingleResult();            
            return c;
            
      } catch (NoResultException e) {
      	
      	  System.out.print("Consumidor Não Localizado!!! " + e);
          return null;
      }
    }
    
    //Metodo de alterar cadastro de consumidor.
    public void alterarConsumidor(Consumidor c) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(c);
            et.commit();
            System.out.print("Cadastro de Consumidor "+c.getNome()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Alterar "+c.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de excluir cadastro de consumidor.
    public void excluirConsumidor(int id) throws Exception {
    	
    	Consumidor c = new Consumidor();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              c = manager.getReference(Consumidor.class, id);
              manager.remove(c);
              et.commit();
              System.out.print("Cadastro do Consumidor "+c.getNome()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir "+c.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Metodo de listar todos os cadastros de consumidor
	public List<Consumidor> listarConsumidor()throws Exception{
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from Consumidor");
			@SuppressWarnings("unchecked")
			List<Consumidor> c = query.getResultList();
			return c;	
		} catch (Exception re) {
        	
    		throw new Exception("Erro ao Tentar Listar "+re);
        
    	}
		finally{
			manager.close();
		}
	}
}
