package Recursos;

public class Persona  implements Runnable{
    private String nombre;
    private Llamada llamada;

    public Persona(String nombre, Llamada llamada) {
        this.nombre = nombre;
        this.llamada = llamada;
    }

    @Override
    public void run() {
        System.out.println(nombre + " Esta atendiendo la llamada "+llamada.getId());
        llamada.procesar();
        System.out.println(nombre + " ha terminado la llamada "+llamada.getId());
    }
}
