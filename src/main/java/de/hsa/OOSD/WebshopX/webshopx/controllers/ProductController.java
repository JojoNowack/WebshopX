package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }




    private final ArrayList<Product> costumerProducts = new ArrayList<>();


    @GetMapping("/{name}")
    public String searchByCategory(Model model, @PathVariable("name") String name) {

        Product chosenProduct = null;

        List<Product> allProducts = productService.findAllProducts();
        for (Product product : allProducts) {
            System.out.println(name);
            System.out.println(product.getName());
            System.out.println(name.equals(product.getName()));
            if (name.equals(product.getName())) {
                chosenProduct = product;
                break;
            }
        }

        model.addAttribute("product", chosenProduct);


        return "article";
    }


    @PostMapping("/myCart")
    public String myCart(@RequestParam(name="addCart") String name, Model model) {
        Product chosenProduct = null;

        List<Product> allProducts = productService.findAllProducts();
        for (Product product : allProducts) {
            System.out.println(name);
            System.out.println(product.getName());
            System.out.println(name.equals(product.getName()));
            if (name.equals(product.getName())) {
                chosenProduct = product;
                break;
            }
        }

        // add to shopping cart
        costumerProducts.add(chosenProduct);

        // remove duplicates
        Set<Product> tempSet = new HashSet<>(costumerProducts);
        costumerProducts.clear();
        costumerProducts.addAll(tempSet);


        model.addAttribute("product", chosenProduct);

        return "article";
    }

    @GetMapping("/warenkorb")
    public String my_articles(Model model) {

        model.addAttribute("customerProducts", costumerProducts);
        return "warenkorb";
    }


    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/gemaelde")
    public String gemealde(Model model) {
        return "gemealde";
    }

    @GetMapping("/FAQ")
    public String FAQ(Model model) {
        return "FAQ";
    }

    @GetMapping("/ueber_uns")
    public String ueber_uns(Model model) {
        return "ueber_uns";
    }

}
