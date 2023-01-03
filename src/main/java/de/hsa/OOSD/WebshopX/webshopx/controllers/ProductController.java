package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product/{productId}")
    public String product(Model model, @PathVariable("productId") Long productId){
        Product product = productService.findProductById(productId);
        model.addAttribute(product);
        return "product";
    }

    @GetMapping("/filter/category={category}")
    public String filterByCategory(Model model, @PathVariable("category") String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        HomeController.products = productService.findByCategory(category);
        HomeController.addAttributesForHome(model);
        return "home";
    }

    @GetMapping("/filter/period={year}")
    public String filterByYear(Model model, @PathVariable("year") String year) {
        HomeController.products = productService.findByYear(year);
        HomeController.addAttributesForHome(model);
        return "home";
    }

    @GetMapping("/sort/{item}/{direction}")
    public String sortByItemUsingDirection(Model model,
                                           @PathVariable("item") String item,
                                           @PathVariable("direction") String direction) {
        HomeController.products = productService.sortProducts(HomeController.products, item, direction);
        HomeController.addAttributesForHome(model);
        return "home";
    }

}
