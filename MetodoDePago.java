public class MetodoDePago {

    public static void pagarConTarjeta(double monto) {
        System.out.println("Pago procesado con Tarjeta de Crédito por $" + monto);
    }

    public static void pagarConTransferencia(double monto) {
        System.out.println("Pago procesado con Transferencia Bancaria por $" + monto);
    }
}