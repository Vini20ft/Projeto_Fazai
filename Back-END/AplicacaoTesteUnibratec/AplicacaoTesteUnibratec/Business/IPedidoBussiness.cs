using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface IPedidoBussiness

    {
        List<Pedido> GetAll(string listadepedidos);
        void Insert(Pedido pedido);
        void Delete(int id);
        void Update(int id, int status);
        Pedido GetForId(int id);

    }
}