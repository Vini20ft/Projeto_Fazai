package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import sun.util.calendar.LocalGregorianCalendar.Date;

@Entity
public class Pagamento {

	@Id
	@Column(name = "codigo", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "numero_Pgto", nullable = false)
	private int  numero_Pgto;
	@Column(name = "valor_Total", nullable = false)
	private double valor_Total;
	@Column(name = "Data_Pgto", nullable = false)
	private Date data_Pgto;
	@Column(name = "Hora_Pgto", nullable = false)
	private Date hora_Pgto;
	
	@OneToMany(mappedBy = "pagamento",
			fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Pedido> pedido;
}
