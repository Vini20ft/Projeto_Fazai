using System.Linq;
using System.Collections.Generic;
using System;
using AplicacaoTesteUnibratec.Models.Mobile;

namespace AplicacaoTesteUnibratec.Business
{
    internal class FoodTruckBusiness : IFoodTruckBusiness
    {
        private TesteUnibratecDBEntities db;

        public FoodTruckBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public FoodTruckBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public void Delete(int id)
        {
            FoodTruck item = db.FoodTruck.Find(id);
            db.FoodTruck.Remove(item);
            db.SaveChanges();
        }

        public List<FoodTruck> GetAll(string listaIdFoodTruckFuncionario)
        {
            List<int> listaIdFoodTruck = listaIdFoodTruckFuncionario.Split('|').Select(a => Convert.ToInt32(a)).ToList();
            List<FoodTruck> lista = (from foodTruck in db.FoodTruck where listaIdFoodTruck.Contains(foodTruck.Id) select foodTruck).ToList();
            return lista;
        }

        public FoodTruck GetForId(int id)
        {
            return db.FoodTruck.Find(id);
        }

        public void Insert(FoodTruck foodTruck)
        {
            db.FoodTruck.Add(foodTruck);
            db.SaveChanges();
        }

        public void Update(FoodTruck foodTruck)
        {
            FoodTruck foodTruckOriginal = db.FoodTruck.Find(foodTruck.Id);
            db.Entry(foodTruckOriginal).CurrentValues.SetValues(foodTruck);
            db.SaveChanges();
        }

        #region Region - Mobile

        public List<Estabelecimento> GetAllMobile(string cidade)
        {
            cidade = cidade ?? "";
            List<FoodTruck> lista = (from foodTruck in db.FoodTruck
                                     where string.IsNullOrEmpty(cidade) ||
                                     foodTruck.Cidade.ToUpper().Trim().StartsWith(cidade.ToUpper().Trim())
                                     select foodTruck).ToList();
            var listaMobile = CriarListaRetornoMobile(lista);
            return listaMobile;
        }

        public Estabelecimento GetForIdMobile(int id)
        {
            List<FoodTruck> lista = (from foodTruck in db.FoodTruck
                                     where  foodTruck.Id == id
                                     select foodTruck).ToList();
            var listaMobile = CriarListaRetornoMobile(lista);
            return listaMobile.FirstOrDefault();
        }

        private List<Estabelecimento> CriarListaRetornoMobile(List<FoodTruck> lista)
        {
            List<Estabelecimento> listaRetorno = new List<Estabelecimento>();

            foreach (var item in lista)
            {
                Estabelecimento estabelecimento = new Estabelecimento();
                estabelecimento.id = item.Id;
                estabelecimento.cnpj = item.CNPJ;
                estabelecimento.especialidade = item.Especialidade;
                estabelecimento.foto = item.Foto;
                estabelecimento.nome = item.Nome;
                estabelecimento.rating = item.Rating == null ? 0 : Convert.ToDouble(item.Rating);
                estabelecimento.razaoSocial = item.RazaoSocial;
                estabelecimento.telefone = item.Telefone;
                estabelecimento.endereco = new Endereco();
                estabelecimento.endereco.bairro = item.Bairro;
                estabelecimento.endereco.cidade = item.Cidade;
                estabelecimento.endereco.estado = item.Estado;
                estabelecimento.endereco.numero = item.Numero;
                estabelecimento.endereco.rua = item.Rua;
                estabelecimento.endereco.localizacao = new Localizacao();
                estabelecimento.endereco.localizacao.latitude = string.IsNullOrEmpty(item.Latitude) ? 0 : Convert.ToInt32(item.Latitude);
                estabelecimento.endereco.localizacao.longitude = string.IsNullOrEmpty(item.Longitude) ? 0 : Convert.ToInt32(item.Longitude);
                listaRetorno.Add(estabelecimento);
            }

            return listaRetorno;
        }

        #endregion
    }
}