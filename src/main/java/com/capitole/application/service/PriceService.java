package com.capitole.application.service;

import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.rest.dto.PriceResponse;

public interface PriceService {
    PriceResponse getPriceByApplyDate(PriceRequest priceRequest);
}
