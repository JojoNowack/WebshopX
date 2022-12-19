package de.hsa.OOSD.WebshopX.webshopx.repositories;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    //taken from https://www.codejava.net/frameworks/spring-boot/spring-data-jpa-filter-search-examples
//    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.artist, ' ', p.date, ' ', p.price,' ',p.category,' ',p.description) LIKE %?1%")
//    public Iterable<Product> search(String keyword);

    List<Product> findDistinctByArtistContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String artist, String name, String description);

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE TO_CHAR(p.date) LIKE %:date%")
    List<Product> findByDateContaining(@Param("date")String date);

    @Query("SELECT p FROM Product p WHERE TO_CHAR(p.price) LIKE %:price%")
    List<Product> findByPriceContaining(@Param("price") String price);

    default List<Product> findBySearchQuery(String searchQuery){
        if (searchQuery != null) {
            List<Product> resultOne = findDistinctByArtistContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchQuery, searchQuery, searchQuery);
            List<Product> resultTwo = findByDateContaining(searchQuery);
            List<Product> resultThree = findByPriceContaining(searchQuery);

            Set<Product> resultSet = new HashSet<>(resultOne);
            resultSet.addAll(resultTwo);
            resultSet.addAll(resultThree);
            List<Product> products= new ArrayList<>(resultSet);
            return products;
        }
        return findAll();
    }

}

