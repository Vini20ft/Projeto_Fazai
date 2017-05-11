using System.Linq;
using System.Collections.Generic;
using System;

namespace AplicacaoTesteUnibratec.Business
{
    internal class ItemCardapioBusiness : IItemCardapioBusiness
    {
        private TesteUnibratecDBEntities db;

        public ItemCardapioBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public ItemCardapioBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public List<ItemCardapio> GetAllForIdCardapio(int id)
        {
            List<ItemCardapio> lista = (from itemCardapio in db.ItemCardapio where itemCardapio.IdCardapio == id select itemCardapio).ToList();
            return lista;
        }

        public void InsertAll(Cardapio cardapio)
        {
            if (cardapio.ItemCardapio.Count <= 0)
                throw new ExceptionBusiness("Informe ao menos um item para o cardápio.");

            DeleteAll(cardapio.Id);
            foreach (var item in cardapio.ItemCardapio)
            {
                item.IdCardapio = cardapio.Id;
                db.ItemCardapio.Add(item);
            }
            db.SaveChanges();
        }

        public void DeleteAll(int idCardapio)
        {
            List<ItemCardapio> lista = GetAllForIdCardapio(idCardapio);
            foreach (var item in lista)
                db.ItemCardapio.Remove(item);
            db.SaveChanges();
        }

        public void Delete(int id)
        {
            ItemCardapio item = db.ItemCardapio.Find(id);
            db.ItemCardapio.Remove(item);
            db.SaveChanges();
        }
    }
}