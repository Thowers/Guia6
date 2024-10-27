package Principal;

import Recursos.Llamada;
import Recursos.Persona;

public class Principal {
    public static void main(String[] args) {

        //Duracion e id de las llamadas
        Llamada llamada1 = new Llamada(1, 5);
        Llamada llamada2 = new Llamada(2, 8);
        Llamada llamada3 = new Llamada(3, 4);

        //Personas que atienden las llamadas
        Thread persona1 = new Thread(new Persona("Jaider", llamada1));
        Thread persona2 = new Thread(new Persona("Juan", llamada2));
        Thread persona3 = new Thread(new Persona("Nicolas", llamada3));

        //Inicia las llamadas
        persona1.start();
        persona2.start();
        persona3.start();

        //Esperar a que los hilos terminen
        try {
            persona1.join();
            persona2.join();
            persona3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Todas las llamadas han sido atendidas.");
    }
}
