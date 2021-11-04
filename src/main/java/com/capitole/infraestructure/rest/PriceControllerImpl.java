package com.capitole.infraestructure.rest;

import com.capitole.application.service.PriceService;
import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api
public class PriceControllerImpl implements PriceController{

    private final PriceService priceService;

    public PriceControllerImpl(PriceService priceService) {
        this.priceService = priceService;
    }



    @Override
    public ResponseEntity<PriceResponse> getPrice(PriceRequest priceRequest) {
        PriceResponse response = priceService.getPriceByApplyDate(priceRequest);
        return ResponseEntity.ok(response);
    }


}
