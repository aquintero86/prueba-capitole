package com.capitole.infraestructure;

import com.capitole.application.service.PriceService;
import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.rest.PriceControllerImpl;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;

public class PriceControllerImplTest {
    @Autowired
    PriceControllerImpl subject;

    @Mock
    PriceService priceService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subject = new PriceControllerImpl(priceService);
    }

    @Test
    public void shouldResponseOkGetPrice(){
        PriceResponse response = buidPriceResponse();
        when(priceService.getPriceByApplyDate(PriceRequest.builder().build())).thenReturn(response);
        ResponseEntity<PriceResponse> entity =
                subject.getPrice(PriceRequest.builder().build());
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    public PriceResponse buidPriceResponse(){
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
