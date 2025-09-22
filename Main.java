import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PaqueteDeViaje> paquetes = cargarPaquetes();
        List<Cliente> clientes = new ArrayList<>();

        System.out.println("=== Sistema de Reservas TravelNow ===");

        System.out.print("Ingrese ID del cliente: ");
        String idClient = sc.nextLine();
        System.out.print("Nombre: ");
        String name = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        Cliente cliente = new Cliente(idClient, name, correo, phone);
        clientes.add(cliente);
        
        System.out.print("Ingrese ID de la reserva: ");
        String idBooking = sc.nextLine();
        System.out.print("Ingrese días hasta el viaje: ");
        int dias = sc.nextInt();

        Reserva reserva = new Reserva(idBooking, cliente, LocalDate.now().plusDays(dias));

        System.out.println("Paquetes disponibles:");
        for (PaqueteDeViaje p : paquetes) {
            p.mostrarPaquete();
        }

        boolean agregarMas = true;
        sc.nextLine();
        while (agregarMas) {
            System.out.print("Ingrese ID del paquete a reservar: ");
            String idPackage = sc.nextLine();
            boolean encontrado = false;
            for (PaqueteDeViaje p : paquetes) {
                if (p.getIdPaquete().equals(idPackage)) {
                    reserva.agregarPaquete(p);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Paquete no encontrado. Intente nuevamente.");
            }

            System.out.print("¿Desea agregar otro paquete? (s/n): ");
            String respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                agregarMas = false;
            }
        }

        System.out.println("Método de pago: 1. Tarjeta Crédito  2. Transferencia");
        int opcion = sc.nextInt();
        reserva.procesarPago(opcion);

        cliente.registrarReserva(reserva);
        guardarReserva(reserva);

        cliente.mostrarReservas();
        sc.close();
    }

    private static List<PaqueteDeViaje> cargarPaquetes() {
        List<PaqueteDeViaje> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("paquetes.csv"))) {
            String linea = br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String id = datos[0];
                String destino = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);
                lista.add(new PaqueteDeViaje(id, destino, precio, cantidad));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo paquetes: " + e.getMessage());
        }
        return lista;
    }

    private static void guardarReserva(Reserva r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("reservas.csv", true))) {
            pw.println(r.toCSV());
        } catch (IOException e) {
            System.out.println("Error guardando reserva: " + e.getMessage());
        }
    }
}
