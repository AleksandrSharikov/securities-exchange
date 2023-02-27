package com.exchangeinformant.services;

import com.exchangeinformant.dto.InfoDTO;
import com.exchangeinformant.repository.NameRepositoryRedis;
import com.exchangeinformant.util.Name;
import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.util.StockClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 05.01.2023
 * Time: 17:27
 */
@Service("bcs")
@Slf4j
@RefreshScope
public class BcsStockService implements StockService {

    private final InfoRepository infoRepository;
    private final StockRepository stockRepository;
    private final NameRepositoryRedis nameRepository;
    private final StockClient stockClient;
    @Value("${quotes.supplier}")
    private String serviceName;

    public BcsStockService(InfoRepository infoRepository, StockRepository stockRepository, NameRepositoryRedis nameRepository, StockClient stockClient) {
        this.infoRepository = infoRepository;
        this.stockRepository = stockRepository;
        this.nameRepository = nameRepository;
        this.stockClient = stockClient;
    }

    @Override
    @Transactional
    public void updateAllStocks() {
        if(nameRepository.findAll(serviceName).isEmpty()){
            getAllStocks();
        }

        if (stockRepository.findAllBySource(serviceName).isEmpty() || stockRepository.findAllBySource(serviceName).size() <1000) {
            saveAllStocks();
        }

        List<Stock> allStocks = stockRepository.findAllBySource(serviceName)
                .stream()
                .limit(500)
                .collect(Collectors.toList());
        for (Stock stock : allStocks) {
            try {
                List<StockDTO> foundStock = stockClient.findOneStock(stock.getSecureCode());
                StockDTO stockDTO = Objects.requireNonNull(foundStock).get(0);
                InfoDTO infoDTO = stockDTO.getInfoList();
                Info info = convertInfoDTOToInfo(infoDTO);
                info.setSecureCode(stock.getSecureCode());
                infoRepository.save(info);
            } catch (WebClientRequestException e) {
                e.printStackTrace();
                throw new QuotesException(ErrorCodes.UPDATE_PROBLEM.name());
            }
        }
        log.info("Updated Successfully");
    }

    @Override
    public void getAllStocks() {
        try {
            List<Name> names = stockClient.findAllStocks().getName();
            for (Name name : names) {
                nameRepository.save(serviceName,name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stock getStockDirectly(String secureCode) {
        List<StockDTO> foundStock = stockClient.findOneStock(secureCode);
        StockDTO stockDTO = Objects.requireNonNull(foundStock).get(0);
        return new Stock(stockDTO.getSecureCode(), stockDTO.getIssuer(),stockDTO.getCurrency(),
                new ArrayList<>(List.of(
                        new Info(stockDTO.getInfoList().getLastPrice(), LocalDateTime.now(), stockDTO.getSecureCode()))),
                serviceName);
    }

    @Override
    public List<Stock> getStocksDirectly(List<String> secureCodes) {
        List<Stock> foundStocks = new ArrayList<>();
        for (String code : secureCodes) {
            foundStocks.add(getStockDirectly(code));
        }
        return foundStocks;
    }

    private void saveAllStocks() {
        nameRepository.findAll(serviceName)
                .stream().map(Name.class::cast)
                .forEach(n -> stockRepository.save(new Stock(n.getSecureCode(),n.getIssuer(),n.getCurrency(),serviceName)));
    }

    private Info convertInfoDTOToInfo(InfoDTO infoDTO) {
        Info info = new Info();
        info.setUpdatedAt(LocalDateTime.now());
        info.setLastPrice(infoDTO.getLastPrice());
        return info;
    }
}
