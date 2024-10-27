package Modulos;

public class Cajero implements Runnable{
    private Cliente cliente;

    public Cajero(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println("Cajero atendiendo a "+cliente.getNombre());

        double total = 0;
        StringBuilder recibo = new StringBuilder();
        //foreach para procesar cada producto del carrito
        for (Producto producto : cliente.getCarrito()) {
            System.out.println("Procesando producto: "+producto.getNombre()+" a un precio de $"+producto.getPrecio());
            total += producto.getPrecio();
            recibo.append("-").append(producto.getNombre()).append(": $").append(producto.getPrecio()).append("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (System.out) {
            System.out.println("\nResumen de la compra de "+cliente.getNombre()+":");
            System.out.println(recibo.toString());
            System.out.println("Total de la compra de "+cliente.getNombre()+" $"+total);
            System.out.println("El cajero ha terminado de atender a "+cliente.getNombre()+"\n");
        }
    }
}

