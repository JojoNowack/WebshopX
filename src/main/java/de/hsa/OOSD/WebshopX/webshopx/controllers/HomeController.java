package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private List<Product> products;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.products = productService.findAllProducts();
    }

    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword, @Param("selectedCategory") String catAsString) {

        products = productService.findBySearchQuery(keyword);

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAllCategories());

        return "home_page_bootstrap5_2";
    }

    @GetMapping("/searchByCategory/{category}")
    public String searchByCategory(Model model, @PathVariable("category") String categoryName){

        if(categoryName.equals("all")){
            products = productService.findAllProducts();
        } else{
            Category category = categoryService.findCategoryByName(categoryName);
            products = productService.findByCategory(category);
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAllCategories());

        return "home_page_bootstrap5_2";
    }

    @GetMapping("/sort/{item}/{direction}")
    public String sortByItemUsingDirection(Model model, @PathVariable("item")
    String item, @PathVariable("direction") String direction){

        products = productService.sortProducts(products,item, direction);

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAllCategories());

        return "home_page_bootstrap5_2";
    }
}
