using AplicacaoTesteUnibratec.Business;
using System;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("Teste")]
    public class TesteController : BaseController
    {
        private readonly ITesteBusiness _business;

        public TesteController()
        {
            _business = new TesteBusiness();
        }

        [Route("GetAll")]
        [HttpGet]
        public HttpResponseMessage GetAll()
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetAll();
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
        [HttpGet]
        public HttpResponseMessage Insert(string descricao)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Insert(descricao);
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
        public HttpResponseMessage Update(string descricao, int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Update(descricao, id);
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
