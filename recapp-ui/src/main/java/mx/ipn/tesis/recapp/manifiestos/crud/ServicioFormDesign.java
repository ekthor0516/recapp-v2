package mx.ipn.tesis.recapp.manifiestos.crud;

import mx.ipn.tesis.recapp.samples.backend.data.Availability;
import mx.ipn.tesis.recapp.samples.backend.data.Category;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;


@SuppressWarnings("serial")
public class ServicioFormDesign extends CssLayout {
    protected TextField empresaGeneradora;
    protected TextField precio;
    protected TextField contenedor;
    protected TextField descripcion;
    protected CheckBoxGroup<Category> unidad;
    protected ComboBox<Availability> estatus;
    protected CheckBoxGroup<Category> categoria;
    
    protected TextField cantidad1;
    protected TextField descripcion1;
    
    protected Button save;
    protected Button discard;
    protected Button cancel;
    protected Button delete;

        public ServicioFormDesign() {
            Design.read(this);
        }
}