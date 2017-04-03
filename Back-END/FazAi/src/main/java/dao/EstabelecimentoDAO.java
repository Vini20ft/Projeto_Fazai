package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import model.Estabelecimento;
import util.UtilJPA;

public class EstabelecimentoDAO {
	
	public EstabelecimentoDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();

    public void inserir(Estabelecimento e) throws  Exception {  	
    	
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
            	
                throw new Exception("Erro ao Tentar Cadastrar "+e.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    public Estabelecimento procurarCnpj(String cnpj) throws  Exception {    
    	
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
    
    public Estabelecimento procurarNome(String nome) throws  Exception {       
    	
         try 
        {   
        	Estabelecimento cli = null;
        	cli = manager.find(Estabelecimento.class, nome);
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
    
    public void alterar(Estabelecimento cli) throws  Exception {      
    	
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
    	
    	Estabelecimento cli = new Estabelecimento();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              cli = manager.getReference(Estabelecimento.class, id);
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
    
   
	public List<Estabelecimento> listar(){
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from cliente");
			List<Estabelecimento> cli = query.getResultList();
			return cli;	
		}
		finally{
			manager.close();
		}
	}
}
