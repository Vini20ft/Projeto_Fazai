package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.model.Funcionario;

@Repository
public class FuncionarioDAOImpl extends UtilJpaSpring implements FuncionarioDAO{

	
	@Override

	public void salvarFuncionario(Funcionario funcionario) {
		getManager().persist(funcionario);
		
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
