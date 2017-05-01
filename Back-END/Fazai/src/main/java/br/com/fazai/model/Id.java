package br.com.fazai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public abstract class Id implements Serializable {

	@Column(name = "id_item", nullable = false)
	private Integer id_item;

	@Column(name = "id_pedido_pk", nullable = false)
	private Integer id_pedido_pk;

	public Integer getId_item() {
		return id_item;
	}

	public void setId_item(Integer id_item) {
		this.id_item = id_item;
	}

	public Integer getId_pedido_pk() {
		return id_pedido_pk;
	}

	public void setId_pedido_pk(Integer id_pedido_pk) {
		this.id_pedido_pk = id_pedido_pk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_item;
		result = prime * result + id_pedido_pk;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Id other = (Id) obj;
		if (id_item != other.id_item)
			return false;
		if (id_pedido_pk != other.id_pedido_pk)
			return false;
		return true;
	}
}