package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.stereotype.Controller;


@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    // @GetMapping("/")
    // public String home(Model model){

    //   model.addAttribute("products", productService.getAllProducts());
    //    model.addAttribute("categories", categoryService.getAllCategories());

    //   return "home_page_bootstrap5";
    // }

}
