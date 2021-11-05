package com.capitole.application.service;

import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.repository.PriceRepository;
import com.capitole.infraestructure.repository.model.PriceModel;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PriceServiceImplTest{


    PriceServiceImpl subject;

    @Mock
    PriceRepository priceRepository;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subject = new PriceServiceImpl(priceRepository);
    }

    @Test
    public void shouldReturnGetPrice(){
      PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest());
        Assertions.assertThat(response).isNotNull();
    }


    public PriceRequest buildPriceRequest(){
        String str = "2021-04-05 00:00:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return PriceRequest.builder()
                .applyDate(dateTime)
                .brandId(1)
                .ProductId(35455)
                .build();
    }



}
