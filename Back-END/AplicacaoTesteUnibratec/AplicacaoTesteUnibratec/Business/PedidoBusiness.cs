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
            if (item.ItemPedido.Count > 0)
            {
                itemPedido = new ItemPedidoBusiness(db);
                itemPedido.DeleteAll(id);
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
            //To do Lista de FoodTrucks - lembrar de dar um update na tabela
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
            //pedido = new Pedido();
            //pedido.Observacao = "rg esgrgse gersn grdg sh g  df";
            //pedido.Status = 1;
            //pedido.ItemPedido = new List<ItemPedido>();
            //pedido.ItemPedido.Add(new ItemPedido() { Quantidade = 1, IdItemCardapio = 41 });
            if (pedido.ItemPedido.Count <= 0)
                throw new ExceptionBusiness("Informe ao menos um item no Pedido");
            db.Pedido.Add(pedido);
            db.SaveChanges();
        }

        public void Update(int id, int status)
        {
            Pedido pedidooriginal = db.Pedido.Find(id);
            Pedido pedido = pedidooriginal;
            pedido.Status = status; 
            db.Entry(pedidooriginal).CurrentValues.SetValues(pedido);
            db.SaveChanges();
        }

    }
}