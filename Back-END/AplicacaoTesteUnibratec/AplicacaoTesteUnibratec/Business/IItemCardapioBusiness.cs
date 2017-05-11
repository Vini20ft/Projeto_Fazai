using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface IItemCardapioBusiness
    {
        List<ItemCardapio> GetAllForIdCardapio(int id);
        void InsertAll(Cardapio cardapio);
        void DeleteAll(int idCardapio);
        void Delete(int id);
    }
}