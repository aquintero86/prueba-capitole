package com.capitole.infraestructure;


import com.capitole.application.service.PriceService;
import com.capitole.infraestructure.rest.dto.PriceRequest;
import com.capitole.infraestructure.rest.PriceControllerImpl;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import com.capitole.infraestructure.rest.exceptions.RestResponseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PriceControllerImplTest {
    @InjectMocks
    private PriceControllerImpl subject;

    private MockMvc mockMvc;

    @Mock
    PriceService priceService;


    @Rule
   public ExpectedException thrown = ExpectedException.none();


    @Before
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subject)
                .setControllerAdvice(new RestResponseException())
                .build();

    }


    @Test
    public void shouldResponseOkGetPrice() throws Exception {
        when(priceService.getPriceByApplyDate(buildPriceRequest())).thenReturn(buildPriceResponse());

        ResponseEntity<PriceResponse> response =
                subject.getPrice( buildPriceRequest());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }




    @Test
    public void shouldResponseBadRequestForNullDate() throws Exception {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(buildPriceRequestDateNull());
        mockMvc.perform(get("/price")
                        .contentType("application/json")
                        .content(jsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("applyDate : Date can't be null");

    }

    @Test
    public void shouldResponseBadRequestForNullBrand() throws Exception {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(buildPriceRequestBrandNull());
        mockMvc.perform(get("/price")
                        .contentType("application/json")
                        .content(jsonStr))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("brandId : must have valid value");;

    }

    @Test
    public void shouldResponseBadRequestForNullProduct() throws Exception {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(buildPriceRequestProductNull());
        mockMvc.perform(get("/price")
                        .contentType("application/json")
                                .content(jsonStr)
                        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .contains("productId : must have valid value");

    }




    public PriceResponse buildPriceResponse(){
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

    public PriceRequest buildPriceRequest(){
        String str = "2021-04-05 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  PriceRequest.builder()
                .brandId(1)
                .productId(1)
                .applyDate(dateTime)
                .build();
    }

    public PriceRequest buildPriceRequestBrandNull(){
        String str = "2021-04-05 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  PriceRequest.builder()
                .productId(1)
                .build();
    }


    public PriceRequest buildPriceRequestProductNull(){
        String str = "2021-04-05 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return  PriceRequest.builder()
                .brandId(1)
                .build();
    }
    public PriceRequest buildPriceRequestDateNull(){
        return  PriceRequest.builder()
                .productId(1)
                .brandId(1)
                .build();


    }


}
