package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleSellerMinDTO {

	private Long id;
	private LocalDate data;
	private double amount;
	
	private SellerMinDTO sellerName;
	
	public SaleSellerMinDTO() {
	}

	public SaleSellerMinDTO(Long id, LocalDate data, double amount, SellerMinDTO sellerName) {
		this.id = id;
		this.data = data;
		this.amount = amount;
		this.sellerName = sellerName;
	}


	public SaleSellerMinDTO(Sale entity) {
		id = entity.getId();
		data = entity.getDate();
		amount = entity.getAmount();
		sellerName = new SellerMinDTO (entity.getSeller());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public SellerMinDTO getSeller() {
		return sellerName;
	}

	public void setSeller(SellerMinDTO seller) {
		this.sellerName = seller;
	}
	
	
}
