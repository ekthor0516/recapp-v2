package mx.ipn.tesis.recapp.samples.backend.data;

public enum Availability {
    COMING("Pendiente"), AVAILABLE("En curso"), DISCONTINUED("Realizado");

    private final String name;

    private Availability(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
