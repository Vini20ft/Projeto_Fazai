package br.com.fazai.service;


import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.FuncionarioDAO;
import br.com.fazai.model.Funcionario;


@Service ("funcionarioService")
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService{
	
	@Autowired
	 private FuncionarioDAO funcionarioDAO;

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void salvarFuncionario(Funcionario funcionario) {
		this.funcionarioDAO.salvarFuncionario(funcionario);
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public Funcionario loginFuncionario(Funcionario funcionario) {
		return this.funcionarioDAO.loginFuncionrio(funcionario);		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void esqueciSenhaFuncionario(Funcionario funcionario) throws EmailException{
		 this.funcionarioDAO.esqueciSenhaFuncionario(funcionario);
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarFuncionario(Funcionario funcionario) {
		this.funcionarioDAO.alterarFuncionario(funcionario);
		
	}

	@Override
	public Funcionario consultarFuncionarioPorCodigo(int codigo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.funcionarioDAO.Excluir(codigo);
		
	}

	@Override
	public List<Funcionario> TodosFuncionarios() {	
		return this.funcionarioDAO.TodosFuncionarios();
	}

}
