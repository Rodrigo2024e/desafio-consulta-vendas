package com.devsuperior.dsmeta.dto;

public class SellerSumDTO {

	private String name;
	private double amount;
	
	public SellerSumDTO () {
		
	}

	public SellerSumDTO(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}
	
	
}
