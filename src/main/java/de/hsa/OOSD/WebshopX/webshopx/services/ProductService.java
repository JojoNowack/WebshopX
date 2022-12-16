package de.hsa.OOSD.WebshopX.webshopx.services;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Service class ProductService.
 */
@Service
public class ProductService {

    private final String PRICE = "price";
    private final String YEAR = "year";
    private final String TITLE = "title";
    private final  String DESCENDING = "desc";

    /**
     * The productRepository of the service.
     */
    private final ProductRepository productRepository;

    /**
     * Creates a new ProductService.
     *
     * @param productRepository A ProductRepository of all products.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Finds a product based on its id.
     *
     * @param productId The id of a specific product.
     * @return The product of the given productId.
     */
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    /**
     * Returns all products.
     *
     * @return All products from the repository.
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }


    /**
     * Saves a new product instance in the repository.
     *
     * @param product The new product to be saved.
     * @return The instance of the new Product.
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findBySearchQuery(String searchQuery) {
        return productRepository.findBySearchQuery(searchQuery);
    }

    public List<Product> sortProducts(List<Product> products, String item, String direction) {

        List<Product> productsSorted;

        switch (item) {
            case PRICE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
            }

            case YEAR -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getDate)).collect(Collectors.toList());
            }

            case TITLE -> {
                productsSorted = products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
            }

            default -> productsSorted = findAllProducts();
        }

        if (direction.equals(DESCENDING)) Collections.reverse(productsSorted);

        return productsSorted;
    }

}