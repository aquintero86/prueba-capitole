package com.capitole.application.service;

import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.repository.PriceRepository;
import com.capitole.infraestructure.repository.model.PriceModel;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PriceServiceImpl implements  PriceService{

    private final PriceRepository repository;

    public PriceServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceResponse getPriceByApplyDate(PriceRequest priceRequest) {

        Collection<PriceModel> priceModelIterable=
                StreamSupport.stream(repository.findPriceByApplyDate(priceRequest.getProductId()
                                , priceRequest.getBrandId(), priceRequest.getApplyDate()).spliterator(), false)
                        .collect(Collectors.toList());

        Comparator<PriceModel> startDateComparator = Comparator.comparing(PriceModel::getStartDate);
        PriceModel priceModel  =priceModelIterable.isEmpty()? null :
                StreamSupport
                .stream(priceModelIterable.spliterator(), false)
                .filter(p-> p.getStartDate() != null)
                .max(startDateComparator)
                .get();

        return Optional.ofNullable(priceModel)
                .map(
                        price -> {
                            return PriceResponse.builder()
                                    .startDate(price.getStartDate())
                                    .endDate(price.getEndDate())
                                    .rateToApply(price.getPriceList())
                                    .productuId(price.getProductId())
                                    .brandId(price.getBrandId())
                                    .finalPrice(price.getPrice())
                                    .build();
                        })
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Prices not found"));
    }


}
