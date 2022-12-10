package de.hsa.OOSD.WebshopX.webshopx;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.CategoryRepository;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public DataInitializer(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    private void initialize(){
        Category category1 = new Category(Category.CategoryType.CATEGORY_ONE, "ONE");
        Category category2 = new Category(Category.CategoryType.CATEGORY_TWO, "TWO");
        Category category3 = new Category(Category.CategoryType.CATEGORY_THREE, "THREE");
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        Product product1 = new Product("Product 1", 1f, "A simple Product 1", category1);
        Product product2 = new Product("Product 2", 2f, "A simple Product 2", category2);
        Product product3 = new Product("Product 3", 3f, "A simple Product 3", category3);
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
    }

    @Override
    public void run(String... args) {
        initialize();
    }
}
