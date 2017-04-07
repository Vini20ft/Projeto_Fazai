package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Consumidor;
import model.Pedido;
import util.UtilJPA;

public class PedidoDAO {
	
	public PedidoDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Metodo de inserir pedido no banco
	public void inserirPedido(Pedido p) throws  Exception {  	
    	
        try 
        {
            EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(p);
            et.commit();
            System.out.print("Pedido de Número "+p.getnPedido()+" Cadastrado com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Cadastrar Pedido de Número "+p.getnPedido()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
	//Metodo de procurar pedido por codigo.
    public Pedido procurarPedidoId(int id) throws  Exception {    
    	
        try 
        {    
        	Pedido p = manager.find(Pedido.class, id);
            return p;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Número de Pedido não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de alterar pedido.
    public void alterarPedido(Pedido p) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(p);
            et.commit();
            System.out.print("Pedido de Número "+p.getnPedido()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Alterar Pedido de Número "+p.getnPedido()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de excluir pedido.
    public void excluirPedido(int id) throws Exception {
    	
    	Pedido p = new Pedido();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              p = manager.getReference(Pedido.class, id);
              manager.remove(p);
              et.commit();
              System.out.print("Pedido de Número "+p.getnPedido()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir Pedido de Número "+p.getnPedido()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
   //metodo de listar pedidos.
	public List<Consumidor> listarPedidos()throws Exception{
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from Pedido");
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

