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
    
    public partial class AvaliacaoFoodTruck
    {
        public int Id { get; set; }
        public Nullable<int> IdFoodTruck { get; set; }
        public Nullable<int> Rating { get; set; }
    
        public virtual FoodTruck FoodTruck { get; set; }
    }
}
