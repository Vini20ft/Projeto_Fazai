package br.com.fazai.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import br.com.fazai.model.Pedido;


@Repository
public class PedidoDAOImpl extends UtilJpaSpring implements PedidoDAO {
	
	@Override

	public void salvarPedido(Pedido pedido) {
		getManager().persist(pedido);
		
	}

	@Override

	public void alterarPedido(Pedido pedido) {	
		getManager().merge(pedido);
	}

	@Override
	public Pedido consultarPedidoPorCodigo(int codigo) {
		return getManager().find(Pedido.class, codigo);
	}

	@Override
	public void Excluir(int codigo) {
		
		Pedido pedido = this.consultarPedidoPorCodigo(codigo);
		 
		getManager().remove(pedido);
 
		
	}

	@Override
	public List<Pedido> TodosPedidos() {
		return getManager().createQuery("SELECT c FROM Pedido c ORDER BY c.numero_pedido ", Pedido.class).getResultList();
	}

}
