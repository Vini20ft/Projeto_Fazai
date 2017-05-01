package br.com.fazai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.EstabelecimentoDAO;
import br.com.fazai.model.Estabelecimento;

@Service("estabelecimentoService")
@Transactional(readOnly = true)
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    @Autowired
    private EstabelecimentoDAO estabelecimentoDAO;

    public void setEstabelecimentoDAO(EstabelecimentoDAO estabelecimentoDAO) {
	this.estabelecimentoDAO = estabelecimentoDAO;
    }

    @Override
    @Transactional(readOnly = false)
    public void salvarEstabelecimento(Estabelecimento estabelecimento) {
	this.estabelecimentoDAO.salvarEstabelecimento(estabelecimento);

    }

    @Override
    @Transactional(readOnly = false)
    public void alterarEstabelecimento(Estabelecimento estabelecimento) {
	this.estabelecimentoDAO.alterarEstabelecimento(estabelecimento);

    }

    @Override
    public Estabelecimento consultarEstabelecimentoPorCodigo(int codigo) {
	return this.estabelecimentoDAO.consultarEstabelecimentoPorCodigo(codigo);
    }

    @Override
    @Transactional(readOnly = false)
    public void Excluir(int codigo) {
	this.estabelecimentoDAO.Excluir(codigo);

    }

    @Override
    public List<Estabelecimento> todosEstabelecimentos() {
	return this.estabelecimentoDAO.todosEstabelecimentos();
    }

    @Override
    public List<Estabelecimento> todosEstabelecimentosPorCidade(String cidade) {

	return this.estabelecimentoDAO.todosEstabelecimentosPorCidade(cidade);
    }

}
