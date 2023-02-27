package com.exchangeinformant.services;

import com.exchangeinformant.model.Stock;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 21.12.2022
 * Time: 13:50
 */
public interface StockService {

    void updateAllStocks();

    void getAllStocks();

    Stock getStockDirectly(String secureCode);

    List<Stock> getStocksDirectly(List<String> secureCodes);
}
