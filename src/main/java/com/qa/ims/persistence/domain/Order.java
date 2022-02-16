package com.qa.ims.persistence.domain;

public class Order {
private Long orderId;
private Long fkId;

	public Order(Long fkId) {
		this.setFkId(fkId);
	}

	public Order(Long orderId, Long fkId) {
		this.setOrderId(orderId);
		this.setFkId(fkId);
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getFkId() {
		return fkId;
	}

	public void setFkId(Long fkId) {
		this.fkId = fkId;
	}

	@Override
	public String toString() {
		return "Order id:" + orderId + " FK_id" + fkId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((fkId == null) ? 0 : fkId.hashCode());
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
		Order other = (Order) obj;
		if (getFkId() == null) {
			if (other.getFkId() != null)
				return false;
		} else if (!getFkId().equals(other.getFkId()))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
