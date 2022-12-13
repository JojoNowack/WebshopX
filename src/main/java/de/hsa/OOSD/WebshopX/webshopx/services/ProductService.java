package de.hsa.OOSD.WebshopX.webshopx.services;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * The Service class ProductService.
 */
@Service
public class ProductService {

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
    public Product getProductById(Long productId){
        return productRepository.findProductById(productId);
    }

    /**
     * Returns all products.
     *
     * @return All products from the repository.
     */
    public Iterable<Product> getAllProducts() {
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

    public Iterable<Product> getFilteredProducts(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword); //todo is casesensitiv
        }
        return productRepository.findAll();
    }
}

