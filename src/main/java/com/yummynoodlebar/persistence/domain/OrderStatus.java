package com.yummynoodlebar.persistence.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

import com.yummynoodlebar.events.orders.OrderStatusDetails;

@Region("YummyNoodleOrder")
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID orderId;
	@Id
	private UUID id;
	private String name;
	private Date statusDate;
	private String status;

	public OrderStatus(UUID orderId, UUID id, String name, final Date date, final String status) {
		this.orderId = orderId;
		this.id = id;
		this.name = name;
		this.status = status;
		this.statusDate = date;
	}

	public OrderStatus() {

	}

	public Date getStatusDate() {
		return statusDate;
	}

	public String getStatus() {
		return status;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public UUID getId() {
		return id;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrderStatusDetails toStatusDetails() {
		return new OrderStatusDetails(orderId, id, name, statusDate, status);
	}

	public static OrderStatus fromStatusDetails(OrderStatusDetails orderStatusDetails) {
		return new OrderStatus(orderStatusDetails.getOrderId(), orderStatusDetails.getId(), orderStatusDetails.getName(), orderStatusDetails.getStatusDate(),
				orderStatusDetails.getStatus());
	}
}
