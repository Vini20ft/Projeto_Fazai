using System.Linq;
using System.Collections.Generic;
using System;

namespace AplicacaoTesteUnibratec.Business
{
    internal class ItemPedidoBusiness : IItemPedidoBusiness
    {
        private TesteUnibratecDBEntities db;

        public ItemPedidoBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public ItemPedidoBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public void Delete(int id)
        {
            ItemPedido item = db.ItemPedido.Find(id);
            db.ItemPedido.Remove(item);
            db.SaveChanges();
        }

        public void DeleteAll(int idPedido)
        {
            List<ItemPedido> lista = GetAllForidpedido(idPedido);
            foreach (var item in lista)
                db.ItemPedido.Remove(item);
            db.SaveChanges();

        }

        public List<ItemPedido> GetAllForidpedido(int listadeitempedidos)
        {
            List<ItemPedido> lista = (from ItemPedido in db.ItemPedido where ItemPedido.IdPedido == listadeitempedidos select ItemPedido).ToList();
            return lista;
        }

        public ItemPedido GetForId(int id)
        {
            return db.ItemPedido.Find(id);
        }

        public void Insert(ItemPedido pedido)
        {
            db.ItemPedido.Add(pedido);
            db.SaveChanges();
        }

        public void Update(ItemPedido pedido)
        {
            ItemPedido pedidooriginal = db.ItemPedido.Find(pedido.Id);
            db.Entry(pedidooriginal).CurrentValues.SetValues(pedido);
            db.SaveChanges();
        }
    }
}