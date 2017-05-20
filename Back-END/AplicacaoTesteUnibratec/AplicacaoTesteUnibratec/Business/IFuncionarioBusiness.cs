using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface IFuncionarioBusiness
    {
        List<Funcionario> GetAll(string listaIdFoodTruckFuncionario);
        void Insert(Funcionario funcionario);
        void Delete(int id);
        void Update(Funcionario funcionario);
        Funcionario GetForId(int id);
        Funcionario Entrar(Funcionario funcionario);
        Funcionario GetFuncionarioByEmail(string email);
    }
}