package mx.ipn.tesis.recapp.samples.crud;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

import mx.ipn.tesis.recapp.samples.backend.data.Availability;
import mx.ipn.tesis.recapp.samples.backend.data.Category;
import mx.ipn.tesis.recapp.samples.backend.data.Servicio;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class ServicioGrid extends Grid<Servicio> {

    public ServicioGrid() {
        setSizeFull();

        addColumn(Servicio::getId, new NumberRenderer()).setCaption("");
        addColumn(Servicio::getProductName).setCaption("Manifiesto");

        // Format and add " €" to price
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        addColumn(product -> "$ " + decimalFormat.format(product.getPrice()) )
                        .setCaption("Precio del servicio").setComparator((p1, p2) -> {
                            return p1.getPrice().compareTo(p2.getPrice());
                        }).setStyleGenerator(product -> "align-right");

        // Add an traffic light icon in front of availability
        addColumn(this::htmlFormatAvailability, new HtmlRenderer())
                .setCaption("Tipo de residuos").setComparator((p1, p2) -> {
                    return p1.getAvailability().toString()
                            .compareTo(p2.getAvailability().toString());
                });

        // Show empty stock as "-"
        addColumn(product -> {
            if (product.getStockCount() == 0) {
                return "-";
            }
            return Integer.toString(product.getStockCount());
        }).setCaption("# Contenedores").setComparator((p1, p2) -> {
            return Integer.compare(p1.getStockCount(), p2.getStockCount());
        }).setStyleGenerator(product -> "align-right");

        // Show all categories the product is in, separated by commas
        addColumn(this::formatCategories).setCaption("Empresa Generadora").setSortable(false);
    }

    public Servicio getSelectedRow() {
        return asSingleSelect().getValue();
    }

    public void refresh(Servicio product) {
        getDataCommunicator().refresh(product);
    }

    private String htmlFormatAvailability(Servicio product) {
        Availability availability = product.getAvailability();
        String text = availability.toString();

        String color = "";
        switch (availability) {
        case AVAILABLE:
            color = "#2dd085";
            break;
        case COMING:
            color = "#ffc66e";
            break;
//        case DISCONTINUED:
//            color = "#f54993";
//            break;
        default:
            break;
        }

        String iconCode = "<span class=\"v-icon\" style=\"font-family: "
                + VaadinIcons.CIRCLE.getFontFamily() + ";color:" + color
                + "\">&#x"
                + Integer.toHexString(VaadinIcons.CIRCLE.getCodepoint())
                + ";</span>";

        return iconCode + " " + text;
    }

    private String formatCategories(Servicio product) {
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            return "";
        }
        return product.getCategory().stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(Category::getName).collect(Collectors.joining(", "));
    }
}
