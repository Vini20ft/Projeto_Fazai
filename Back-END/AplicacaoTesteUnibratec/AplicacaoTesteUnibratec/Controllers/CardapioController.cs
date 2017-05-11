using AplicacaoTesteUnibratec.Business;
using System;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("Cardapio")]
    public class CardapioController : BaseController
    {
        private readonly ICardapioBusiness _business;
        private readonly IItemCardapioBusiness _businessItem;

        public CardapioController()
        {
            _business = new CardapioBusiness();
            _businessItem = new ItemCardapioBusiness();
        }

        [Route("GetAll")]
        [HttpGet]
        public HttpResponseMessage GetAll(string listaIdFoodTruckFuncionario)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetAll(listaIdFoodTruckFuncionario);
                resultado = Request.CreateResponse(HttpStatusCode.OK, entity);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }

        [Route("Insert")]
        [HttpPost]
        public HttpResponseMessage Insert(Cardapio cardapio)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Insert(cardapio);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }

        [Route("Delete")]
        [HttpGet]
        public HttpResponseMessage Delete(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Delete(id);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }

        [Route("DeleteItemCardapio")]
        [HttpGet]
        public HttpResponseMessage DeleteItemCardapio(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _businessItem.Delete(id);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }

        [Route("Update")]
        [HttpPost]
        public HttpResponseMessage Update(Cardapio cardapio)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Update(cardapio);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }

        [Route("GetForId")]
        [HttpGet]
        public HttpResponseMessage GetForId(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetForId(id);
                resultado = Request.CreateResponse(HttpStatusCode.OK, entity);
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }
            return resultado;
        }

        #region Region - Mobile
        
        [Route("GetForIdFoodTruckMobile")]
        [HttpGet]
        public HttpResponseMessage GetForIdFoodTruckMobile(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetForIdFoodTruckMobile(id);
                resultado = Request.CreateResponse(HttpStatusCode.OK, new { CardapioList = entity });
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }


        [Route("GetForIdItemCardapioMobile")]
        [HttpGet]
        public HttpResponseMessage GetForIdItemCardapioMobile(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetForIdItemCardapioMobile(id);
                resultado = Request.CreateResponse(HttpStatusCode.OK, new { ItemCardapio = entity });
            }
            catch (ExceptionBusiness ex)
            {
                resultado = ProcessarExcecaoWeb(ex, true);
            }
            catch (Exception ex)
            {
                resultado = ProcessarExcecaoWeb(ex);
            }

            return resultado;
        }
        #endregion
    }
}
