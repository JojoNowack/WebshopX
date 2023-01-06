package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final UserService userService;

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/product/{productId}")
    public String product(Model model, @PathVariable("productId") Long productId) {
        Product product = productService.findProductById(productId);
        model.addAttribute(product);
        model.addAttribute("user", userService.getCurrentUser());
        return "home/product";
    }
}
