package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.models.User;
import de.hsa.OOSD.WebshopX.webshopx.services.EmailService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private final ProductService productService;
    private final UserService userService;
    private final EmailService emailService;


    public CartController(ProductService productService, UserService userService, EmailService emailService) {
        this.productService = productService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/toggle-cart/{productId}")
    public String toggleCartItem(HttpServletRequest request,
                                 @PathVariable("productId") Long productId) {

        Product product = productService.findProductById(productId);
        User user = userService.getCurrentUser();

        if (!user.containsCartItem(product)) {
            user.addCartItem(product);
        } else {
            user.removeCartItem(product);
        }
        userService.saveUser(user);

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        User user = userService.getCurrentUser();

        model.addAttribute("user", user);
        model.addAttribute("sum", productService.getSumOfPrices(user.getCartItems()));

        return "home/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        User user = userService.getCurrentUser();

        if (user.getCartItems() == null && user.getCartItems().size() == 0){
            return "home/home";
        }

        List<Product> cartItems = user.getCartItems();

        model.addAttribute("user", user);
        model.addAttribute("sum", productService.getSumOfPrices(user.getCartItems()));
        model.addAttribute("cartItems", cartItems);


        // try to send the confirmation mail
        try{
            emailService.setBillContent(cartItems, productService.getSumOfPrices(cartItems));
            emailService.sendEmail(user.getEmail());

            for (Product item : user.getCartItems()) {
                item.toggleStatus();
                productService.save(item);
            }

            user.setCartItems(new ArrayList<>());
            userService.saveUser(user);

        } catch (Exception e){
            return "home/order_failed";
        }

        return "home/order_confirmation";
    }
}

