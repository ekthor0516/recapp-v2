package mx.ipn.tesis.recapp.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import mx.ipn.tesis.recapp.samples.backend.data.Category;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;
import mx.ipn.tesis.recapp.samples.backend.mock.ServicioMockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class ServicioDataService implements Serializable {

    public abstract Collection<Servicio> getAllProducts();

    public abstract Collection<Category> getAllCategories();

    public abstract void updateProduct(Servicio p);

    public abstract void deleteProduct(int productId);

    public abstract Servicio getProductById(int productId);

    public static ServicioDataService get() {
        return ServicioMockDataService.getInstance();
    }

}
