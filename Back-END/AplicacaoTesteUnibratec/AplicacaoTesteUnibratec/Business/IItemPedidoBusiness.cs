using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface IItemPedidoBusiness
    {
        List<ItemPedido> GetAllForidpedido(int listadeitempedidos);
        void Insert(ItemPedido pedido);
        void Delete(int id);
        void Update(ItemPedido pedido);
        ItemPedido GetForId(int id);
        void DeleteAll(int idCardapio);
    }
}