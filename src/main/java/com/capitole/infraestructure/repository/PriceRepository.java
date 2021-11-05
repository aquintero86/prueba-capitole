package com.capitole.infraestructure.repository;

import com.capitole.infraestructure.repository.model.PriceModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends CrudRepository<PriceModel, Integer> {

    @Query(value = "select p.* FROM prices p where p.product_id =:product_id and p.brand_id =:brand_id " +
            "and p.start_date < parsedatetime(:dateToApply, 'yyyy-MM-dd hh:mm:ss') and p.end_date > parsedatetime(:dateToApply, 'yyyy-MM-dd hh:mm:ss')", nativeQuery = true)
    public Iterable<PriceModel> findPriceByApplyDate(@Param("product_id") int productId, @Param("brand_id") int brandId, @Param("dateToApply") LocalDateTime dateToApply);

}
