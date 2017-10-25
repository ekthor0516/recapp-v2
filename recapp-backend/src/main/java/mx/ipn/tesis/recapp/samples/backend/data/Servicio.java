package mx.ipn.tesis.recapp.samples.backend.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Servicio implements Serializable { 

    @NotNull
    private int id = -1;
    @NotNull
    @Size(min = 2, message = "La empresa generadora debe tener mas de dos caracteres")
    private String empresa = "";
    @Min(0)
    private BigDecimal precio = BigDecimal.ZERO;
    private Set<Category> tipo;
    @Min(value = 0, message = "No se puede tener un número de contenedores negativo")
    private int contenedor = 0;
    @NotNull
    private Availability estatus = Availability.COMING;
    
    @Min(0)
    private BigDecimal cantidad1 = BigDecimal.ZERO;
    @NotNull
    @Size(min = 5, message = "La descripcion debe tener al menos 5 caracteres")
    private String descripcion1 = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return empresa;
    }

    public void setProductName(String productName) {
        this.empresa = productName;
    }

    public BigDecimal getPrice() {
        return precio;
    }

    public void setPrice(BigDecimal price) {
        this.precio = price;
    }

    public Set<Category> getCategory() {
        return tipo;
    }

    public void setCategory(Set<Category> category) {
        this.tipo = category;
    }

    public int getStockCount() {
        return contenedor;
    }

    public void setStockCount(int stockCount) {
        this.contenedor = stockCount;
    }

    public Availability getAvailability() {
        return estatus;
    }

    public void setAvailability(Availability availability) {
        this.estatus = availability;
    }

}
