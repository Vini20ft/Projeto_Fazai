package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Estabelecimento;

public interface EstabelecimentoService {
	

	public void salvarEstabelecimento(Estabelecimento estabelecimento);
	public void alterarEstabelecimento(Estabelecimento estabelecimento);
	public Estabelecimento consultarEstabelecimentoPorCodigo(int codigo);
	public void Excluir(int codigo);
	public List<Estabelecimento> TodosEstabelecimentos();

}
