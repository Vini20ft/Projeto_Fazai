package br.com.fazai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.PedidoDAO;
import br.com.fazai.model.Pedido;

@Service("pedidoService")
@Transactional(readOnly = true)
public class PedidoServiceImpl implements PedidoService {
		
	@Autowired
	 private PedidoDAO pedidoDAO;

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}


	@Override
	@Transactional(readOnly = false)
	public void salvarPedido(Pedido pedido) {
		this.pedidoDAO.salvarPedido(pedido);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarPedido(Pedido pedido) {
		this.pedidoDAO.alterarPedido(pedido);
		
	}

	@Override
	public Pedido consultarPedidoPorCodigo(int codigo) {
		return this.pedidoDAO.consultarPedidoPorCodigo(codigo);
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.pedidoDAO.Excluir(codigo);
		
	}

	@Override
	public List<Pedido> todosPedidos() {	
		return this.pedidoDAO.todosPedidos();
	}
}
