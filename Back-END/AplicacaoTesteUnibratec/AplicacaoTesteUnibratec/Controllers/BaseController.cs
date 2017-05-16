using System;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AplicacaoTesteUnibratec.Controllers
{
    public class BaseController : ApiController
    {
        public HttpResponseMessage ProcessarExcecaoWeb(Exception ex, bool tratada = false)
        {
            var response = Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
            if (tratada)
                response = Request.CreateErrorResponse(HttpStatusCode.NotAcceptable, ex);

            if (ex.InnerException != null)
            {
                if (ex.InnerException.InnerException != null)
                {
                    response.Content = new StringContent(ex.InnerException.InnerException.Message);
                    response.ReasonPhrase = ex.InnerException.InnerException.Message;
                }
                else
                {
                    response.Content = new StringContent(ex.InnerException.Message);
                    response.ReasonPhrase = ex.InnerException.Message;
                }
            }
            else
            {
                response.Content = new StringContent(ex.Message);
                response.ReasonPhrase = ex.Message;
            }

            return response;
        }
    }
}
