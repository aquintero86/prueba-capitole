package com.capitole.application.service;


import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.repository.PriceRepository;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql("classpath:db/migration/V20211103212209__V1_create_data.sql")
public class PriceServiceImplTest{


    PriceServiceImpl subject;

    @Autowired
    PriceRepository priceRepository;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        subject = new PriceServiceImpl(priceRepository);
    }

    @Test
    public void shouldReturnGetPrice(){
      PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2021-04-05 00:00:01"));
        Assertions.assertThat(response).isNotNull();
    }



    @Test
    public void shouldReturnGetPriceWithMaxPriority(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-14 00:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(25.45));
    }

    public PriceRequest buildPriceRequest(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return PriceRequest.builder()
                .applyDate(dateTime)
                .brandId(1)
                .ProductId(35455)
                .build();
    }

}
