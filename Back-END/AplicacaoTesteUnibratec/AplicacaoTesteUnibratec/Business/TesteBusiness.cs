using System.Collections.Generic;
using System.Linq;
using AplicacaoTesteUnibratec.Models;

namespace AplicacaoTesteUnibratec.Business
{
    public class TesteBusiness : ITesteBusiness
    {
        private TesteUnibratecDBEntities db;

        public TesteBusiness()
        {
            db = new TesteUnibratecDBEntities();
        }

        public TesteBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        public void Delete(int id)
        {
            TBTeste item = db.TBTeste.Find(id);
            db.TBTeste.Remove(item);
            db.SaveChanges();
        }

        public List<TBTeste> GetAll()
        {
            List<TBTeste> lista = (from tbTeste in db.TBTeste select tbTeste).ToList();
            return lista;
        }

        public TesteView GetForId(int id)
        {
            TBTeste tbTeste = db.TBTeste.Find(id);
            return ParseModelParaViewModel(tbTeste);
        }

        public void Insert(string descricao)
        {
            TBTeste tbTeste = new TBTeste() { DescricaoTeste = descricao };
            db.TBTeste.Add(tbTeste);
            db.SaveChanges();
        }

        public void Update(string descricao, int id)
        {
            TBTeste tbTesteOriginal = db.TBTeste.Find(id);
            TBTeste tbTesteAlterado = new TBTeste() { DescricaoTeste = descricao, CodigoTeste = id };
            db.Entry(tbTesteOriginal).CurrentValues.SetValues(tbTesteAlterado);
            db.SaveChanges();
        }

        private TesteView ParseModelParaViewModel(TBTeste tbTeste)
        {
            TesteView testeView = new TesteView()
            {
                DescricaoTeste = tbTeste.DescricaoTeste,
                CodigoTeste = tbTeste.CodigoTeste
            };
            return testeView;
        }
    }
}