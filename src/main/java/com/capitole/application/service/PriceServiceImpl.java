package com.capitole.application.service;

import com.capitole.domain.PriceRequest;
import com.capitole.infraestructure.repository.PriceRepository;
import com.capitole.infraestructure.repository.model.PriceModel;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PriceServiceImpl implements  PriceService{

    private final PriceRepository repository;

    public PriceServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public PriceResponse getPriceByApplyDate(PriceRequest priceRequest) {
        Stream<PriceModel> priceModelsStream = StreamSupport
                .stream(repository.findPriceByApplyDate(priceRequest.getProductId()
                        , priceRequest.getBrandId(), priceRequest.getApplyDate()).spliterator(), false);
        PriceModel priceModel =validateResultsQuery(priceModelsStream);

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

    private PriceModel validateResultsQuery(Stream<PriceModel> stream) {
        Comparator<PriceModel> startDateComparator = Comparator.comparing(PriceModel::getStartDate);
        if(stream.count() > 0)
            return  stream
                    .filter(p-> p.getStartDate() != null)
                    .max(startDateComparator)
                    .get();
        else
            return null;
    }
}
