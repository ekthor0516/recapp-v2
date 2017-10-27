package mx.ipn.tesis.recapp.samples.backend.data;

public enum Availability {
    COMING("Residuo Industrial"), AVAILABLE("Residuo Médico");

    private final String name;

    private Availability(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
