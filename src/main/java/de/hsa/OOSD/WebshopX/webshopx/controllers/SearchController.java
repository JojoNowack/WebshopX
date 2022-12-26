package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }




    private final ArrayList<Product> costumerProducts = new ArrayList<>();



    @GetMapping("/searchByCategory/warenkorb")
    public String my_articles(Model model) {
        Set<Product> set_costumer_products = new HashSet<Product>(costumerProducts);

        model.addAttribute("customerProducts", set_costumer_products);
        return "warenkorb";
    }


    @GetMapping("/searchByCategory/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/searchByCategory/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/searchByCategory/gemaelde")
    public String gemealde(Model model) {
        return "gemealde";
    }

    @GetMapping("/searchByCategory/FAQ")
    public String FAQ(Model model) {
        return "FAQ";
    }

    @GetMapping("/searchByCategory/ueber_uns")
    public String ueber_uns(Model model) {
        return "ueber_uns";
    }


}
