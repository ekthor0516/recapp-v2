package mx.ipn.tesis.recapp.samples.backend.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mx.ipn.tesis.recapp.samples.backend.data.Availability;
import mx.ipn.tesis.recapp.samples.backend.data.Category;
import mx.ipn.tesis.recapp.samples.backend.data.Product;

public class MockDataGeneratorRESPALDO {
    private static int nextCategoryId = 1;
    private static int nextProductId = 1;
    private static final Random random = new Random(1);
    private static final String categoryNames[] = new String[] {
            "Residuos Médicos", "Residuos Industriales", "Residuos Quimicos", "Aguas Tratadas",
            "Residuos Infecciosos" };

    private static String[] word1 = new String[] { "Servicios ", "Clinica",
            "Instituto Mexicano", "Sistema Nacional", "Hospital General",
            "Centro de Atención", "Almacen" };

    private static String[] word2 = new String[] { " especializado",
            " bacteriologico", " salud", "industrial",
            "central", "procesos" };

    static List<Category> createCategories() {
        List<Category> categories = new ArrayList<Category>();
        for (String name : categoryNames) {
            Category c = createCategory(name);
            categories.add(c);
        }
        return categories;

    }

    static List<Product> createProducts(List<Category> categories) {
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < 100; i++) {
            Product p = createProduct(categories);
            products.add(p);
        }

        return products;
    }

    private static Category createCategory(String name) {
        Category c = new Category();
        c.setId(nextCategoryId++);
        c.setName(name);
        return c;
    }

    private static Product createProduct(List<Category> categories) {
        Product p = new Product();
        p.setId(nextProductId++);
        p.setProductName(generateName());

        p.setPrice(new BigDecimal((random.nextInt(250000) + 5000) / 10.0));
        p.setAvailability(Availability.values()[random.nextInt(Availability
                .values().length)]);
        if (p.getAvailability() == Availability.AVAILABLE) {
            p.setStockCount(random.nextInt(1));
        }

        p.setCategory(getCategory(categories, 1, 1));
        return p;
    }

    private static Set<Category> getCategory(List<Category> categories,
            int min, int max) {
        int nr = random.nextInt(max) + min;
        HashSet<Category> productCategories = new HashSet<Category>();
        for (int i = 0; i < nr; i++) {
            productCategories.add(categories.get(random.nextInt(categories
                    .size())));
        }

        return productCategories;
    }

    private static String generateName() {
        return word1[random.nextInt(word1.length)] + " "
                + word2[random.nextInt(word2.length)];
    }

}
