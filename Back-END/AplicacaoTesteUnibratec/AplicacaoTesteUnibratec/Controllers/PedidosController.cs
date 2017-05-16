using AplicacaoTesteUnibratec.Business;
using System;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("Pedido")]
    public class PedidosController : BaseController
    {
        private readonly IPedidoBussiness _business;
        private readonly IItemPedidoBusiness _businessItem;

        public PedidosController()
        {
            _business = new PedidoBusiness();
            _businessItem = new ItemPedidoBusiness();
        }

        [Route("GetAll")]
        [HttpGet]
        public HttpResponseMessage GetAll(string lista)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetAll(lista);
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
        public HttpResponseMessage Insert()
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Insert( new Pedido());

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

        [Route("Update")]
        [HttpGet]
        public HttpResponseMessage Update(int id,int status)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Update(id,status);
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

    }
}
