package br.com.fazai.service;

import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.fazai.model.Funcionario;

public interface FuncionarioService {

	void salvarFuncionario(Funcionario funcionario);
	Funcionario loginFuncionario(Funcionario funcionario);
	void esqueciSenhaFuncionario(Funcionario funcionario) throws EmailException;
	void alterarFuncionario(Funcionario funcionario);
	Funcionario consultarFuncionarioPorCodigo(int codigo);
	void Excluir(int codigo);
	List<Funcionario> TodosFuncionarios();

}
