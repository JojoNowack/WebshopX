package de.hsa.OOSD.WebshopX.webshopx.controllers;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.models.User;
import de.hsa.OOSD.WebshopX.webshopx.services.EmailService;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/{userId}/toggle-cart/{productId}")
    public String toggleCartItem(Model model,
                                 HttpServletRequest request,
                                 @PathVariable("productId") Long productId,
                                 @PathVariable("userId") Long userId) {

        Product product = productService.findProductById(productId);
        User user = userService.findById(userId);
        System.out.println("----------------------------------------   "+user.getName());

        if (!user.containsCartItem(product)) {
            user.addCartItem(product);
        } else {
            user.removeCartItem(product);
        }
        userService.saveUser(user);
        HomeController.addAttributesForHome(model);
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("user", HomeController.getUserFromId());
        return "home/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String userEmail = authentication.getName();
            emailService.sendEmail(userEmail);
        }
        return "home/home";
    }
}

