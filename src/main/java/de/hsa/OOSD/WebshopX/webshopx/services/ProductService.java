package de.hsa.OOSD.WebshopX.webshopx.services;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final String PRICE = "price";
    private final String YEAR = "year";
    private final String TITLE = "title";
    private final String DESCENDING = "desc";

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findBySearchQuery(String searchQuery) {
        return productRepository.findBySearchQuery(searchQuery);
    }

    public List<Product> findByPublicationYear(String publicationYear) {
        String prefix = publicationYear.substring(0,2);
        return productRepository.findProductsByPublicationYearStartingWith(prefix);
    }

    public List<Product> sortProducts(List<Product> products, String item, String direction) {
        List<Product> productsSorted;

        switch (item) {
            case PRICE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
            }
            case YEAR -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getPublicationYear)).collect(Collectors.toList());
            }
            case TITLE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
            }
            default -> productsSorted = findAllProducts();
        }

        if (direction.equals(DESCENDING)) Collections.reverse(productsSorted);

        return productsSorted;
    }

    public double getSumOfPrices(List<Product> products){
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}