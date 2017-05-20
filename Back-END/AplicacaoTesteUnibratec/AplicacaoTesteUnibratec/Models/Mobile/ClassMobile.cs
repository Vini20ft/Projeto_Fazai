using System.Collections.Generic;

namespace AplicacaoTesteUnibratec.Models.Mobile
{
    public class ItensCardapio
    {
        public int codigo { get; set; }
        public string nome { get; set; }
        public string imagem { get; set; }
        public string descricao { get; set; }
        public double valor { get; set; }
        public int tempo_estimado { get; set; }
    }

    public class Cardapio
    {
        public int id { get; set; }
        public string foto { get; set; }
        public string descricao { get; set; }
        public List<ItensCardapio> itens_cardapio { get; set; }
    }

    public class Localizacao
    {
        public decimal latitude { get; set; }
        public decimal longitude { get; set; }
    }

    public class Endereco
    {
        public string estado { get; set; }
        public string cidade { get; set; }
        public string bairro { get; set; }
        public string rua { get; set; }
        public string numero { get; set; }
        public Localizacao localizacao { get; set; }
    }

    public class Estabelecimento
    {
        public int id { get; set; }
        public string nome { get; set; }
        public string cnpj { get; set; }
        public string especialidade { get; set; }
        public string razaoSocial { get; set; }
        public string telefone { get; set; }
        public double rating { get; set; }
        public string foto { get; set; }
        public Endereco endereco { get; set; }
    }
}