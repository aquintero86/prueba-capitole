package com.capitole.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonProperty("ApplyDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date can't be null")
    private LocalDateTime applyDate;

    @NotNull(message = "Product can't be null")
    @JsonProperty("ProductId")
    private int ProductId;

    @NotNull(message = "Brand can't be null")
    @JsonProperty("BrandId")
    private int brandId;
}