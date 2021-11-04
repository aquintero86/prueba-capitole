package com.capitole.infraestructure;

import com.capitole.application.service.PriceService;
import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.rest.PriceControllerImpl;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PriceControllerImplTest {
    @InjectMocks
    PriceControllerImpl subject;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    PriceService priceService;


    private LinkedMultiValueMap<String, String> params;

    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subject).build();

    }

    @Test
    public void shouldResponseOkGetPrice() throws Exception {
        when(priceService.getPriceByApplyDate(buidPriceRequest())).thenReturn(buidPriceResponse());

        ResponseEntity<PriceResponse> response =
                subject.getPrice( buidPriceRequest());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldResponseBadRequestForNullDate() throws Exception {
        params = new LinkedMultiValueMap<>();
        params.add("ProductId", "35455");
        params.add("BrandId", "1");
        String responseValidate = "{\"applyDate\": \"Date cannot be null\" }";
        ResultActions resultActions = this.mockMvc.perform(get("/price")
                        .params(params)
                        .contentType("application/json")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldResponseBadRequestForNullBrand() throws Exception {
        params = new LinkedMultiValueMap<>();
        params.add("ProductId", "35455");
        params.add("ApplyDate","2020-06-14 00:00:02");
        this.mockMvc.perform(get("/price")
                        .params(params)
                        .contentType("application/json")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
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

    public PriceRequest buidPriceRequest(){
        String str = "2021-04-05 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  PriceRequest.builder()
                .brandId(1)
                .ProductId(1)
                .applyDate(dateTime)
                .build();
    }



}
