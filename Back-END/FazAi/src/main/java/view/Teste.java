package view;
 
import model.Funcionario;
import dao.FuncionarioDAO;

public class Teste{
 
    public static void main(String[] args) throws Exception {
    	
    	//Cliente c = new Cliente();
        
    	//c.setId(1);
        //c.setLogin("misaeljunior");
        //c.setNome("misaeljunior");
        //c.setSenha("123456");
        
        //ClienteDAO cli = new ClienteDAO();
        //cli.excluir(c.getId());
        //cli.inserir(c);
    	
    	Funcionario f = new Funcionario();
    	f.setLogin("misaeljunior");
    	//f.setNome("misaeljjunior");
    	f.setSenha("misaeljunior");
    	//f.setEmail("misaeljunior");
    	
    	FuncionarioDAO fun = new FuncionarioDAO();
    	fun.getFuncionario(f.getLogin(), f.getSenha());
    	//fun.inserir(f);	
    }
}