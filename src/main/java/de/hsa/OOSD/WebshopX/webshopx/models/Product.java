package de.hsa.OOSD.WebshopX.webshopx.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @NotNull
    private String name;

    /**
     * The price of a specific product.
     */
    @NotNull
    private Float price;

    /**
     * The description of a specific product.
     */
    private String description;

    @ManyToOne
    private Category category;

    public Product (String name, float price, String description, Category category){
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}


