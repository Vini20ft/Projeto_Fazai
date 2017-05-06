package br.com.fazai.dao;

import java.util.List;

import br.com.fazai.model.Pedido;

public interface PedidoDAO {

	void Excluir(int codigo);
	Pedido consultarPedidoPorCodigo(int codigo);
	void alterarPedido(Pedido pedido);
	void salvarPedido(Pedido pedido);
	List<Pedido> TodosPedidos();

}
