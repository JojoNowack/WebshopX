package de.hsa.OOSD.WebshopX.webshopx.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String artist;

    @NotNull
    private Float price;

    @NotNull
    String date;

    @ManyToOne
    private Category category;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;


    public Product(String name, String artist, Float price, String date, Category category, String description, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.date = date;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}


