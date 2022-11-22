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
    public Product findProductById(Long productId){
        return productRepository.findProductById(productId);
    }
}
