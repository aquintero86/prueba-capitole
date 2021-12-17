package com.capitole.application.service;


import com.capitole.infraestructure.rest.dto.PriceRequest;
import com.capitole.domain.repository.
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql("classpath:db/migration/V20211103212209__V1_create_data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PriceServiceImplTest{

    @Autowired
    PriceServiceImpl subject;

    @Autowired
    PriceRepository priceRepository;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected= ResponseStatusException.class)
    public void shouldReturnGetPriceThrowException(){
      PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2021-04-05 00:00:01"));

    }



    @Test
    public void shouldReturnGetPriceTestOne(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-14 10:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(35.50));
    }


    @Test
    public void shouldReturnGetPriceTestTwo(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-14 16:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(25.45));
    }

    @Test
    public void shouldReturnGetPriceTestTree(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-14 21:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(35.5));
    }

    @Test
    public void shouldReturnGetPriceTestfour(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-15 10:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(30.5));
    }

    @Test
    public void shouldReturnGetPriceTestFive(){
        PriceResponse response =
                subject.getPriceByApplyDate(buildPriceRequest("2020-06-16 21:00:01"));
        Assertions.assertThat(response.getFinalPrice()).isEqualTo(BigDecimal.valueOf(38.95));
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
