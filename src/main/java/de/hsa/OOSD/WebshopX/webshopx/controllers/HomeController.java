package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.CategoryService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;


@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping("/")
    public String home(Model model, @Param("keyword") String keyword, @Param("order") String order) {

        System.out.println(keyword);
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

}
