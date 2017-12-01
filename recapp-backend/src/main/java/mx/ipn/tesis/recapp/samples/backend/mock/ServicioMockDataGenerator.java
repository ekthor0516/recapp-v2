package mx.ipn.tesis.recapp.samples.backend.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mx.ipn.tesis.recapp.samples.backend.data.Availability;
import mx.ipn.tesis.recapp.samples.backend.data.Category;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

public class ServicioMockDataGenerator {
    private static int nextCategoryId = 1;
    private static int nextProductId = 1;
    private static final Random random = new Random(1);
    private static final String categoryNames[] = new String[] {
            "Residuos Médicos", "Residuos Industriales", "Residuos Quimicos", "Aguas Tratadas",
            "Residuos Infecciosos" };

    private static String[] word1 = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
             "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
             "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
             "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
             "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
             "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
             "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
             "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
             "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" };

    private static String[] word2 = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
             "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
             "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
             "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
             "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
             "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
             "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
             "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
             "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" };
    
    private static String[] word3 = new String[] { "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
             "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
             "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
             "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
             "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
             "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
             "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
             "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
             "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" };

    static List<Category> createCategories() {
        List<Category> categories = new ArrayList<Category>();
        for (String name : categoryNames) {
            Category c = createCategory(name);
            categories.add(c);
        }
        return categories;

    }

    static List<Servicio> createProducts(List<Category> categories) {
        List<Servicio> products = new ArrayList<Servicio>();
        for (int i = 0; i < 100; i++) {
            Servicio p = createProduct(categories);
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

    private static Servicio createProduct(List<Category> categories) {
        Servicio p = new Servicio();
        p.setId(nextProductId++);
        p.setProductName(generateName());

        p.setPrice(new BigDecimal((random.nextInt(250000) + 5000) / 10.0));
        p.setAvailability(Availability.values()[random.nextInt(Availability
                .values().length)]);
        if (p.getAvailability() == Availability.AVAILABLE) {
            p.setStockCount(random.nextInt(1));
        }

        p.setCategory(getCategory(categories, 1, 2));
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
        return word1[random.nextInt(word1.length)] + word2[random.nextInt(word2.length)] + word3[random.nextInt(word3.length)];
    }

}
