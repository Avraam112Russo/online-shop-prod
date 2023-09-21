package com.russozaripov.basket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "product_in_basket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sku_code")
    private String skuCode;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;


}
