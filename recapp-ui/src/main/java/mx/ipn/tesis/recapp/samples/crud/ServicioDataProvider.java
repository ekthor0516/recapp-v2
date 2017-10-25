package mx.ipn.tesis.recapp.samples.crud;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import mx.ipn.tesis.recapp.samples.backend.ServicioDataService;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;

public class ServicioDataProvider
        extends AbstractDataProvider<Servicio, String> {
    
    /** Text filter that can be changed separately. */
    private String filterText = "";

    /**
     * Store given product to the backing data service.
     * 
     * @param product
     *            the updated or new product
     */
    public void save(Servicio product) {
        boolean newProduct = product.getId() == -1;
        
        ServicioDataService.get().updateProduct(product);
        if (newProduct) {
            refreshAll();
        } else {
            refreshItem(product);
        }
    }

    /**
     * Delete given product from the backing data service.
     * 
     * @param product
     *            the product to be deleted
     */
    public void delete(Servicio product) {
        ServicioDataService.get().deleteProduct(product.getId());
        refreshAll();
    }
    
    /**
     * Sets the filter to use for the this data provider and refreshes data.
     * <p>
     * Filter is compared for product name, availability and category.
     * 
     * @param filterText
     *           the text to filter by, never null
     */
    public void setFilter(String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null");
        if (Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim();
        
        refreshAll();
    }
    
    @Override
    public Integer getId(Servicio product) {
        Objects.requireNonNull(product, "Cannot provide an id for a null product.");
        
        return product.getId();
    }
    
    @Override
    public boolean isInMemory() {
        return true;
    }

    @Override
    public int size(Query<Servicio, String> t) {
        return (int) fetch(t).count();
    }

    @Override
    public Stream<Servicio> fetch(Query<Servicio, String> query) {
        if (filterText.isEmpty()) {
            return ServicioDataService.get().getAllProducts().stream();
        }
        return ServicioDataService.get().getAllProducts().stream().filter(
                product -> passesFilter(product.getProductName(), filterText)
                        || passesFilter(product.getAvailability(), filterText)
                        || passesFilter(product.getCategory(), filterText));
    }

    private boolean passesFilter(Object object, String filterText) {
        return object != null && object.toString().toLowerCase(Locale.ENGLISH)
                .contains(filterText);
    }
}
