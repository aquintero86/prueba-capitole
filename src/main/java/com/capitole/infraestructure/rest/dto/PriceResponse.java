package com.capitole.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PriceResponse {

    @JsonProperty("ProductId")
    private int productuId;

    @JsonProperty("BrandiId")
    private int brandId;

    @JsonProperty("RateToApply")
    private int rateToApply;

    @JsonProperty("StartDate")
    private LocalDateTime startDate;

    @JsonProperty("EndDate")
    private LocalDateTime endDate;

    @JsonProperty("FinalPrice")
    private BigDecimal finalPrice;


}