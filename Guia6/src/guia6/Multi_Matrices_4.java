package guia6;
import java.util.Random;

public class Multi_Matrices_4 {
    
    public static void main(String[] args) {
        int size = 1000; // Tamaño de las matrices cuadradas
        int[][] A = generarMatriz(size); // Genera la matriz A
        int[][] B = generarMatriz(size); // Genera la matriz B
        int[][] C = new int[size][size]; // Matriz resultado

        long startTime = System.currentTimeMillis(); // Marca el tiempo inicial

        // Crear y ejecutar 4 hilos
        int numHilos = 4; // Número de hilos
        Thread[] hilos = new Thread[numHilos]; // Arreglo de hilos
        int filaDividida = size / numHilos; // Número de filas que procesará cada hilo

        // Asigna a cada hilo una submatriz para procesar
        for (int i = 0; i < numHilos; i++) {
            final int inicio = i * filaDividida; // Fila de inicio para el hilo actual
            final int fin = (i + 1) * filaDividida; // Fila de fin para el hilo actual
            hilos[i] = new Thread(() -> multiplicarSubmatriz(A, B, C, inicio, fin, size)); // Crea el hilo
            hilos[i].start(); // Inicia el hilo
        }

        // Espera a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Espera a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace(); // Maneja posibles interrupciones
            }
        }

        long endTime = System.currentTimeMillis(); // Marca el tiempo final
        System.out.println("Tiempo de ejecucion con hilos: " + (endTime - startTime) + " ms"); // Imprime el tiempo de ejecución
    }

    // Método para multiplicar una submatriz asignada a un hilo
    private static void multiplicarSubmatriz(int[][] A, int[][] B, int[][] C, int inicio, int fin, int size) {
        for (int i = inicio; i < fin; i++) { // Recorre las filas asignadas
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    C[i][j] += A[i][k] * B[k][j]; // Realiza la multiplicación y acumulación
                }
            }
        }
    }

    // Genera una matriz con valores aleatorios
    private static int[][] generarMatriz(int size) {
        int[][] matriz = new int[size][size]; // Inicializa la matriz
        Random random = new Random(); // Generador de números aleatorios
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matriz[i][j] = random.nextInt(10); // Llena la matriz con valores entre 0 y 9
            }
        }
        return matriz; // Retorna la matriz generada
    }
    
}
