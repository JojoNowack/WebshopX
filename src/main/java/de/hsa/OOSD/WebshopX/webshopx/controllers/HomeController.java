package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.models.User;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {
    private static ProductService productService;
    private static CategoryService categoryService;
    private static UserService userService;
    public static List<Product> products;

    public HomeController(ProductService productService, CategoryService categoryService, UserServiceImpl userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.products = productService.findAllProducts();
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword) {
        products = productService.findBySearchQuery(keyword);
        addAttributesForHome(model);
        return "home/home";
    }

    @GetMapping("/FAQ")
    public String faq(Model model) {
        return "home/faq";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "home/about_us";
    }

    public static void addAttributesForHome(Model model) {
        List<String> years = new ArrayList<>(Arrays.asList("1300-1399",
                "1400-1499",
                "1500-1599",
                "1600-1699",
                "1700-1799",
                "1800-1899",
                "1900-1999",
                "2000-heute"));
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("years", years);
        model.addAttribute("user", getUserFromId());

    }

    public static User getUserFromId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            User user = userService.findByEmail(email);
            return user;
        }
        return null;
    }
}
