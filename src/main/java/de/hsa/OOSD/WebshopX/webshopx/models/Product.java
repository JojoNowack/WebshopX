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
    private Status status;
    @NotNull
    private String artist;

    @NotNull
    private Double price;

    @NotNull
    String publicationYear;

    @ManyToOne
    private Category category;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;


    public Product(String name, String artist, Double price, String publicationYear, Category category, String description, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.publicationYear = publicationYear;
        this.category = category;
        this.description = description;
        this.imageUrl = imageUrl;

        this.status = Status.AVAILABLE;
    }

    public boolean isAvailable(){
        return status == Status.AVAILABLE;
    }

    public void toggleStatus(){
        if(status == Status.AVAILABLE){
            status = Status.SOLD;
        } else{
            status = Status.AVAILABLE;
        }
    }

    public enum Status{
        AVAILABLE, SOLD
    }

}


