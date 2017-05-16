using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AplicacaoTesteUnibratec.Models;

namespace AplicacaoTesteUnibratec.Business
{
    interface ITesteBusiness
    {
        List<TBTeste> GetAll();
        void Insert(string descricao);
        void Delete(int id);
        void Update(string descricao, int id);
        TesteView GetForId(int id);
    }
}
