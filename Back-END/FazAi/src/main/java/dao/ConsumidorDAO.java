package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Consumidor;
import util.UtilJPA;

public class ConsumidorDAO {
	
	public ConsumidorDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Método de cadastrar consumidor.
	
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
    
    //Método de procurar consumidor pelo cpf.
    
    public Consumidor procurarConsumidorCpf(String cpf) throws  Exception {    
    	
        try 
        {    
        	Consumidor c = manager.find(Consumidor.class, cpf);
            return c;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cpf de Consumidor não Consta nos Nossos Registros "+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método de procurar consumidor pelo nome.
    
    public Consumidor procurarNome(String nome) throws  Exception {       
    	
         try 
        {   
        	Consumidor c = null;
        	c = manager.find(Consumidor.class, nome);
            return c;
            
                
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Nome de Consumidor não Consta nos Nossos Registros "+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método para alterar cadastro de consumidor.
    
    public void alterar(Consumidor cli) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(cli);
            et.commit();
            System.out.print("Cadastro de Consumidor "+cli.getNome()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Alterar Cadastro de Consumidor "+cli.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método de exclusão de consumidor.
    
    public void excluir(int id) throws Exception {
    	
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
            	
            		throw new Exception("Erro ao Tentar Excluir Cadastro do Consumidor "+c.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Método de listar consumidores cadastrados.
   
	public List<Consumidor> listar(){
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from consumidor");
			List<Consumidor> c = query.getResultList();
			return c;	
		}
		finally{
			manager.close();
		}
	}
}
