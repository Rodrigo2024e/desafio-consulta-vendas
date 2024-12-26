package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.controllers.projections.SaleSellerProjections;
import com.devsuperior.dsmeta.controllers.projections.SelleSumProjections;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(nativeQuery = true, value = """
		    SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name As sellerName
		    FROM tb_sales 
		    JOIN tb_seller ON tb_seller.id = tb_sales.seller_id
		    WHERE (:minDate IS NULL OR tb_sales.date >= :minDate) 
		    AND (:maxDate IS NULL OR tb_sales.date <= :maxDate) 
		    AND (:name IS NULL OR LOWER(tb_seller.name) LIKE LOWER(CONCAT('%', :name, '%')))
		    ORDER BY tb_sales.id
		""",
		countQuery = """
		    SELECT COUNT(*) 
		    FROM tb_sales 
		    JOIN tb_seller ON tb_seller.id = tb_sales.seller_id
		    WHERE (:minDate IS NULL OR tb_sales.date >= :minDate) 
		    AND (:maxDate IS NULL OR tb_sales.date <= :maxDate) 
		    AND (:name IS NULL OR LOWER(tb_seller.name) LIKE LOWER(CONCAT('%', :name, '%')))
		""")
	Page<SaleSellerProjections> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

	
	@Query(nativeQuery = true, value = "SELECT tb_seller.name AS sellerName, SUM(tb_sales.amount) AS total " 
	           +"FROM tb_sales "
	           +"JOIN tb_seller ON tb_seller.id = tb_sales.seller_id "
	           +"WHERE (:minDate IS NULL OR tb_sales.date >= :minDate) " 
	           +"AND (:maxDate IS NULL OR tb_sales.date <= :maxDate) " 
	           +"GROUP BY tb_seller.name")
	List<SelleSumProjections> getSummary(LocalDate minDate, LocalDate maxDate);

}
