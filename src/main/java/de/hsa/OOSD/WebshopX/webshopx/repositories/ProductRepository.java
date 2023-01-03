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

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long productId);

    List<Product> findDistinctByArtistContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String artist, String name, String description);

    List<Product> findProductsByDateStartingWith(String prefix);

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

