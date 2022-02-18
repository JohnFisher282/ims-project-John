package com.qa.ims.persistence.domain;

public class OrderItem {
	private Long fkOrderId;
	private Long fkItemId;

		public OrderItem(Long fkOrderId) {
			this.setFkOrderId(fkOrderId);
		}

		public OrderItem(Long fkItemId, Long fkOrderId) {
			this.setFkItemId(fkItemId);
			this.setFkOrderId(fkOrderId);
		}

		public Long getFkOrderId() {
			return fkOrderId;
		}

		public void setFkOrderId(Long fkOrderId) {
			this.fkOrderId = fkOrderId;
		}

		public Long getFkItemId() {
			return fkItemId;
		}

		public void setFkItemId(Long fkItemId) {
			this.fkItemId = fkItemId;
		}

		@Override
		public String toString() {
			return "FK_order_id: " + fkOrderId + " FK_item_id: " + fkItemId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fkOrderId == null) ? 0 : fkOrderId.hashCode());
			result = prime * result + ((fkItemId == null) ? 0 : fkItemId.hashCode());
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
			OrderItem other = (OrderItem) obj;
			if (getFkItemId() == null) {
				if (other.getFkItemId() != null)
					return false;
			} else if (!getFkItemId().equals(other.getFkItemId()))
				return false;
			if (fkOrderId == null) {
				if (other.fkOrderId != null)
					return false;
			} else if (!fkOrderId.equals(other.fkOrderId))
				return false;
			return true;
		}

}
