import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String idReserva;
    private LocalDate fechaReserva;
    private LocalDate fechaViaje;
    private double costoTotal;
    private Cliente cliente;
    private List<PaqueteDeViaje> paquetes;

    public Reserva(String idReserva, Cliente cliente, LocalDate fechaViaje) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.fechaReserva = LocalDate.now();
        this.fechaViaje = fechaViaje;
        this.paquetes = new ArrayList<>();
        this.costoTotal = 0;
    }

    public void agregarPaquete(PaqueteDeViaje p) {
        if (p.descontarDisponibilidad()) {
            paquetes.add(p);
            calcularCosto();
        } else {
            System.out.println("No hay disponibilidad en el paquete " + p.getDestino());
        }
    }

    public void calcularCosto() {
        costoTotal = 0;
        for (PaqueteDeViaje p : paquetes) {
            costoTotal += p.getPrecioBase();
        }
    }

    public void procesarPago(int tipo) {
        if (tipo == 1) {
            MetodoDePago.pagarConTarjeta(costoTotal);
        } else if (tipo == 2) {
            MetodoDePago.pagarConTransferencia(costoTotal);
        }
    }

    public void mostrarReserva() {
        System.out.println("Reserva ID: " + idReserva);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Fecha de reserva: " + fechaReserva);
        System.out.println("Fecha de viaje: " + fechaViaje);
        System.out.println("Paquetes:");
        for (PaqueteDeViaje p : paquetes) {
            System.out.println(" - " + p.getDestino() + " $" + p.getPrecioBase());
        }
        System.out.println("Costo total: $" + costoTotal);
        System.out.println("---------------------------");
    }

    public double getCostoTotal() { return costoTotal; }
    public String getIdReserva() { return idReserva; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getFechaReserva() { return fechaReserva; }
    public LocalDate getFechaViaje() { return fechaViaje; }
    public List<PaqueteDeViaje> getPaquetes() { return paquetes; }

    public String toCSV() {
        StringBuilder mandarAlCsv = new StringBuilder();
        mandarAlCsv.append(idReserva).append(";")
          .append(cliente.getId()).append(";")
          .append(cliente.getNombre()).append(";")
          .append(fechaReserva).append(";")
          .append(fechaViaje).append(";")
          .append(costoTotal).append(";");

        for (PaqueteDeViaje p : paquetes) {
            mandarAlCsv.append(p.getDestino()).append(",");
        }
        return mandarAlCsv.toString();
    }
}

