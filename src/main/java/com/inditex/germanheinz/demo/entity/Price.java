package com.inditex.germanheinz.demo.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    @Column(name = "start_date")
    private String startDate;
    
    @Column(name = "end_date")
    private String endDate;
    
    @Column(name = "price_list")
    private Integer priceList;
    
    @Column(name = "product_id")
    private Integer productId;
    
    private Integer priority;
    
    private Double price;
    
    private String curr;
}