using System.Linq;
using System.Collections.Generic;
using System;
using AplicacaoTesteUnibratec.Models.Mobile;
using System.Globalization;
using System.Transactions;

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

        public void Delete(int id, string email)
        {
            using (TransactionScope scope = new TransactionScope())
            {
                FoodTruck item = db.FoodTruck.Find(id);

                FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness(db);

                var funcionario = funcionarioBusiness.GetFuncionarioByEmail(email);
                if (funcionario == null)
                    throw new ExceptionBusiness("Ocorreu um erro ao salvar o FoodTruck favor tente mais tarde, caso o erro continue entre em contato a área responsável pelo sistema.");

                if (funcionario.FoodTruckFuncionario.Count <= 1)
                    throw new ExceptionBusiness("Você deve possuir ao menos um FoodTruck cadastrato.");

                foreach (var itemFF in item.FoodTruckFuncionario.ToList())
                {
                    itemFF.Funcionario = null;
                    itemFF.FoodTruck = null;
                    db.FoodTruckFuncionario.Remove(itemFF);
                }

                db.FoodTruck.Remove(item);
                db.SaveChanges();
                scope.Complete();
            }
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
            VerificarFoodTruckExiste(foodTruck);
            FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness(db);

            if (!string.IsNullOrEmpty(foodTruck.EmailFuncionario))
            {
                var funcionario = funcionarioBusiness.GetFuncionarioByEmail(foodTruck.EmailFuncionario);
                if (funcionario == null)
                    throw new ExceptionBusiness("Ocorreu um erro ao salvar o FoodTruck favor tente mais tarde, caso o erro continue entre em contato a área responsável pelo sistema.");

                foodTruck.FoodTruckFuncionario = new List<FoodTruckFuncionario>();
                foodTruck.FoodTruckFuncionario.Add(new FoodTruckFuncionario() { IdFuncionario = funcionario.Id });
            }

            db.FoodTruck.Add(foodTruck);
            db.SaveChanges();
        }

        public void Update(FoodTruck foodTruck)
        {
            VerificarFoodTruckExiste(foodTruck);

            FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness(db);
            var funcionario = funcionarioBusiness.GetFuncionarioByEmail(foodTruck.EmailFuncionario);
            if (funcionario == null)
                throw new ExceptionBusiness("Ocorreu um erro ao salvar o FoodTruck favor tente mais tarde, caso o erro continue entre em contato a área responsável pelo sistema.");

            FoodTruck foodTruckOriginal = db.FoodTruck.Find(foodTruck.Id);
            db.Entry(foodTruckOriginal).CurrentValues.SetValues(foodTruck);
            db.SaveChanges();
        }

        private void VerificarFoodTruckExiste(FoodTruck foodTruck)
        {
            var foodTruckBD = (from foodT in db.FoodTruck
                               join ff in db.FoodTruckFuncionario on foodT.Id equals ff.IdFoodTruck
                               join func in db.Funcionario on ff.IdFuncionario equals func.Id
                               where foodT.Nome.ToUpper().Trim().Equals(foodTruck.Nome.ToUpper().Trim()) &&
                               foodT.Id != foodTruck.Id && func.Email.ToUpper().Trim().Equals(foodTruck.EmailFuncionario.ToUpper().Trim())
                               select foodT).FirstOrDefault();

            if (foodTruckBD != null)
                throw new ExceptionBusiness("Já existe um FoodTruck com o nome " + foodTruck.Nome);
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
                                     where foodTruck.Id == id
                                     select foodTruck).ToList();
            var listaMobile = CriarListaRetornoMobile(lista);
            return listaMobile.FirstOrDefault();
        }

        private List<Estabelecimento> CriarListaRetornoMobile(List<FoodTruck> lista)
        {
            List<Estabelecimento> listaRetorno = new List<Estabelecimento>();

            foreach (var item in lista)
            {
                try
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
                    estabelecimento.endereco.localizacao.latitude = string.IsNullOrEmpty(item.Latitude) ? 0 : Convert.ToDecimal(item.Latitude, CultureInfo.GetCultureInfo("en-US"));
                    estabelecimento.endereco.localizacao.longitude = string.IsNullOrEmpty(item.Longitude) ? 0 : Convert.ToDecimal(item.Longitude, CultureInfo.GetCultureInfo("en-US"));
                    listaRetorno.Add(estabelecimento);
                }
                catch (Exception)
                {
                }
            }

            return listaRetorno;
        }

        public void AvaliarFoodTruck(int id, int rating)
        {
            using (TransactionScope scope = new TransactionScope())
            {
                db.AvaliacaoFoodTruck.Add(new AvaliacaoFoodTruck() { IdFoodTruck = id, Rating = rating, FoodTruck = null });
                var avaliacoes = (from ratingDB in db.AvaliacaoFoodTruck where ratingDB.IdFoodTruck == id select ratingDB.Rating).ToList();

                decimal mediaAvaliacoes = rating;
                if (avaliacoes != null && avaliacoes.Count > 0)
                    mediaAvaliacoes = (decimal)avaliacoes.Average();

                var foodTruck = GetForId(id);
                foodTruck.Rating = mediaAvaliacoes;
                FoodTruck foodTruckOriginal = db.FoodTruck.Find(foodTruck.Id);
                db.Entry(foodTruckOriginal).CurrentValues.SetValues(foodTruck);
                db.SaveChanges();
                scope.Complete();
            }
        }

        #endregion
    }
}