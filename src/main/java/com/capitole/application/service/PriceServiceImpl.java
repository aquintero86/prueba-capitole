package com.capitole.application.service;

import com.capitole.infraestructure.rest.dto.PriceRequest;
import com.capitole.domain.repository.PriceRepository;
import com.capitole.domain.model.PriceModel;
import com.capitole.infraestructure.rest.dto.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Comparator;
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
        PriceResponse priceResponse = null;

        Iterable<PriceModel> prices = repository.findPriceByApplyDate(priceRequest.getProductId()
                , priceRequest.getBrandId(), priceRequest.getApplyDate());

        try{
            Collection<PriceModel> priceModelIterable= StreamSupport.stream(prices.spliterator(), false)
                    .collect(Collectors.toList());

            Comparator<PriceModel> startDateComparator = Comparator.comparing(PriceModel::getStartDate);
            Comparator<PriceModel> priorityComparator = Comparator.comparing(PriceModel::getPriority);
            PriceModel priceModel  =priceModelIterable.isEmpty()? null :
                    StreamSupport
                            .stream(priceModelIterable.spliterator(), false)
                            .filter(p-> p.getStartDate() != null ).sorted(priorityComparator)
                            .max(startDateComparator)
                            .get();
            priceResponse = PriceResponse.builder()
                    .startDate(priceModel.getStartDate())
                    .endDate(priceModel.getEndDate())
                    .rateToApply(priceModel.getPriceList())
                    .productuId(priceModel.getProductId())
                    .brandId(priceModel.getBrandId())
                    .finalPrice(priceModel.getPrice())
                    .build();

        }catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Prices not found");
        }

        return priceResponse;

    }


}
