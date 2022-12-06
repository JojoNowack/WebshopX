package de.hsa.OOSD.WebshopX.webshopx.controllers;

import com.sun.istack.NotNull;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String home(Model model){

        Iterable<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);

        return "home_page_bootstrap5";
    }
}
