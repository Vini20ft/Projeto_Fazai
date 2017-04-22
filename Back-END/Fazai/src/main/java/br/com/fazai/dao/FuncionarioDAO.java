package br.com.fazai.dao;

import java.util.List;

import br.com.fazai.model.Funcionario;


public interface FuncionarioDAO {
	
	public void salvarFuncionario(Funcionario funcionario);
	public void alterarFuncionario(Funcionario funcionario);
	public Funcionario consultarFuncionarioPorCodigo(int codigo);
	public void Excluir(int codigo);
	public List<Funcionario> TodosFuncionarios();

}
