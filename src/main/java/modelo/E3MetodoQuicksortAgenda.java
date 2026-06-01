
package modelo;

/**
 *
 * @author alfar
 */
public class E3MetodoQuicksortAgenda {
    // Método principal que llamarás desde el Controlador
    public static void quickSort(E3ModeloQuicksortAgenda[] array, int bajo, int alto, boolean ascendente) {
        if (bajo < alto) {
            int pi = particion(array, bajo, alto, ascendente);
            
            quickSort(array, bajo, pi - 1, ascendente);
            quickSort(array, pi + 1, alto, ascendente);
        }
    }

    private static int particion(E3ModeloQuicksortAgenda[] array, int bajo, int alto, boolean ascendente) {
        // Usamos el nombre del último contacto como pivote
        String pivote = array[alto].getNombre();
        int i = (bajo - 1);

        for (int j = bajo; j < alto; j++) {
            // CompareToIgnoreCase devuelve:
            // < 0 si es menor, > 0 si es mayor, 0 si son iguales
            int comparacion = array[j].getNombre().compareToIgnoreCase(pivote);

            boolean condicion;
            if (ascendente) {
                // Para Ascendente (A-Z): el actual debe ser "menor" que el pivote
                condicion = (comparacion < 0);
            } else {
                // Para Descendente (Z-A): el actual debe ser "mayor" que el pivote
                condicion = (comparacion > 0);
            }

            if (condicion) {
                i++;
                // Intercambio de objetos E3ModeloQuicksortAgenda
                E3ModeloQuicksortAgenda temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Intercambio final con el pivote
        E3ModeloQuicksortAgenda temp = array[i + 1];
        array[i + 1] = array[alto];
        array[alto] = temp;

        return i + 1;
    }
}
