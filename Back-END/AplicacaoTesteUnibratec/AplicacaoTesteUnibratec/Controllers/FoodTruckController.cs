using AplicacaoTesteUnibratec.Business;
using System;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Transactions;
using System.Web;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    [RoutePrefix("FoodTruck")]
    public class FoodTruckController : BaseController
    {
        private readonly IFoodTruckBusiness _business;
        private IFuncionarioBusiness _businessFuncionario;

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
                using (TransactionScope scope = new TransactionScope())
                {
                    _business.Insert(foodTruck);
                    _businessFuncionario = new FuncionarioBusiness();
                    var entity = _businessFuncionario.GetFuncionarioByEmail(foodTruck.EmailFuncionario);
                    resultado = Request.CreateResponse(HttpStatusCode.OK, entity);
                    scope.Complete();
                }
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
        public HttpResponseMessage Delete(int id, string email)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.Delete(id, email);
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
                using (TransactionScope scope = new TransactionScope())
                {
                    _business.Update(foodTruck);
                    _businessFuncionario = new FuncionarioBusiness();
                    var entity = _businessFuncionario.GetFuncionarioByEmail(foodTruck.EmailFuncionario);
                    resultado = Request.CreateResponse(HttpStatusCode.OK, entity);
                    scope.Complete();
                }
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

        [Route("Upload")]
        [HttpPost]
        public HttpResponseMessage Upload(int id, string email)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var httpRequest = HttpContext.Current.Request;
                if (httpRequest.Files.Count < 1)
                    throw new ExceptionBusiness("Selecione uma imagem.");

                if (!Directory.Exists(HttpContext.Current.Server.MapPath("~/upload/")))
                    Directory.CreateDirectory(HttpContext.Current.Server.MapPath("~/upload/"));

                foreach (string file in httpRequest.Files)
                {
                    var postedFile = httpRequest.Files[file];
                    var filePath = HttpContext.Current.Server.MapPath("~/upload/" + postedFile.FileName);

                    var tipoArquivo = Path.GetExtension(filePath);

                    if (!tipoArquivo.ToUpper().Equals(".PNG") && !tipoArquivo.ToUpper().Equals(".JPG") && !tipoArquivo.ToUpper().Equals(".GIF"))
                        throw new ExceptionBusiness("Tipo da foto inválido.");

                    filePath = filePath.Replace(Path.GetFileName(filePath), id + "FoodTruck" + tipoArquivo);
                    postedFile.SaveAs(filePath);

                    var entity = _business.GetForId(id);
                    entity.Foto = Path.GetFileName(filePath);
                    entity.EmailFuncionario = email;
                    _business.Update(entity);
                }
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

        [Route("AvaliarFoodTruck")]
        [HttpGet]
        public HttpResponseMessage AvaliarFoodTruck(int id, int rating)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                _business.AvaliarFoodTruck(id, rating);
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
