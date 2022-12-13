package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;


@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword, @Param("order") String order) {

        //System.out.println(keyword);
        //System.out.println(order);

        Iterable<Product> filteredProducts = productService.getFilteredProducts(keyword);
        if (filteredProducts instanceof Collection) {
            int size = ((Collection<?>) filteredProducts).size();
            if (size < 1) {
                //System.out.println("no results");
                model.addAttribute("noProducts", "Zu dieser Suche konnten leider keine Produkte gefunden werden");
            }
        }
        model.addAttribute("products", filteredProducts);
        model.addAttribute("categories", categoryService.getAllCategories());


        return "home_page_bootstrap5";
    }

    private final ArrayList<Product> costumerProducts = new ArrayList<>();




    @GetMapping("/{name}")
    public String article(@PathVariable("name") String name, Model model) {
        Product chosenProduct = null;

        Iterable<Product> allProducts = productService.getAllProducts();
        for (Product product : allProducts) {
            System.out.println(name);
            System.out.println(product.getName());
            System.out.println(name.equals(product.getName()));
            if (name.equals(product.getName())) {
                chosenProduct = product;
                break;
            }
        }
        costumerProducts.add(chosenProduct);
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
