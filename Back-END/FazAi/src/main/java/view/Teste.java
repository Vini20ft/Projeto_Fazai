package view;

import model.Funcionario;
import dao.FuncionarioDAO;


public class Teste{
 
    public static void main(String[] args) throws Exception {
    	
    	Funcionario f = new Funcionario();
    	f.setNome("MisaelJunior");
    	f.setEmail("junioribeiro-2009@hotmail.com");
    	f.setLogin("MisaelJunior");
    	f.setSenha("123456");
    	
    	FuncionarioDAO fd = new FuncionarioDAO();
    	fd.redefinirSenhaFuncionario(f.getEmail());
    }
}