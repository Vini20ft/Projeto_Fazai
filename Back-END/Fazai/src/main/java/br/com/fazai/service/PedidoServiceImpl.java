package br.com.fazai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fazai.dao.PedidoDAO;
import br.com.fazai.model.Pedido;

@Service ("pedidoservice")
@Transactional(readOnly = true)
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	 private PedidoDAO pedidodao;

	public void setPedidoDAO(PedidoDAO pedidodao) {
		this.pedidodao = pedidodao;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void salvarPedido(Pedido pedido) {
		this.pedidodao.salvarPedido(pedido);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void alterarPedido(Pedido pedido) {
		this.pedidodao.alterarPedido(pedido);
		
	}

	@Override
	public Pedido consultarPedidoPorCodigo(int codigo) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void Excluir(int codigo) {
		this.pedidodao.Excluir(codigo);
		
	}

	@Override
	public List<Pedido> TodosPedidos() {	
		return this.pedidodao.TodosPedidos();
	}
	

}
