package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Funcionario;

public interface FuncionarioService {

	void salvarFuncionario(Funcionario funcionario);

	void alterarFuncionario(Funcionario funcionario);

	Funcionario consultarFuncionarioPorCodigo(int codigo);

	void Excluir(int codigo);

	List<Funcionario> TodosFuncionarios();

}
