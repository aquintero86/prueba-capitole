package com.capitole.application.service;

import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PriceServiceImpl implements  PriceService{
    public PriceResponse getPriceByApplyDate(PriceRequest priceRequest) {
        String str = "2021-04-05 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return PriceResponse.builder()
                .brandId(1)
                .finalPrice(BigDecimal.valueOf(10.0))
                .endDate(dateTime)
                .startDate(dateTime)
                .build();
    }
}
