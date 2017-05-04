package br.com.fazai.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="TB_PAGAMENTO")
public class Pagamento implements Serializable{
	
		//Atributos da classe Pagamento
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_codigo_pk", nullable = false)
		private Integer id_pagamento;
		@Column(name = "int_numero_pagamento", nullable = false, unique=true)
		private int  numero_pagamento;
		@Column(name = "db_valor_total_pagamento", nullable = false)
		private double valor_total_pagamento;
		@Column(name = "dt_data_pagamento", nullable = false)
		private Date data_pagamento;
		@Column(name = "st_hora_pagamento", nullable = false)
		private String hora_pagamento;
		
		
		@OneToOne
		 private Pedido pedido;
		
	
		
		//Contrtutor da Classe Pagamento.
		public Pagamento() {}
		
		//Gets e Sets de Pagamento.
		public Integer getId_pagamento() {
			return id_pagamento;
		}

		public void setId_pagamento(Integer id_pagamento) {
			this.id_pagamento = id_pagamento;
		}

		public int getNumero_pagamento() {
			return numero_pagamento;
		}

		public void setNumero_pagamento(int numero_pagamento) {
			this.numero_pagamento = numero_pagamento;
		}

		public double getValor_total_pagamento() {
			return valor_total_pagamento;
		}

		public void setValor_total_pagamento(double valor_total_pagamento) {
			this.valor_total_pagamento = valor_total_pagamento;
		}

		public Date getData_pagamento() {
			return data_pagamento;
		}

		public void setData_pagamento(Date data_pagamento) {
			this.data_pagamento = data_pagamento;
		}

		public String getHora_pagamento() {
			return hora_pagamento;
		}

		public void setHora_pagamento(String hora_pagamento) {
			this.hora_pagamento = hora_pagamento;
		}		

}

