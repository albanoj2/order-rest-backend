package com.dzone.albanoj2.example.rest.domain;

public class Order implements Identifiable {

	private Long id;
	private String description;
	private long costInCents;
	private boolean isShipped;
	private boolean isDelivered;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCostInCents(long cost) {
		costInCents = cost;
	}
	
	public long getCostInCents() {
		return costInCents;
	}

	public boolean isShipped() {
		return isShipped;
	}

	public void setShipped(boolean desiredShipmentState) {
		validateDeliveryStatus(desiredShipmentState, isDelivered);
		isShipped = desiredShipmentState;
	}
	
	private void validateDeliveryStatus(boolean isShipped, boolean isDelivered) {
		
		if (!isShipped && isDelivered) {
			throw new IllegalOrderStateException("Cannot mark order " + id + " as unshipped but delivered");
		}
	}

	public void markAsShipped() {
		setShipped(true);
	}
	
	public void markAsUnshipped() {
		setShipped(false);
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean desiredDeliveryState) {
		validateDeliveryStatus(isShipped, desiredDeliveryState);
		isDelivered = desiredDeliveryState;
	}

	public void markAsDelivered() {
		setDelivered(true);
	}

	public void markAsNotDelivered() {
		setDelivered(false);
	}
	
	public boolean isCompleted() {
		return isShipped && isDelivered;
	}
}
