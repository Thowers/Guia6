package guia6;
import java.util.Random;

public class Multi_Matrices_2 {
    
    public static void main(String[] args) {
        int size = 1000; // Tamaño de las matrices cuadradas
        int[][] A = generarMatriz(size); // Genera la matriz A con valores aleatorios
        int[][] B = generarMatriz(size); // Genera la matriz B con valores aleatorios
        int[][] C = new int[size][size]; // Matriz resultado
        
        long startTime = System.currentTimeMillis(); // Marca el tiempo inicial
        
        // Multiplicación secuencial
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    C[i][j] += A[i][k] * B[k][j]; // Realiza la multiplicación y acumulación
                }
            }
        }
        
        long endTime = System.currentTimeMillis(); // Marca el tiempo final
        System.out.println("Tiempo de ejecucion secuencial: " + (endTime - startTime) + " ms"); // Imprime el tiempo de ejecución
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
