package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import model.Funcionario;
import util.UtilJPA;

public class FuncionarioDAO {
	
	public FuncionarioDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Método de inserir funcionário.
	
    public void inserirFuncionario(Funcionario f) throws  Exception {  	
    	
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
            	
                throw new Exception("Erro ao Tentar Cadastrar Funcionario "+f.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Método de procurar funcionario por cpf.
    
    public Funcionario procurarFuncionarioCpf(String cpf) throws  Exception {    
    	
        try 
        {    
        	Funcionario f = manager.find(Funcionario.class, cpf);
            return f;
            
        } catch (Exception ex) {        	
            	try 
            	{           	
            		utx.rollback();
                
            	} catch (Exception re) {
            	
            		throw new Exception("Cpf do Funcionario não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método de procurar funcionario pelo nome.
    
    public Funcionario procurarFuncionarioNome(String nome) throws  Exception {       
    	
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
            	
            		throw new Exception("Nome do Funcionario não Consta nos Nossos Registros"+re);               
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método para alterar cadastro de funcionario.
    
    public void alterarFuncionario(Funcionario f) throws  Exception {      
    	
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
            	
            		throw new Exception("Erro ao Tentar Alterar Cadastro do Funcionario "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Método de excluir cadastro de funcionário.
    
    public void excluirFuncionario(int id) throws Exception {
    	
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
            	
            		throw new Exception("Erro ao Tentar Excluir Cadastro do Funcionario "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Método de listar todos os funcionários cadastrados.
   
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
