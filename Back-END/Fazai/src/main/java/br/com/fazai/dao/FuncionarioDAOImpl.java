package br.com.fazai.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Repository;

import br.com.fazai.model.Funcionario;

@Repository
public class FuncionarioDAOImpl extends UtilJpaSpring implements FuncionarioDAO{

	
	@Override

	public void salvarFuncionario(Funcionario funcionario) {
		getManager().persist(funcionario);
		
	}
	
	@Override
	public Funcionario loginFuncionrio(Funcionario funcionario) {
		
		try{
			
			Funcionario f = (Funcionario)
            getManager().createQuery(
            "SELECT f FROM Funcionario f WHERE login = :login and senha = :senha")
            .setParameter("login", funcionario.getLogin())
            .setParameter("senha", funcionario.getSenha()).getSingleResult();
            return f;
            
			
		}catch (NoResultException e){
			
			System.out.print("Nome de Funcionario ou Senha Inválidos!!! " + e);
            return null;
			
		}
		
	}
	
	@Override
	public void esqueciSenhaFuncionario(Funcionario funcionario) throws EmailException {
		
		try {
    		
			Funcionario f = (Funcionario)
		            getManager().createQuery(
		            "SELECT f FROM Funcionario f WHERE email_funcionario = :email_funcionario")
		            .setParameter("email_funcionario", funcionario.getEmail_funcionario()).getSingleResult();
        	   
        	   if(f.getEmail_funcionario() == funcionario.getEmail_funcionario()){
        		   
        		   SimpleEmail mEmail = new SimpleEmail();
              		//Utilize o hostname do seu provedor de email
              		System.out.println("alterando hostname...");
              		mEmail.setHostName("smtp.gmail.com");
              		//Quando a porta utilizada não é a padrão (gmail = 465)
              		mEmail.setSmtpPort(465);
              		//Adicione os destinatários
              		mEmail.addTo(f.getEmail_funcionario(), f.getNome_funcionario());
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
              		
              		/*getManager().createQuery(
   						"UPDATE Funcionario SET senha = :senha WHERE email_funcionario = :email_funcionario")
   						.setParameter("senha", funcionario.getSenha())
   						.setParameter("email_funcionario", funcionario.getEmail_funcionario()).getSingleResult();
   			
   				System.out.print("Senha de Funcionario Alterada com Sucesso!!! ");*/
        	   }
           		
           
            
      } catch (NoResultException e) {
      	
      	  System.out.print("Erro ao Tentar Locarlizar Email de Funcionário " + e);
      	  
      }
    	
		
	}

	@Override
	public void alterarFuncionario(Funcionario funcionario) {	
		getManager().merge(funcionario);
	}

	@Override
	public Funcionario consultarFuncionarioPorCodigo(int codigo) {
		return getManager().find(Funcionario.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		Funcionario funcionario = this.consultarFuncionarioPorCodigo(codigo);
		 
		getManager().remove(funcionario);
 
		
	}

	@Override
	public List<Funcionario> TodosFuncionarios() {
		return getManager().createQuery("SELECT c FROM Funcionario c ORDER BY c.nome_funcionario ", Funcionario.class).getResultList();
	}

}
