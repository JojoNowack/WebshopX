package de.hsa.OOSD.WebshopX.webshopx.initialization;

import de.hsa.OOSD.WebshopX.webshopx.models.Category;
import de.hsa.OOSD.WebshopX.webshopx.models.Product;
import de.hsa.OOSD.WebshopX.webshopx.repositories.CategoryRepository;
import de.hsa.OOSD.WebshopX.webshopx.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public DataInitializer(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    private void initialize() {
        Category Malerei = new Category(Category.CategoryType.Malerei, "Malerei");
        Category Plastik = new Category(Category.CategoryType.Plastik, "Plastik");
        Category Grafik = new Category(Category.CategoryType.Grafik, "Grafik");
        Category Fluegelaltar = new Category(Category.CategoryType.Fluegelaltar, "Fluegelaltar");
        categoryRepository.save(Malerei);
        categoryRepository.save(Plastik);
        categoryRepository.save(Grafik);
        categoryRepository.save(Fluegelaltar);

        productService.save(new Product("Maria mit Kind und einem Mönch", "PARMIGIANINO", 9999.0f, 1530, Malerei, "Bayerische Staatsgemaeldesammlungen - Alte Pinakothek Muenchen", "/images/5289.jpg"));
        productService.save(new Product("Bildnis des Pfalzgrafen Christian III. von Birkenfeld-Bischweiler-Rappoltstein", "HYACINTHE RIGAUD", 9999.0f, 1743, Malerei, "1901 aus Privatbesitz durch Ankauf erworben", "/images/8176.jpg"));
        productService.save(new Product("Gerüst eines Neubaues", "PAUL KLEE",  8999.0f, 1930, Malerei, "1963 als Ankauf von Dr. Fritz und Dr. Peter Nathan, Zuerich, erworben", "/images/13338.jpg"));
        productService.save(new Product("Bildnis Gött", "PAUL ROLOFF", 7999.0f, 1933, Malerei, "1937 erworben als Ankauf durch den Staatsminister des Inneren.", "/images/10325.jpg"));
        productService.save(new Product("König Max I. Joseph", "Herr Deutsch", 6999.0f, 1806, Malerei, "1964 durch das Bayerische Staatsministerium des Inneren uebertragen", "/images/13449.jpg"));
        productService.save(new Product("Abendmahl", "GUGLIELMUS PALUDANUS", 5999.0f, 1560, Plastik, "Erworben 1807 als Saekularisationsgut aus der Dominikanerkirche Augsburg", "/images/B619.jpg"));
        productService.save(new Product("Gegen den Wind Schreitender", "ADOLF HÖLZEL", 5999.0f, 1925, Grafik, "1964 als Schenkung von Sofie und Emanuel Fohn erworben", "/images/13501.jpg"));

        productService.save(new Product("Die Madonna Tempi", "RAFFAEL", 4999.0f, 1507, Malerei, "1829 für König Ludwig I. aus dem Hause Tempi in Florenz erworben", "/images/796.jpg"));
        productService.save(new Product("Kirchliche Szene", "RAFFAEL", 4999.0f, 1722, Grafik, "Stich von Francesco Aquila nach Raffaels Borgobrand", "/images/19.jpg"));
        productService.save(new Product("Kauernde", "AUGUSTE RODIN", 4999.0f, 1880, Plastik, "1912 als Schenkung im Rahmen der Tschudi-Spende erworben", "/images/58.jpg"));
        productService.save(new Product("Tirol", "FRANZ MARC", 4999.0f, 1914, Malerei, "1949 als Ankauf von Maria Marc, Benediktbeuern erworben", "/images/10973.jpg"));
        productService.save(new Product("Jenenser Student", "FERDINAND HODLER", 4999.0f, 1908, Malerei, "1912 als Schenkung von Friedrich Wilhelm Freiherr von Bissing im Rahmen der Tschudi-Spende erworben", "/images/8643.jpg"));
        productService.save(new Product("Double Loop", "LÁSZLO MOHOLY-NAGY", 4999.0f, 1946, Plastik, "transparenter Kunststoff, thermisch verformt", "/images/357.jpg"));
        productService.save(new Product("La Bouteille de Bordeaux", "JUAN GRIS", 4999.0f, 1915, Malerei, "1971 als Vermächtnis von Theodor und Woty Werner erworben", "/images/14237.jpg"));
        productService.save(new Product("Hausaltärchen Kleiner Dom", "KÖLNISCH",  3999.0f, 1370, Fluegelaltar, "mit plastischer Verkündigungsgruppe (Schrein), Szenen aus der Kindheit Jesu und Heiligen Flügelaußenseiten: Verkündigung", "/images/454.jpg"));
        productService.save(new Product("Reliquienaltärchen mit Verkündigung", "KÖLNISCH", 2999.0f, 1349, Fluegelaltar, "Geburt Christi, Marienkrönung, Taufe Christi und nicht zugehöriger Elfenbeinstatuette der Muttergottes Flügelaußenseite: Hl. Bischof und hl. Gereon,", "/images/454.jpg"));

    }

    @Override
    public void run(String... args) {
        initialize();
    }
}
