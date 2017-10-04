package mx.ipn.tesis.recapp.samples.backend.data;

public enum Unidad {
    KILOS("Kilogramos"), LITROS("Litros"), TON("Toneladas");

    private final String name;

    private Unidad(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
