package Recursos;

public class Llamada {
    private int id;
    private int duracion;

    public Llamada(int id, int duracion) {
        this.id = id;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void procesar() {
        System.out.println("Llamada #: "+id+" durante "+duracion+" minutos." );
        try {
            Thread.sleep(duracion * 1000);
        } catch (InterruptedException e) {
            System.out.println("Llamada # "+id+" interrumpida.");
        }
        System.out.println("Llamada # "+id+" finalizada.");
    }
}
