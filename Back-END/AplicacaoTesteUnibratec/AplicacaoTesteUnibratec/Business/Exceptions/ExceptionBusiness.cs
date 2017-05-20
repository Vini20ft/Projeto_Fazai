using System;
using System.Runtime.Serialization;

namespace AplicacaoTesteUnibratec.Business
{
    [Serializable]
    internal class ExceptionBusiness : Exception
    {
        public ExceptionBusiness()
        {
        }

        public ExceptionBusiness(string message) : base(message)
        {
        }

        public ExceptionBusiness(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected ExceptionBusiness(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}