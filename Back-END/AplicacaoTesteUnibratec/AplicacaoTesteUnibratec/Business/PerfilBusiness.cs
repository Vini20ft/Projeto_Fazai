using System;

namespace AplicacaoTesteUnibratec.Business
{
    internal class PerfilBusiness
    {
        private TesteUnibratecDBEntities db;

        public PerfilBusiness(TesteUnibratecDBEntities db)
        {
            this.db = db;
        }

        internal Perfil GetForId(int id)
        {
            return db.Perfil.Find(id);
        }
    }
}