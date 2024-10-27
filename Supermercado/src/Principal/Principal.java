package Principal;

import Modulos.Cajero;
import Modulos.Cliente;
import Modulos.Producto;

import java.util.Arrays;

public class Principal {
    public static void main(String[] args) {

        //Productos para los clientes
        Producto leche = new Producto("Leche", 13000);
        Producto pan = new Producto("Pan", 4000);
        Producto huevos = new Producto("Huevos", 16000);
        Producto rosas = new Producto("Rosas", 15000);
        Producto cordones = new Producto("Cordones", 18500);
        Producto papel = new Producto("Papel", 17000);
        Producto cuatroDeCarne = new Producto("Cuatro de Carne", 6000);
        Producto unaDeVerduraQueso = new Producto("Una de verdura con queso", 1600);
        Producto unaDeChoclo = new Producto("Una de choclo", 1200);
        Producto unaDeCarne = new Producto("Una de carne", 1500);
        Producto otraDechoclo = new Producto("otra de choclo", 1200);

        //Creacion de clientes
        Cliente cliente1 = new Cliente("Jaider", Arrays.asList(leche, pan, huevos));
        Cliente cliente2 = new Cliente("Juan", Arrays.asList(rosas, cordones, papel));
        Cliente cliente3 = new Cliente("Nicolas", Arrays.asList(cuatroDeCarne, unaDeVerduraQueso, unaDeChoclo, unaDeCarne, otraDechoclo));

        //Crear hilos para la simulacion del cajero procesando las compras
        Thread cajero1 = new Thread(new Cajero(cliente1));
        Thread cajero2 = new Thread(new Cajero(cliente2));
        Thread cajero3 = new Thread(new Cajero(cliente3));

        //Iniciame lo ilo
        cajero1.start();
        cajero2.start();
        cajero3.start();

        //Espera a que los hilos terminen

        try {
            cajero1.join();
            cajero2.join();
            cajero3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Todas las compras han sido procesadas.");
    }
}
