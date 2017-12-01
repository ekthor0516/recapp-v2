package mx.ipn.tesis.recapp.manifiestos.crud;

import mx.ipn.tesis.recapp.samples.ResetButtonForTextField;
import mx.ipn.tesis.recapp.samples.backend.DataService;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import mx.ipn.tesis.recapp.samples.crud.ServicioDataProvider;
import mx.ipn.tesis.recapp.samples.crud.ServicioGrid;

/**
 * A view for performing create-read-update-delete operations on products.
 *
 * See also {@link SampleCrudLogic} for fetching the data, the actual CRUD
 * operations and controlling the view based on events from outside.
 */
public class ManifiestosCrudView extends CssLayout implements View {

    public static final String VIEW_NAME = "Industrial";
    private ServicioGrid grid;
    private ManifiestoForm form;
    private TextField filter;

    private ManifiestosCrudLogic viewLogic = new ManifiestosCrudLogic(this);
    private Button newProduct;

    private ServicioDataProvider dataProvider = new ServicioDataProvider();

    public ManifiestosCrudView() {
        setSizeFull();
        addStyleName("crud-view");
        HorizontalLayout topLayout = createTopBar();

        grid = new ServicioGrid();
        grid.setDataProvider(dataProvider);
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));

        form = new ManifiestoForm(viewLogic);
        form.setCategories(DataService.get().getAllCategories());

        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.addComponent(topLayout);
        barAndGridLayout.addComponent(grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.setExpandRatio(grid, 1);
        barAndGridLayout.setStyleName("crud-main-layout");

        addComponent(barAndGridLayout);
        addComponent(form);

        viewLogic.init();
    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setStyleName("filter-textfield");
        filter.setPlaceholder("Inserte manifiesto");
        ResetButtonForTextField.extend(filter);
        // Apply the filter to grid's data provider. TextField value is never null
        filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

        newProduct = new Button("Nuevo servicio");
        newProduct.addStyleName(ValoTheme.BUTTON_PRIMARY);
        newProduct.setIcon(VaadinIcons.PLUS_CIRCLE);
        newProduct.addClickListener(click -> viewLogic.newProduct());

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidth("100%");
        topLayout.addComponent(filter);
        topLayout.addComponent(newProduct);
        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");
        return topLayout;
    }

    @Override
    public void enter(ViewChangeEvent event) {
        viewLogic.enter(event.getParameters());
    }

    public void showError(String msg) {
        Notification.show(msg, Type.ERROR_MESSAGE);
    }

    public void showSaveNotification(String msg) {
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }

    public void setNewProductEnabled(boolean enabled) {
        newProduct.setEnabled(enabled);
    }

    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    public void selectRow(Servicio row) {
        grid.getSelectionModel().select(row);
    }

    public Servicio getSelectedRow() {
        return grid.getSelectedRow();
    }

    public void updateProduct(Servicio product) {
        dataProvider.save(product);
        // FIXME: Grid used to scroll to the updated item
    }

    public void removeProduct(Servicio product) {
        dataProvider.delete(product);
    }

    public void editProduct(Servicio product) {
        if (product != null) {
            form.addStyleName("visible");
            form.setEnabled(true);
        } else {
            form.removeStyleName("visible");
            form.setEnabled(false);
        }
        form.editProduct(product);
        System.out.println("ya lo llamó");
    }
}
