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

    public void inserir(Consumidor c) throws  Exception {  	
    	
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
    
    public Consumidor procurarId(String cpf) throws  Exception {    
    	
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
    
    public Consumidor procurarNome(String nome) throws  Exception {       
    	
         try 
        {   
        	Consumidor cli = null;
        	cli = manager.find(Consumidor.class, nome);
            return cli;
            
                
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Nome de Consumidor não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
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
            	
            		throw new Exception("Erro ao Tentar Alterar "+cli.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }

    public void excluir(int id) throws Exception {
    	
    	Consumidor cli = new Consumidor();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              cli = manager.getReference(Consumidor.class, id);
              manager.remove(cli);
              et.commit();
              System.out.print("Cadastro do Consumidor "+cli.getNome()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir "+cli.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
   
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
