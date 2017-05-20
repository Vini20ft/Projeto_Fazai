using System.Transactions;
using System.Linq;
using System.Collections.Generic;
using System;
using AplicacaoTesteUnibratec.Models.Mobile;

namespace AplicacaoTesteUnibratec.Business
{
    internal class CardapioBusiness : ICardapioBusiness
    {
        private TesteUnibratecDBEntities db;

        public CardapioBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public CardapioBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public void Delete(int id)
        {
            IItemCardapioBusiness itemCardapioBusiness = new ItemCardapioBusiness(db);
            itemCardapioBusiness.DeleteAll(id);
            Cardapio item = db.Cardapio.Find(id);
            db.Cardapio.Remove(item);
            db.SaveChanges();
        }

        public List<Cardapio> GetAll(string listaIdFoodTruckFuncionario)
        {
            List<int> listaIdFoodTruck = listaIdFoodTruckFuncionario.Split('|').Select(a => Convert.ToInt32(a)).ToList();
            List<Cardapio> lista = (from cardapio in db.Cardapio where listaIdFoodTruck.Contains(cardapio.IdFoodTruck) select cardapio).ToList();
            return lista;
        }

        public Cardapio GetForId(int id)
        {
            Cardapio cardapio = db.Cardapio.Find(id);
            return cardapio;
        }

        public void Insert(Cardapio cardapio)
        {
            cardapio.FoodTruck = null;
            ValidarCardapioFoodTruck(cardapio.IdFoodTruck);

            using (TransactionScope scope = new TransactionScope())
            {
                db.Cardapio.Add(cardapio);
                db.SaveChanges();
                scope.Complete();
            }
        }

        public void Update(Cardapio cardapio)
        {
            cardapio.FoodTruck = null;
            ValidarCardapioFoodTruck(cardapio.IdFoodTruck, cardapio.Id);

            using (TransactionScope scope = new TransactionScope())
            {
                Cardapio cardapioOriginal = db.Cardapio.Find(cardapio.Id);
                db.Entry(cardapioOriginal).CurrentValues.SetValues(cardapio);
                db.SaveChanges();

                IItemCardapioBusiness itemCardapioBusiness = new ItemCardapioBusiness(db);
                itemCardapioBusiness.InsertAll(cardapio);
                scope.Complete();
            }
        }

        private void ValidarCardapioFoodTruck(int idFoodTruck, int idCardapio = 0)
        {
            Cardapio cardapio = GetForIdFoodTruck(idFoodTruck);
            if (cardapio != null && idCardapio != cardapio.Id)
                throw new ExceptionBusiness("O " + cardapio.FoodTruck.Nome + " já possui um cardápio.");
        }

        private Cardapio GetForIdFoodTruck(int idFoodTruck)
        {
            Cardapio cardapio = (from card in db.Cardapio where card.IdFoodTruck == idFoodTruck select card).FirstOrDefault();
            return cardapio;
        }

        #region Region - Mobile

        public Models.Mobile.Cardapio GetForIdFoodTruckMobile(int idFoodTruck)
        {
            List<Cardapio> lista = (from cardapio in db.Cardapio
                                    where cardapio.IdFoodTruck == idFoodTruck
                                    select cardapio).ToList();
            var listaMobile = CriarListaRetornoMobile(lista);
            return listaMobile.FirstOrDefault();
        }

        public ItensCardapio GetForIdItemCardapioMobile(int idItemCardapio)
        {
            List<ItemCardapio> lista = (from itemCardapio in db.ItemCardapio
                                    where itemCardapio.Id == idItemCardapio
                                    select itemCardapio).ToList();
            var itemMobile = CriarListaRetornoItemMobile(lista.FirstOrDefault());
            return itemMobile;
        }

        private List<Models.Mobile.Cardapio> CriarListaRetornoMobile(List<Cardapio> lista)
        {
            List<Models.Mobile.Cardapio> listaRetorno = new List<Models.Mobile.Cardapio>();

            foreach (var item in lista)
            {
                var cardapio = new Models.Mobile.Cardapio();
                cardapio.id = item.Id;
                cardapio.descricao = item.Observacao;
                cardapio.itens_cardapio = new List<Models.Mobile.ItensCardapio>();
                foreach (var itemCard in item.ItemCardapio)
                {
                    var itemcardapio = CriarListaRetornoItemMobile(itemCard);
                    cardapio.itens_cardapio.Add(itemcardapio);
                }

                listaRetorno.Add(cardapio);
            }

            return listaRetorno;
        }

        private ItensCardapio CriarListaRetornoItemMobile(ItemCardapio item)
        {
            ItensCardapio retorno = new ItensCardapio();
            retorno.codigo = item.Id;
            retorno.descricao = item.Observacao;
            retorno.imagem = "http://40.114.51.8/servico/upload/" + item.Foto;
            retorno.nome = item.Nome;
            retorno.tempo_estimado = string.IsNullOrEmpty(item.TempoEstimado) ? 0 : Convert.ToInt32(item.TempoEstimado);
            retorno.valor = item.Preco == null ? 0 : Convert.ToDouble(item.Preco);
            return retorno;
        }

        #endregion
    }
}