public class PaqueteDeViaje {
    private String idPaquete;
    private String destino;
    private double precioBase;
    private int cantidadDisponible;

    public PaqueteDeViaje(String idPaquete, String destino, double precioBase, int cantidadDisponible) {
        this.idPaquete = idPaquete;
        this.destino = destino;
        this.precioBase = precioBase;
        this.cantidadDisponible = cantidadDisponible;
    }

    public boolean descontarDisponibilidad() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--;
            return true;
        }
        return false;
    }

    public void mostrarPaquete() {
        System.out.println(idPaquete + " | " + destino + " | $" + precioBase + " | disponibles: " + cantidadDisponible);
    }

    public double getPrecioBase() { return precioBase; }
    public String getDestino() { return destino; }
    public String getIdPaquete() { return idPaquete; }
    public int getCantidadDisponible() { return cantidadDisponible; }

    public String toCSV() {
        return idPaquete + ";" + destino + ";" + precioBase + ";" + cantidadDisponible;
    }
}
