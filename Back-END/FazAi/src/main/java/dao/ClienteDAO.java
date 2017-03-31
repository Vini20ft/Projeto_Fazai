package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Cliente;
import util.UtilJPA;

public class ClienteDAO {
	
	public ClienteDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();

    public void inserir(Cliente c) throws  Exception {  	
    	
        try 
        {
            EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(c);
            et.commit();
            System.out.print("Cliente "+c.getNome()+" Cadastrado com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Cadastrar "+c.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    public Cliente procurarId(String cpf) throws  Exception {    
    	
        try 
        {    
        	Cliente cli = manager.find(Cliente.class, cpf);
            return cli;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cpf de Cliente não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    public Cliente procurarNome(String nome) throws  Exception {       
    	
         try 
        {   
        	Cliente cli = null;
        	cli = manager.find(Cliente.class, nome);
            return cli;
            
                
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Nome de Cliente não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    public void alterar(Cliente cli) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(cli);
            et.commit();
            System.out.print("Cadastro do Cliente "+cli.getNome()+" Alterado com Sucesso!!!");
            
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
    	
    	Cliente cli = new Cliente();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              cli = manager.getReference(Cliente.class, id);
              manager.remove(cli);
              et.commit();
              System.out.print("Cadastro do Cliente "+cli.getNome()+" Excluido com Sucesso!!!");
              
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
    
   
	public List<Cliente> listar(){
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from cliente");
			List<Cliente> cli = query.getResultList();
			return cli;	
		}
		finally{
			manager.close();
		}
	}
}
