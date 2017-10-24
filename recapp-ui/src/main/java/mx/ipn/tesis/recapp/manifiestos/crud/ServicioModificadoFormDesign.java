package mx.ipn.tesis.recapp.manifiestos.crud;
import mx.ipn.tesis.recapp.samples.backend.data.Category;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;
import mx.ipn.tesis.recapp.samples.backend.data.Unidad;

/**
 *
 * @author ektho
 */
@SuppressWarnings("serial")
public class ServicioModificadoFormDesign extends CssLayout {
    protected TextField empresaGeneradora;
    protected TextField descripcion;
    protected TextField contenedor;
    protected ComboBox<Unidad> unidad;
    protected CheckBoxGroup<Category> chofer;
    protected Button save;
    protected Button discard;
    protected Button cancel;
    protected Button delete;

    public ServicioModificadoFormDesign() {
        Design.read(this);
    }
}
