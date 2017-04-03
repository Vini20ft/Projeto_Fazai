package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Funcionario;
import util.UtilJPA;

public class FuncionarioDAO {
	
	public FuncionarioDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	public Funcionario getFuncionario(String login, String senha) {
		   
        try {
              Funcionario f = (Funcionario) manager
                         .createQuery(
                    		 "SELECT login, senha from funcionario where login = :login and senha = :senha")
                         .setParameter("login", login)
                         .setParameter("senha", senha).getSingleResult();
              if(f != null){
            	  System.out.print("pegou caralho!!!");
              }
              return f;
              
        } catch (NoResultException e) {
        	
        	  System.out.print("Funcionario ou Senha Inválida!!!");
              return null;
        }
  }

    public void inserir(Funcionario f) throws  Exception {  	
    	
        try 
        {
            EntityTransaction et = manager.getTransaction();
            et.begin();
            manager.persist(f);
            et.commit();
            System.out.print("Funcionario "+f.getNome()+" Cadastrado com Sucesso!!!");
            
        } catch (Exception ex) {        	
            try 
            {
                utx.rollback();   
                
            } catch (Exception re) {    
            	
                throw new Exception("Erro ao Tentar Cadastrar "+f.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    public Funcionario procurarId(String cpf) throws  Exception {    
    	
        try 
        {    
        	Funcionario f = manager.find(Funcionario.class, cpf);
            return f;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cpf de Funcionario não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    public Funcionario procurarNome(String nome) throws  Exception {       
    	
         try 
        {   
        	Funcionario f = null;
        	f = manager.find(Funcionario.class, nome);
            return f;
            
                
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Nome de Funcionario não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    public void alterar(Funcionario f) throws  Exception {      
    	
        try 
        {               
            EntityTransaction et = manager.getTransaction();
            et.begin();	
            manager.merge(f);
            et.commit();
            System.out.print("Cadastro do Funcionario "+f.getNome()+" Alterado com Sucesso!!!");
            
        } catch (Exception ex) {
        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Alterar "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }

    public void excluir(int id) throws Exception {
    	
    	Funcionario f = new Funcionario();
    	    
        try 
        {
              EntityTransaction et = manager.getTransaction();
              et.begin();	
              f = manager.getReference(Funcionario.class, id);
              manager.remove(f);
              et.commit();
              System.out.print("Cadastro do Funcionario "+f.getNome()+" Excluido com Sucesso!!!");
              
        } catch (Exception ex) {
        	
            	try 
            	{
            	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Erro ao Tentar Excluir "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
   
	public List<Funcionario> listar(){
    	
		try{
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from funcionario");
			List<Funcionario> f = query.getResultList();
			return f;	
		}
		finally{
			manager.close();
		}
	}
}
