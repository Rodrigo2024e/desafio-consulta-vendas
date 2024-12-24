package com.devsuperior.dsmeta.controllers.projections;

import java.time.LocalDate;

public interface SaleSellerProjections {

	Long getId();
	LocalDate getDate();
	double getAmount();
	String getSellerName();
}
