package controller;

import java.util.Properties;

import model.Funcionario;
import dao.FuncionarioDAO;

public class ControllerDadosEmail {
	
    public Funcionario buscarEmail(String email) throws  Exception{  
        try{
        	FuncionarioDAO fd = new FuncionarioDAO();  
            return fd.redefinirSenhaFuncionario(email);  
        }catch (Exception re){
        	System.out.print("Erro ao Tentar Locarlizar Email de Funcionário " + re);
            return null;
        }
    }  
            
    /********************************** Transporte ****************************************/  
    /*public boolean enviarEmail(String mailServer, String para, String de, String assunto, String mensagem, String login_instituicao, String senha_instituicao)  
    throws Exception{  
          
        try{  
            Properties mailProps = new Properties();  
              
            mailProps.put("mail.smtp.host",mailServer);  
            mailProps.put("mail.smtp.auth", "true"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.debug", "true"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.debug", "true"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.mime.charset", "ISO?8859?1"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.port", "465"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.starttls.enable", "true"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.socketFactory.port", "465"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.socketFactory.fallback", "false"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
              
            Session mailSession = Session.getDefaultInstance(mailProps);  
              
            //As duas linhas seguintes de código, colocam no formato de endereços,  
            //supostamente válidos, de email os dados passados pelos parâmetros to e from.  
            InternetAddress destinatario = new InternetAddress(para);  
            InternetAddress remetente = new InternetAddress(de);  
              
            //As duas linhas de código a seguir, são responsáveis por setar os atributos e  
            //propriedades necessárias do objeto message para que o email seja enviado.  
            //inicialização do objeto Message  
            Message message = new MimeMessage(mailSession);  
              
            //Definição da Data que está enviando o email  
            message.setSentDate(new Date());//novo  
              
            //Definição de quem está enviando o email  
            message.setFrom(remetente);  
              
            //define o(s) destinatário(s) e qual o tipo do destinatário.  
            //os possíveis tipos de destinatário: TO, CC, BCC  
            message.setRecipient(Message.RecipientType.TO, destinatario);  
              
            //definição do assunto do email  
            message.setSubject(assunto);  
              
            //definição do conteúdo da mensagem e do tipo da mensagem  
            message.setContent(mensagem.toString(), "text/plain"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
              
            //as linhas de código seguinte é a responsável pelo envio do email  
            Transport transport = mailSession.getTransport("smtp"<img src="http://javafree.uol.com.br/forum/images/smiles/icon_wink.gif">;  
            transport.connect(mailServer, login_instituicao, senha_instituicao);  
            message.saveChanges();  
            transport.sendMessage(message, message.getAllRecipients());  
            transport.close();  
              
            return true;  
            
        }catch (Exception e){  
            return false;  
        }  
    } */ 

}
