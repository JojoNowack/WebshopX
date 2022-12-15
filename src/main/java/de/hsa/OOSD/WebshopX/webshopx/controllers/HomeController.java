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

import java.util.Collection;
import java.util.List;


@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword, @Param("selectedCategory") String catAsString) {

        List <Product> products = productService.findBySearchQuery(keyword);

        if (products instanceof Collection) {
            int size = ((Collection<?>) products).size();
            if (size < 1) {
                //System.out.println("no results");
                model.addAttribute("noProducts", "Zu dieser Suche konnten leider keine Produkte gefunden werden");
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "home_page_bootstrap5_2";
    }

    @GetMapping("/searchByCategory/{category}")
    public String searchByCategory(Model model, @PathVariable("category") String categoryName){
        List<Product> products;
        if(categoryName.equals("all")){
            products = productService.findAllProducts();
        } else{
            Category category = categoryService.findCategoryByName(categoryName);
            products = productService.findByCategory(category);
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "home_page_bootstrap5_2";
    }
}
