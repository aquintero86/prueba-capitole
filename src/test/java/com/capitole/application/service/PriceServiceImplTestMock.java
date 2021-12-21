package com.capitole.application.service;

import com.capitole.domain.repository.PriceRepository;
import com.capitole.infraestructure.rest.dto.PriceRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTestMock {

    @InjectMocks
    PriceServiceImpl subject;

    @Mock
    PriceRepository priceRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);

    }

    @Test(expected= ResponseStatusException.class)
    public void shouldReturnGetPriceThrowException(){
        PriceRequest request = buildPriceRequest("2020-06-16 21:00:01");
        when(priceRepository.findPriceByApplyDate(request.getProductId(), request.getBrandId(), request.getApplyDate()))
                .thenReturn(Collections.emptyList());
        subject.getPriceByApplyDate(request);
    }

    public PriceRequest buildPriceRequest(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return PriceRequest.builder()
                .applyDate(dateTime)
                .brandId(1)
                .productId(23)
                .build();

    }



}
