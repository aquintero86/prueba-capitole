package com.capitole.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequest {

    @JsonProperty("ProductId")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Min(value = 1, message = "must have valid value")
    private int productId;


    @JsonProperty("BrandId")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    @Min(value = 1, message = "must have valid value")
    private int brandId;

    @JsonProperty("ApplyDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date can't be null")
    private LocalDateTime applyDate;



}