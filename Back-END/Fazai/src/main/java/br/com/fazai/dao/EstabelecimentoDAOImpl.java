package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.model.Estabelecimento;
@Repository("estabelecimentoDAO")
public class EstabelecimentoDAOImpl extends UtilJpaSpring implements EstabelecimentoDAO{

	
	@Override
	public void salvarEstabelecimento(Estabelecimento estabelecimento) {
		getManager().persist(estabelecimento);
		
	}

	@Override
	public void alterarEstabelecimento(Estabelecimento estabelecimento) {	
		getManager().merge(estabelecimento);
	}

	@Override
	public Estabelecimento consultarEstabelecimentoPorCodigo(int codigo) {
		return getManager().find(Estabelecimento.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		Estabelecimento estabelecimento = this.consultarEstabelecimentoPorCodigo(codigo);
		 
		getManager().remove(estabelecimento);
 
		
	}

	@Override
	public List<Estabelecimento> TodosEstabelecimentos() {
		return getManager().createQuery("SELECT c FROM Estabelecimento c ORDER BY c.nome_estabelecimento ", Estabelecimento.class).getResultList();
	}

}
