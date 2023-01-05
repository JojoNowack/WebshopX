package de.hsa.OOSD.WebshopX.webshopx.initialization;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.CategoryRepository;
import de.hsa.OOSD.WebshopX.webshopx.repositories.RoleRepository;
import de.hsa.OOSD.WebshopX.webshopx.models.User;
import de.hsa.OOSD.WebshopX.webshopx.repositories.UserRepository;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductService productService, CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void initialize() {
        userRepository.save(new User(1l,"test","test@test.de", passwordEncoder.encode("test"), Arrays.asList(roleRepository.findByName("ROLE_ADMIN")), new ArrayList<>()));
        userRepository.save(new User(2l,"test","andyschmid3@gmail.com", passwordEncoder.encode("test"), Arrays.asList(roleRepository.findByName("ROLE_ADMIN")), new ArrayList<>()));

        Category Malerei = new Category(Category.CategoryType.Malerei, "Malerei");
        Category Plastik = new Category(Category.CategoryType.Plastik, "Plastik");
        Category Grafik = new Category(Category.CategoryType.Grafik, "Grafik");
        Category Fluegelaltar = new Category(Category.CategoryType.Fluegelaltar, "Fluegelaltar");
        categoryRepository.save(Malerei);
        categoryRepository.save(Plastik);
        categoryRepository.save(Grafik);
        categoryRepository.save(Fluegelaltar);

        productService.save(new Product("Maria mit Kind und einem Mönch", "Parmigianino", 10000.00, "1530", Malerei, "Bayerische Staatsgemaeldesammlungen - Alte Pinakothek Muenchen", "/images/products/5289.jpg"));
        productService.save(new Product("Bildnis des Pfalzgrafen Christian III. von Birkenfeld-Bischweiler-Rappoltstein", "Hyacinthe Rigaud", 9999.99, "1743", Malerei, "1901 aus Privatbesitz durch Ankauf erworben", "/images/products/8176.jpg"));
        productService.save(new Product("Gerüst eines Neubaues", "Paul Klee", 6500.00, "1930", Malerei, "1963 als Ankauf von Dr. Fritz und Dr. Peter Nathan, Zuerich, erworben", "/images/products/13338.jpg"));
        productService.save(new Product("Bildnis Gött", "Paul Roloff", 9995.95, "1933", Malerei, "1937 erworben als Ankauf durch den Staatsminister des Inneren.", "/images/products/10325.jpg"));
        productService.save(new Product("König Max I. Joseph", "Herr Deutsch", 9000.00, "1806", Malerei, "1964 durch das Bayerische Staatsministerium des Inneren uebertragen", "/images/products/13449.jpg"));
        productService.save(new Product("Abendmahl", "Guglielmus Paludanus", 24999.99, "1560", Plastik, "Erworben 1807 als Saekularisationsgut aus der Dominikanerkirche Augsburg", "/images/products/B619.jpg"));
        productService.save(new Product("Gegen den Wind Schreitender", "Adolf Hölzel", 10000.00, "1925", Grafik, "1964 als Schenkung von Sofie und Emanuel Fohn erworben", "/images/products/13501.jpg"));
        productService.save(new Product("Die Madonna Tempi", "Raffael", 300000.00, "1507", Malerei, "1829 für König Ludwig I. aus dem Hause Tempi in Florenz erworben", "/images/products/796.jpg"));
        productService.save(new Product("Kirchliche Szene", "Raffael", 75500.00, "1722", Grafik, "Stich von Francesco Aquila nach Raffaels Borgobrand", "/images/products/19.jpg"));
        productService.save(new Product("Kauernde", "Auguste Rodin", 8000.00, "1880", Plastik, "1912 als Schenkung im Rahmen der Tschudi-Spende erworben", "/images/products/58.jpg"));
        productService.save(new Product("Tirol", "Franz Marc", 12999.99, "1914", Malerei, "1949 als Ankauf von Maria Marc, Benediktbeuern erworben", "/images/products/10973.jpg"));
        productService.save(new Product("Jenenser Student", "Ferdinand Hodler", 650.00, "1908", Malerei, "1912 als Schenkung von Friedrich Wilhelm Freiherr von Bissing im Rahmen der Tschudi-Spende erworben", "/images/products/8643.jpg"));
        productService.save(new Product("Double Loop", "Lászlo Moholy-Nagy", 670.00, "1946", Plastik, "transparenter Kunststoff, thermisch verformt", "/images/products/357.jpg"));
        productService.save(new Product("La Bouteille de Bordeaux", "Juan Gris", 4000.00, "1915", Malerei, "1971 als Vermächtnis von Theodor und Woty Werner erworben", "/images/products/14237.jpg"));
        productService.save(new Product("Hausaltärchen Kleiner Dom", "Kölnisch", 6000.00, "1370", Fluegelaltar, "mit plastischer Verkündigungsgruppe (Schrein), Szenen aus der Kindheit Jesu und Heiligen Flügelaußenseiten: Verkündigung", "/images/products/454.jpg"));
        productService.save(new Product("Reliquienaltärchen mit Verkündigung", "Kölnisch", 4300.00, "1349", Fluegelaltar, "Geburt Christi, Marienkrönung, Taufe Christi und nicht zugehöriger Elfenbeinstatuette der Muttergottes Flügelaußenseite: Hl. Bischof und hl. Gereon,", "/images/products/WAF453.jpg"));

    }

    @Override
    public void run(String... args) {
        initialize();
    }
}
