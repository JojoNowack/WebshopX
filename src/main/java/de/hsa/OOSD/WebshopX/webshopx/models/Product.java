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
    @NotNull
    private String artist;


    /**
     * The price of a specific product.
     */
    @NotNull
    private float price;
    @NotNull
    private String date;



    /**
     * The description of a specific product.
     */
    @NotNull
    private String description;


    @ManyToOne
    private Category category;
    @NotNull
    private String imageUrl;

    public Product(String name, String artist, float price, String date, Category category, String description, String imageUrl) {

        this.name = name;
        this.price = price;
        this.description = description;
        this.artist = artist;
        this.date = date;


        this.category = category;

        this.imageUrl = imageUrl;

    }
}


