package com.capitole.infraestructure.repository;

import com.capitole.infraestructure.repository.model.PriceModel;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceModel, Integer> {
}
