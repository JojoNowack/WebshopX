package de.hsa.OOSD.WebshopX.webshopx;

import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class WebshopxApplication {

	// Test comment by Andreas Schmid
	// Test comment by Tobias Artz
	public static void main(String[] args) {
		SpringApplication.run(WebshopxApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(ProductService productService) {
		return args -> {
			productService.save(new Product("Smartphone", 500.0f, "A simple Smartphone", "/images/lebensmittelgeschaft.png"));
			productService.save(new Product("Tablet", 300.0f, "A simple Tablet", "/images/waren.png"));
			productService.save(new Product("SmartWatch", 550.0f, "A simple SmartWatch", "/images/lebensmittelgeschaft.png"));
			productService.save(new Product("Earpods", 600.0f, "Simple Earpods", "/images/waren.png"));
		};
	}
}
