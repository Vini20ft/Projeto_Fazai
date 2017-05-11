using AplicacaoTesteUnibratec.Business;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("Funcionario")]
    public class FuncionarioController : BaseController
    {
        private readonly IFuncionarioBusiness _business;
        private IFoodTruckBusiness _businessFoodTruck;

        public FuncionarioController()
        {
            _business = new FuncionarioBusiness();
        }

        [Route("Entrar")]
        [HttpPost]
        public HttpResponseMessage Entrar(Funcionario funcionario)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.Entrar(funcionario);
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
        public HttpResponseMessage Insert(Funcionario funcionario)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                if (!string.IsNullOrEmpty(funcionario.NomeFoodTruck))
                {
                    _businessFoodTruck = new FoodTruckBusiness();
                    var foodTruck = new FoodTruck() { Nome = funcionario.NomeFoodTruck };
                    _businessFoodTruck.Insert(foodTruck);
                    funcionario.FoodTruckFuncionario = new List<FoodTruckFuncionario>();
                    funcionario.FoodTruckFuncionario.Add(new FoodTruckFuncionario() { IdFoodTruck = foodTruck.Id }); 
                }

                funcionario.Perfil = null;
                _business.Insert(funcionario);
                resultado = Request.CreateResponse(HttpStatusCode.OK, funcionario);
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
        public HttpResponseMessage Update(Funcionario funcionario)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                //if (string.IsNullOrEmpty(funcionario.FoodTruck.Nome))
                //    funcionario.FoodTruck = null;
                funcionario.Perfil = null;
                _business.Update(funcionario);
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
