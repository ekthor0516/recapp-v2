package mx.ipn.tesis.recapp.samples.backend.mock;

import java.util.List;

import mx.ipn.tesis.recapp.samples.backend.ServicioDataService;
import mx.ipn.tesis.recapp.samples.backend.data.Category;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class ServicioMockDataService extends ServicioDataService {

    private static ServicioMockDataService INSTANCE;

    private List<Servicio> products;
    private List<Category> categories;
    private int nextProductId = 0;

    private ServicioMockDataService() {
        categories = ServicioMockDataGenerator.createCategories();
        products = ServicioMockDataGenerator.createProducts(categories);
        nextProductId = products.size() + 1;
    }

    public synchronized static ServicioDataService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServicioMockDataService();
        }
        return INSTANCE;
    }

    @Override
    public synchronized List<Servicio> getAllProducts() {
        return products;
    }

    @Override
    public synchronized List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public synchronized void updateProduct(Servicio p) {
        if (p.getId() < 0) {
            // New product
            p.setId(nextProductId++);
            products.add(p);
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == p.getId()) {
                products.set(i, p);
                return;
            }
        }

        throw new IllegalArgumentException("El manifiesto con el número  " + p.getId()
                + " encontrado");
    }

    @Override
    public synchronized Servicio getProductById(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                return products.get(i);
            }
        }
        return null;
    }

    @Override
    public synchronized void deleteProduct(int productId) {
        Servicio p = getProductById(productId);
        if (p == null) {
            throw new IllegalArgumentException("Servicio con número de manifiesto " + productId
                    + " no encontrado");
        }
        products.remove(p);
    }
}
