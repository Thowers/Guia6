package guia6;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Productor_Consumidor {

    public static void main(String[] args) {
        
        Queue<Integer> cola = new LinkedList<>(); // Cola compartida entre el productor y el consumidor
        Object lock = new Object(); // Objeto de bloqueo para sincronizar el acceso a la cola
        
        // Creación de hilos de productor y consumidor
        Thread productor = new Thread(new Productor(cola, lock));
        Thread consumidor = new Thread(new Consumidor(cola, lock));
        
        productor.start(); // Inicia el hilo productor
        consumidor.start(); // Inicia el hilo consumidor
        
    }
    
}

// Clase que representa al productor
class Productor implements Runnable {
    private final Queue<Integer> cola;  // Cola para almacenar números generados
    private final Object lock; // Objeto de bloqueo para sincronizar el acceso a la cola
    private final Random random = new Random(); // Generador de números aleatorios

    public Productor(Queue<Integer> cola, Object lock) {
        this.cola = cola;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) { // Bloquea la cola para acceso exclusivo
                int numero = random.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
                cola.add(numero); // Agrega el número a la cola
                System.out.println("Productor genero: " + numero); // Imprime el número generado
                lock.notify(); // Notifica al consumidor de que hay un nuevo número en la cola
            }
            try {
                Thread.sleep(1000); // Espera 1 segundo antes de generar el próximo número
            } catch (InterruptedException e) {
                e.printStackTrace(); // Maneja posibles interrupciones
            }
        }
    }
}

// Clase que representa al consumidor
class Consumidor implements Runnable {
    private final Queue<Integer> cola; // Cola de donde el consumidor obtiene los números
    private final Object lock; // Objeto de bloqueo para sincronizar el acceso a la cola

    public Consumidor(Queue<Integer> cola, Object lock) {
        this.cola = cola;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) { // Bloquea la cola para acceso exclusivo
                while (cola.isEmpty()) { // Si la cola está vacía, espera
                    try {
                        lock.wait(); // Espera hasta que haya un número en la cola
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // Maneja posibles interrupciones
                    }
                }
                int numero = cola.poll(); // Obtiene y elimina el primer número en la cola
                System.out.println("Consumidor proceso: " + numero + " * 2 = " + (numero * 2)); // Imprime el resultado
            }
            try {
                Thread.sleep(1000); // Espera 1 segundo antes de consumir otro número
            } catch (InterruptedException e) {
                e.printStackTrace(); // Maneja posibles interrupciones
            }
        }
    }
}