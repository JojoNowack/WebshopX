package de.hsa.OOSD.WebshopX.webshopx.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The class Product representing a specific product.
 */
@Entity
@NoArgsConstructor
@Data
public class Product {

    /**
     * The id of a specific product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of a specific product.
     */
    private String name;

    /**
     * The price of a specific product.
     */
    private float price;

    /**
     * The description of a specific product.
     */
    private String description;
}


