package com.capitole.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@Validated
public class PriceRequest {

    @JsonProperty("ProductId")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @NotNull(message = "Product can't be null")
    private int ProductId;


    @JsonProperty("BrandId")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @NotNull(message = "Brand can't be null")
    private int brandId;

    @JsonProperty("ApplyDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date can't be null")
    private LocalDateTime applyDate;



}