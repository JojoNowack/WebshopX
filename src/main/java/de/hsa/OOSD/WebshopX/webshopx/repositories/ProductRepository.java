package de.hsa.OOSD.WebshopX.webshopx.repositories;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    //taken from https://www.codejava.net/frameworks/spring-boot/spring-data-jpa-filter-search-examples
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.artist, ' ', p.date, ' ', p.price,' ',p.category,' ',p.description) LIKE %?1%")
    public Iterable<Product> search(String keyword);
}
