package com.exchangeinformant.repository;

import com.exchangeinformant.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
    Stock findBySecureCode(String secureCode);
    List<Stock> findAllBySource(String source);
}