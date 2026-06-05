
package modelo;

/**
 *
 * @author PC
 */
public class E5MetodoHeapSortProducto {
    public static <T extends Comparable<T>> void ordenar(T[] vector, boolean ascendente) {
        int n = vector.length;

        // 1. Construir el montículo inicial
        for (int i = n / 2 - 1; i >= 0; i--) {
            aplicarHeap(vector, n, i, ascendente);
        }

        // 2. Extraer elementos uno a uno del montículo
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final del arreglo
            T temp = vector[0];
            vector[0] = vector[i];
            vector[i] = temp;

            // Reconstruir el montículo reducido
            aplicarHeap(vector, i, 0, ascendente);
        }
    }

    private static <T extends Comparable<T>> void aplicarHeap(T[] vector, int n, int i, boolean ascendente) {
        int principal = i; // Raíz (Padre)
        int izquierdo = 2 * i + 1; 
        int derecho = 2 * i + 2;   

        if (ascendente) {
            // Para orden Ascendente usamos un Max-Heap
            if (izquierdo < n && vector[izquierdo].compareTo(vector[principal]) > 0) {
                principal = izquierdo;
            }
            if (derecho < n && vector[derecho].compareTo(vector[principal]) > 0) {
                principal = derecho;
            }
        } else {
            // Para orden Descendente usamos un Min-Heap
            if (izquierdo < n && vector[izquierdo].compareTo(vector[principal]) < 0) {
                principal = izquierdo;
            }
            if (derecho < n && vector[derecho].compareTo(vector[principal]) < 0) {
                principal = derecho;
            }
        }

        // Si el nodo principal ya no es la raíz asignada originalmente
        if (principal != i) {
            T swap = vector[i];
            vector[i] = vector[principal];
            vector[principal] = swap;

            // Recursivamente aplicar heap en el subárbol afectado
            aplicarHeap(vector, n, principal, ascendente);
        }
    }
}
