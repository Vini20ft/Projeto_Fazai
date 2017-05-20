using AplicacaoTesteUnibratec.Business;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Web;
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

        [Route("Upload")]
        [HttpPost]
        public HttpResponseMessage Upload(int id)
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

                    filePath = filePath.Replace(Path.GetFileName(filePath), id + "ItemCardapio" + tipoArquivo);
                    postedFile.SaveAs(filePath);

                    var entity = _businessItem.GetForId(id);
                    entity.Foto = Path.GetFileName(filePath);
                    _businessItem.Update(entity);
                    resultado = Request.CreateResponse(HttpStatusCode.OK, entity.IdCardapio);
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

        [Route("GetForIdFoodTruckMobile")]
        [HttpGet]
        public HttpResponseMessage GetForIdFoodTruckMobile(int id)
        {
            var resultado = new HttpResponseMessage();
            try
            {
                var entity = _business.GetForIdFoodTruckMobile(id);
                var item = new List<Models.Mobile.Cardapio>();
                item.Add(entity);
                resultado = Request.CreateResponse(HttpStatusCode.OK, new { CardapioList = item });
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
