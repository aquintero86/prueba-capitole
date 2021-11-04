package com.capitole.infraestructure.repository.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "prices")
@Entity
@Getter
@Builder
public class PriceModel {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="brand_id")
    private int brandId;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="price_list")
    private int priceList;

    @Column(name="product_id")
    private int productId;

    @Column(name="priority")
    private int priority;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="curr")
    private String curr;
}
