package de.hsa.OOSD.WebshopX.webshopx.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Category {

    /**
     * The id of a specific category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private CategoryType categoryType;

    @NotNull
    private String name;


    public Category(CategoryType categoryType, String name){
        this.categoryType = categoryType;
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public enum CategoryType{
        CATEGORY_ONE,
        CATEGORY_TWO,
        CATEGORY_THREE
    }
}
