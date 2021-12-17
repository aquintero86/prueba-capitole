package com.capitole.infraestructure.rest;

import com.capitole.infraestructure.rest.dto.PriceRequest;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/")
public interface IPrice {

    @GetMapping(
            value = "/price",
            produces = {"application/json"},
            consumes = {"application/json"})
    ResponseEntity<PriceResponse> getPrice(@Valid @RequestBody PriceRequest priceRequest);

}