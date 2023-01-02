package de.hsa.OOSD.WebshopX.webshopx;

import de.hsa.OOSD.WebshopX.webshopx.controllers.HomeController;
import de.hsa.OOSD.WebshopX.webshopx.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebshopxApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private HomeController homeController;
	@Autowired
	private ProductController productController;

	@Test
	void controllerLoads() {
		assertThat(homeController).isNotNull();
		assertThat(productController).isNotNull();
		assertThat(Boolean.TRUE).isTrue();
	}

}
