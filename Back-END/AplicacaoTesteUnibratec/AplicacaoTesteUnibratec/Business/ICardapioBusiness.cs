using AplicacaoTesteUnibratec.Models.Mobile;
using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Business
{
    internal interface ICardapioBusiness
    {
        List<Cardapio> GetAll(string listaIdFoodTruckFuncionario);
        void Insert(Cardapio cardapio);
        void Delete(int id);
        void Update(Cardapio cardapio);
        Cardapio GetForId(int id);

        #region Region - Mobile

        Models.Mobile.Cardapio GetForIdFoodTruckMobile(int idFoodTruck);
        Models.Mobile.ItensCardapio GetForIdItemCardapioMobile(int idItemCardapio);

        #endregion
    }
}