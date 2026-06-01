
package modelo;

/**
 *
 * @author alfar
 */
public class E2MetodoQuicksortEmpleado {
        public static void quickSort(E2ModeloQuicksortEmpleado[] array, int bajo, int alto){
        if(bajo < alto){
            int pi = particion (array, bajo, alto);
            quickSort(array, bajo, pi - 1);
            quickSort(array, pi + 1, alto);
        }
    }
    
    private static int particion(E2ModeloQuicksortEmpleado [] array, int bajo, int alto){
        double pivote = array[alto].getSalario();
        int i = (bajo - 1);
        for (int j = bajo; j < alto; j++) {
            if(array[j].getSalario() > pivote){
                i++;
                E2ModeloQuicksortEmpleado temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        E2ModeloQuicksortEmpleado temp = array[i+1];
        array[i+1] = array[alto];
        array[alto] = temp;
        return i+1;
    }
}
