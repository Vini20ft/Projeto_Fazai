package br.com.fazai.service;

import java.util.List;

import br.com.fazai.model.Pedido;

public interface PedidoService {

	List<Pedido> TodosPedidos();

	void Excluir(int codigo);

	Pedido consultarPedidoPorCodigo(int codigo);

	void alterarPedido(Pedido pedido);

	void salvarPedido(Pedido pedido);

}
