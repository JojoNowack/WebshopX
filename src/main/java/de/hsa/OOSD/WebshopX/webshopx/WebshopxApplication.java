package de.hsa.OOSD.WebshopX.webshopx;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
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
			//productService.save(new Product("Smartphone", 500.0f, "A simple Smartphone", "/images/lebensmittelgeschaft.png"));
			//productService.save(new Product("Tablet", 300.0f, "A simple Tablet", "/images/waren.png"));
			//productService.save(new Product("SmartWatch", 550.0f, "A simple SmartWatch", "/images/lebensmittelgeschaft.png"));
			//productService.save(new Product("Earpods", 600.0f, "Simple Earpods", "/images/waren.png"));
			//productService.save(new Product("Maria mit Kind und einem Mönch", "PARMIGIANINO", 9999.0f, 1530, Category.Malerei, "Bayerische Staatsgemäldesammlungen - Alte Pinakothek München", "/images/5289.jpg"));
			productService.save(new Product("Bildnis des Pfalzgrafen Christian III. von Birkenfeld-Bischweiler-Rappoltstein", "HYACINTHE RIGAUD", 9999.0f, 1743, Category.Malerei, "1901 aus Privatbesitz durch Ankauf erworben", "/images/8176.jpg"));
			productService.save(new Product("Gerüst eines Neubaues", "PAUL KLEE", 8999.0f, 1930, Category.Malerei, "1963 als Ankauf von Dr. Fritz und Dr. Peter Nathan, Zürich, erworben", "/images/13338.jpg"));
			productService.save(new Product("Bildnis Gött", "PAUL ROLOFF", 7999.0f, 1933, Category.Malerei, "1937 erworben als Ankauf durch den Staatsminister des Inneren.", "/images/10325.jpg"));
			productService.save(new Product("König Max I. Joseph", "Herr Deutsch", 6999.0f, 1806, Category.Malerei, "1964 durch das Bayerische Staatsministerium des Inneren übertragen", "/images/13449.jpg"));
			productService.save(new Product("Abendmahl", "GUGLIELMUS PALUDANUS", 5999.0f, 1560, Category.Plastik, "Erworben 1807 als Säkularisationsgut aus der Dominikanerkirche Augsburg", "/images/B619.jpg"));
			productService.save(new Product("Gegen den Wind Schreitender", "ADOLF HÖLZEL", 5999.0f, 1925, Category.Grafik, "1964 als Schenkung von Sofie und Emanuel Fohn erworben", "/images/13501.jpg"));
		};
	}
}
