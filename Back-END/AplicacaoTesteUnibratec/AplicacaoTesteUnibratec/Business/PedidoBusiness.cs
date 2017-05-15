using System.Linq;
using System.Collections.Generic;
using System;

namespace AplicacaoTesteUnibratec.Business
{
    internal class PedidoBusiness : IPedidoBussiness
    {
        private TesteUnibratecDBEntities db;
        private IItemPedidoBusiness itemPedido;

        public PedidoBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public PedidoBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }
        public void Delete(int id)
        {
            Pedido item = db.Pedido.Find(id);
            List<ItemPedido> listapedido = this.itemPedido.GetAllForidpedido(id);
            if (!listapedido.Any() && listapedido != null)
            {
                this.itemPedido.DeleteAll(id);
                db.Pedido.Remove(item);
                db.SaveChanges();
            }
            else { 
            
                db.Pedido.Remove(item);
                db.SaveChanges();
            }
        }

        public List<Pedido> GetAll(string listadepedidos)
        {
            List<int> listaIdItens = listadepedidos.Split('|').Select(a => Convert.ToInt32(a)).ToList();
            List<Pedido> lista = (from pedido in db.Pedido where listaIdItens.Contains(pedido.Id) select pedido).ToList();
            return lista;
        }

        public Pedido GetForId(int id)
        {
            return db.Pedido.Find(id);
        }

        public void Insert(Pedido pedido)
        {
            db.Pedido.Add(pedido);
            db.SaveChanges();
        }

        public void Update(Pedido pedido)
        {
            Pedido pedidooriginal = db.Pedido.Find(pedido.Id);
            db.Entry(pedidooriginal).CurrentValues.SetValues(pedido);
            db.SaveChanges();
        }

    }
}