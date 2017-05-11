using AplicacaoTesteUnibratec.Business;
using System;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("FoodTruck")]
    public class FoodTruckController : BaseController
    {
        private readonly IFoodTruckBusiness _business;

        public FoodTruckController()
        {
            _business = new FoodTruckBusiness();
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

        [Route("GetAllDropDown")]
        [HttpGet]
        public HttpResponseMessage GetAllDropDown(string listaIdFoodTruckFuncionario)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetAll(listaIdFoodTruckFuncionario).Select(a => new { Id = a.Id, Nome = a.Nome });
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
        public HttpResponseMessage Insert(FoodTruck foodTruck)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Insert(foodTruck);
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
        [HttpPost]
        public HttpResponseMessage Update(FoodTruck foodTruck)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Update(foodTruck);
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

        [Route("GetAllMobile")]
        [HttpGet]
        public HttpResponseMessage GetAllMobile(string cidade)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetAllMobile(cidade);
                resultado = Request.CreateResponse(HttpStatusCode.OK, new { EstabelecimentoList = entity });
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

        [Route("GetForIdMobile")]
        [HttpGet]
        public HttpResponseMessage GetForIdMobile(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetForIdMobile(id);
                resultado = Request.CreateResponse(HttpStatusCode.OK, new { Estabelecimento = entity });
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
