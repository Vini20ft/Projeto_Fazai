package br.com.fazai.dao;

import java.util.List;

import br.com.fazai.model.Pedido;


public interface PedidoDAO {
	
	public void salvarPedido(Pedido pedido);
	public void alterarPedido(Pedido pedido);
	public Pedido consultarPedidoPorCodigo(int codigo);
	public void Excluir(int codigo);
	public List<Pedido> todosPedidos();

}
