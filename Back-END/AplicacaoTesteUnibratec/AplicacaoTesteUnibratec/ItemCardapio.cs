//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace AplicacaoTesteUnibratec
{
    using System;
    using System.Collections.Generic;
    
    public partial class ItemCardapio
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public ItemCardapio()
        {
            this.ItemPedido = new HashSet<ItemPedido>();
        }
    
        public int Id { get; set; }
        public string Nome { get; set; }
        public Nullable<decimal> Preco { get; set; }
        public string Observacao { get; set; }
        public int IdCardapio { get; set; }
        public string Foto { get; set; }
        public string TempoEstimado { get; set; }
    
        public virtual Cardapio Cardapio { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<ItemPedido> ItemPedido { get; set; }
    }
}
