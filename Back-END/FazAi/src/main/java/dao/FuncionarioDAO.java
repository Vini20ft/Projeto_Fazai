package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.commons.mail.SimpleEmail;

import model.Funcionario;
import util.UtilJPA;

public class FuncionarioDAO {
	
	public FuncionarioDAO(){}
	
	private UserTransaction utx = null;
    
    UtilJPA utiljpa = new UtilJPA();
	EntityManager manager = UtilJPA.getEntityManager();
	
	//Metodo de login de funcionario.
	public Funcionario loginFuncionario(String login, String senha) {
		
        try {
              Funcionario f = (Funcionario) manager
                         .createQuery(
                    		 "SELECT f FROM Funcionario f WHERE login = :login and senha = :senha")
                         .setParameter("login", login)
                         .setParameter("senha", senha).getSingleResult();
              return f;
              
        } catch (NoResultException e) {
        	
        	  System.out.print("Nome de Funcionario ou Senha Inválidos!!! " + e);
              return null;
        }
	}
	
	//Método de redefinição de senha.
	public Funcionario redefinirSenhaFuncionario(String email) throws  Exception {       
    	
    	try {
    		
            Funcionario f = (Funcionario) manager
                       .createQuery(
                  		 "SELECT f FROM Funcionario f WHERE email = :email ")
                       .setParameter("email", email).getSingleResult();
            
            if(f.getEmail() == email){
            	
            	SimpleEmail mEmail = new SimpleEmail();
            	//Utilize o hostname do seu provedor de email
            	System.out.println("alterando hostname...");
            	mEmail.setHostName("smtp.gmail.com");
            	//Quando a porta utilizada não é a padrão (gmail = 465)
            	mEmail.setSmtpPort(465);
            	//Adicione os destinatários
            	mEmail.addTo(f.getEmail(), f.getNome());
            	//Configure o seu email do qual enviará
            	mEmail.setFrom("grupofazai@gmail.com", "Grupo Faz Aí");
            	//Adicione um assunto
            	mEmail.setSubject("Redefinição de Senha do Faz Aí");
            	//Adicione a mensagem do email
            	mEmail.setMsg("Redefina Sua Senha Através do Link... ");
            	//Para autenticar no servidor é necessário chamar os dois métodos abaixo
            	System.out.println("autenticando...");
            	mEmail.setSSL(true);
            	mEmail.setAuthentication("grupofazai@gmail.com", "123fazai456");
            	System.out.println("enviando...");
            	mEmail.send();
            	System.out.println("Email enviado!");
            	
            }else{
            	
            }
            		
            		
                	
            	
            return null;
            
      } catch (NoResultException e) {
      	
      	  System.out.print("Erro ao Tentar Locarlizar Email de Funcionário " + e);
          return null;
      }
    	
    }

	//Metodo de inserir cadastro de funcionario.
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
            	
                throw new Exception("Erro ao Tentar Cadastrar "+f.getNome()+re);           
            }
            
            throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Metodo de procurar cadastro de funcionario por codigo.
    public Funcionario procurarFuncionarioId(int id) throws  Exception {    
    	
        try 
        {    
        	Funcionario f = manager.find(Funcionario.class, id);
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
    
    //Metodo de procurar cadastro de funcionario por nome.
    public Funcionario procurarFuncionarioNome(String nome) throws  Exception {       
    	
    	try {
    		
            Funcionario f = (Funcionario) manager
                       .createQuery(
                  		 "SELECT f FROM Funcionario f WHERE nome = :nome ")
                       .setParameter("nome", nome).getSingleResult();           
            return f;
            
      } catch (NoResultException e) {
      	
      	  System.out.print("Funcionário Não Localizado !!! " + e);
          return null;
      }
    }
    
    //Metodo de alterar cadastro de funcionario.
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
            	
            		throw new Exception("Erro ao Tentar Alterar "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
        	if (manager != null) {
        		manager.close();
            	
            }
        }
    }
    
    //Metodo de excluir cadastro de funcionarino.
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
            	
            		throw new Exception("Erro ao Tentar Excluir "+f.getNome()+re);
                
            	}
            
            	throw ex;
            
        } finally {
        	
            if (manager != null) {
            	manager.close();
            	
            }
        }
    }
    
    //Metodo de listar todos os cadastro de funcionario.
	public List<Funcionario> listarFuncionario()throws Exception{
		
		try{
			
			manager.getEntityManagerFactory();
			Query query = manager.createQuery("from Funcionario");
			@SuppressWarnings("unchecked")
			List<Funcionario> f = query.getResultList();
			return f;
			
		} catch (Exception re) {
        	
    		throw new Exception("Erro ao Tentar Listar "+re);
        
    	}
		finally{
			manager.close();
		}
	}
}
