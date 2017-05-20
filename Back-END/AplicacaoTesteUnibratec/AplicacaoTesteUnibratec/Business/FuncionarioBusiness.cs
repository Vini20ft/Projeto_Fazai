using System.Linq;
using System.Collections.Generic;
using System;
using System.Data.Entity.Infrastructure;

namespace AplicacaoTesteUnibratec.Business
{
    internal class FuncionarioBusiness : IFuncionarioBusiness
    {
        private TesteUnibratecDBEntities db;

        public FuncionarioBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public FuncionarioBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public void Delete(int id)
        {
            try
            {
                var itensFoodTruckFuncionario = db.FoodTruckFuncionario.Where(a => a.IdFuncionario == id).ToList();
                foreach (var itemFoodTruckFuncionario in itensFoodTruckFuncionario)
                    db.FoodTruckFuncionario.Remove(itemFoodTruckFuncionario);
                Funcionario item = db.Funcionario.Find(id);
                db.Funcionario.Remove(item);
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                throw new ExceptionBusiness("O registro que você tentou excluir possui dados relacionados");
            }
        }

        public Funcionario Entrar(Funcionario funcionario)
        {
            var funcionarioBD = (from func in db.Funcionario
                                 where func.Email.ToUpper().Trim().Equals(funcionario.Email.ToUpper().Trim()) &&
                                 func.Senha.ToUpper().Trim().Equals(funcionario.Senha.ToUpper().Trim())
                                 select func).FirstOrDefault();

            if (funcionarioBD == null)
                throw new ExceptionBusiness("Usuário e senha inválido.");

            return funcionarioBD;
        }

        public Funcionario GetFuncionarioByEmail(string email)
        {
            var funcionarioBD = (from func in db.Funcionario
                                 where func.Email.ToUpper().Trim().Equals(email.ToUpper().Trim())
                                 select func).FirstOrDefault();
            return funcionarioBD;
        }

        public List<Funcionario> GetAll(string listaIdFoodTruckFuncionario)
        {
            List<int> listaIdFoodTruck = listaIdFoodTruckFuncionario.Split('|').Select(a => Convert.ToInt32(a)).ToList();
            List<Funcionario> lista = (from funcionario in db.Funcionario
                                       join foodTruckFuncionario in db.FoodTruckFuncionario on funcionario.Id equals foodTruckFuncionario.IdFuncionario
                                       where listaIdFoodTruck.Contains(foodTruckFuncionario.IdFoodTruck)
                                       select funcionario).Distinct().ToList();
            return lista;
        }

        public Funcionario GetForId(int id)
        {
            Funcionario funcionario = db.Funcionario.Find(id);
            return funcionario;
        }

        public void Insert(Funcionario funcionario)
        {
            VerificarUsuarioExiste(funcionario);
            db.Funcionario.Add(funcionario);
            db.SaveChanges();
        }

        public void Update(Funcionario funcionario)
        {
            VerificarUsuarioExiste(funcionario);
            Funcionario funcionarioOriginal = db.Funcionario.Find(funcionario.Id);
            db.Entry(funcionarioOriginal).CurrentValues.SetValues(funcionario);
            db.SaveChanges();
        }

        private void VerificarUsuarioExiste(Funcionario funcionario)
        {
            var funcionarioBD = (from func in db.Funcionario
                                 where func.Email.ToUpper().Trim().Equals(funcionario.Email.ToUpper().Trim()) &&
                                 func.Id != funcionario.Id
                                 select func).FirstOrDefault();

            if (funcionarioBD != null)
                throw new ExceptionBusiness("Usuário já existe.");
        }
    }
}