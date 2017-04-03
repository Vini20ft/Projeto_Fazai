package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Cardapio;
import model.Funcionario;
import util.UtilJPA;

public class CardapioDAO {
	
	public CardapioDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Método de inserir Cardapio no banco de dados
	public void inserirCardapio(Cardapio c) throws  Exception {  	
    	
        try 
        {
            EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(c);
            et.commit();
            System.out.print("Cardapio Criado Por "+c.getCriador()+" Feito com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Criar Cardapio de "+c.getCriador()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
	//Método de procurar cardapio por id.
    public Cardapio procurarCardapioId(int id) throws  Exception {    
    	
        try 
        {    
        	Cardapio c = manager.find(Cardapio.class, id);
            return c;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Código de Cardápio não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    //Metodo de procurar cardapio por tipo
    public Cardapio procurarCardapioTipo(String tipo) throws  Exception {       
    	
         try 
        {   
        	Cardapio c = null;
        	c = manager.find(Cardapio.class, tipo);
            return c;
            
                
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Tipo de Cardápio não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método de alterar cardapio
    public void alterarCardapio(Cardapio c) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(c);
            et.commit();
            System.out.print("Cardapio Criado por "+c.getCriador()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Alterar Cardapio Criado por "+c.getCriador()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de excluir cardapio
    public void excluirCardapio(int id) throws Exception {
    	
    	Cardapio c = new Cardapio();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              c = manager.getReference(Cardapio.class, id);
              manager.remove(c);
              et.commit();
              System.out.print("Cadastro de Cardapio Criado por "+c.getCriador()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir o Cardápio Criado por "+c.getCriador()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
   //Metodo de listar cardapios
	public List<Funcionario> listarCardapio(){
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from cardapio");
			List<Funcionario> f = query.getResultList();
			return f;	
		}
		finally{
			manager.close();
		}
	}
}

