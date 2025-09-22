import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private String telefono;
    private List<Reserva> reservas;

    public Cliente(String id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }

    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void mostrarReservas() {
        System.out.println("Reservas del cliente " + nombre + ":");
        for (Reserva r : reservas) {
            r.mostrarReserva();
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
}
