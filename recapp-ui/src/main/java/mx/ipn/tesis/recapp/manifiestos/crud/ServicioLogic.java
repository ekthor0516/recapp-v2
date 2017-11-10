package mx.ipn.tesis.recapp.manifiestos.crud;

import mx.ipn.tesis.recapp.MyUI;
import mx.ipn.tesis.recapp.samples.backend.ServicioDataService;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

import java.io.Serializable;
import com.vaadin.server.Page;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the product editor form and the data source, including
 * fetching and saving products.
 *
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
public class ServicioLogic implements Serializable {

    private ManifiestosCrudView view;

    public ServicioLogic(ManifiestosCrudView simpleCrudView) {
        view = simpleCrudView;
    }

    public void init() {
        editProduct(null);
        // Hide and disable if not admin
        if (!MyUI.get().getAccessControl().isUserInRole("administrador")) {
            view.setNewProductEnabled(false);
        }
    }

    public void cancelProduct() {
        setFragmentParameter("");
        view.clearSelection();
    }

    /**
     * Update the fragment without causing navigator to change view
     */
    private void setFragmentParameter(String productId) {
        String fragmentParameter;
        if (productId == null || productId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = productId;
        }

        Page page = MyUI.get().getPage();
        page.setUriFragment(
                "!" + ManifiestosCrudView.VIEW_NAME + "/" + fragmentParameter,
                false);
    }

    public void enter(String productId) {
        if (productId != null && !productId.isEmpty()) {
            if (productId.equals("new")) {
                newProduct();
            } else {
                // Ensure this is selected even if coming directly here from
                // login
                try {
                    int pid = Integer.parseInt(productId);
                    Servicio product = findProduct(pid);
                    view.selectRow(product);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    private Servicio findProduct(int productId) {
        return ServicioDataService.get().getProductById(productId);
    }

    public void saveProduct(Servicio product) {
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") guardado");
        view.clearSelection();
        view.updateProduct(product);
        setFragmentParameter("");
    }

    public void deleteProduct(Servicio product) {
        view.showSaveNotification(product.getProductName() + " ("
                + product.getId() + ") borrado");
        view.clearSelection();
        view.removeProduct(product);
        setFragmentParameter("");
    }

    public void editProduct(Servicio product) {
        if (product == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(product.getId() + "");
        }
        view.editProduct(product);
    }

    public void newProduct() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editProduct(new Servicio());
    }

    public void rowSelected(Servicio product) {
        if (MyUI.get().getAccessControl().isUserInRole("administrador")) {
            view.editProduct(product);
        }
    }
}
