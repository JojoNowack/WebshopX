package de.hsa.OOSD.WebshopX.webshopx.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CustomerCartItems {

    private final ArrayList<Product> costumerProducts = new ArrayList<>();

    private CustomerCartItems() {

    }
    private static CustomerCartItems instance;

    public static CustomerCartItems getInstance() {

        if(instance == null){
            instance = new CustomerCartItems();
        }

        return instance;
    }

    public ArrayList<Product> getCostumerProducts() {
        return costumerProducts;
    }

    public ArrayList addToCostumerProducts(Product product){
        costumerProducts.add(product);
        return costumerProducts;
    }

    public ArrayList removeDuplicates(){
        Set<Product> tempSet = new HashSet<>(costumerProducts);
        costumerProducts.clear();
        costumerProducts.addAll(tempSet);

        return costumerProducts;
    }
}
