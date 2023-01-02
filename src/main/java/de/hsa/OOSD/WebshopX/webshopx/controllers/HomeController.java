package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.CustomerItems;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private List<Product> products;

    private Product lastProduct = null;

    private CustomerItems singletonCart = CustomerItems.getInstance();
    private ArrayList<Product> costumerProducts = singletonCart.getCostumerProducts();

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

    @PostMapping("/myCartHome")
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
        costumerProducts = singletonCart.addToCostumerProducts(chosenProduct);

        // remove duplicates
        costumerProducts = singletonCart.removeDuplicates();

        model.addAttribute("products", allProducts);

        return "redirect:/";
    }

    @GetMapping("/searchByCategory/{category}")
    public String searchByCategory(Model model, @PathVariable("category") String categoryName){

        Product chosenProduct = null;

        List<Product> allProducts = productService.findAllProducts();
        for (Product product : allProducts) {
            System.out.println(categoryName);
            System.out.println(product.getName());
            System.out.println(categoryName.equals(product.getName()));
            if (categoryName.equals(product.getName())) {
                chosenProduct = product;
                break;
            }
        }

        // article
        if(chosenProduct != null){
            lastProduct = chosenProduct;
            model.addAttribute("product", chosenProduct);
            model.addAttribute("shoppingCart", costumerProducts);


            return "article";
        }
        //category
        else {


            if (categoryName.equals("all")) {
                products = productService.findAllProducts();
            } else {
                Category category = categoryService.findCategoryByName(categoryName);
                products = productService.findByCategory(category);
            }

            model.addAttribute("products", products);
            model.addAttribute("categories", categoryService.findAllCategories());

            return "home_page_bootstrap5_2";
        }

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
