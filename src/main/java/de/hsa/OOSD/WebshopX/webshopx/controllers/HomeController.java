package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private List<Product> products;

    public HomeController(ProductService productService, CategoryService categoryService, UserServiceImpl userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.products = productService.findAllProducts();
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword) {
        products = productService.findBySearchQuery(keyword);
        collectAttributesForModel(model);
        return "home/home";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        return "home/faq";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "home/about_us";
    }

    @GetMapping("/filter/category={category}")
    public String filterByCategory(Model model, @PathVariable("category") String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        products = productService.findByCategory(category);
        collectAttributesForModel(model);
        return "home/home";
    }

    @GetMapping("/filter/period={year}")
    public String filterByYear(Model model, @PathVariable("year") String year) {
        products = productService.findByPublicationYear(year);
        collectAttributesForModel(model);
        return "home/home";
    }

    @GetMapping("/sort/{item}/{direction}")
    public String sortByItemUsingDirection(Model model,
                                           @PathVariable("item") String item,
                                           @PathVariable("direction") String direction) {
        products = productService.sortProducts(products, item, direction);
        collectAttributesForModel(model);
        return "home/home";
    }

    private void collectAttributesForModel(Model model) {
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
        model.addAttribute("user", userService.getCurrentUser());
    }
}
