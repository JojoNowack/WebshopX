package de.hsa.OOSD.WebshopX.webshopx.repositories;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface ProductRepository.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Finds a product based on its id.
     *
     * @param productId The id of a specific product.
     * @return The product of the given productId.
     */
    Product findProductById(Long productId);
}
