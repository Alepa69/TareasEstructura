
package modelo;

/**
 *
 * @author alfar
 */
public class E1MetodoQuicksortPersona {
    public static void sort(E1ModeloQuicksortPersona [] array, int bajo, int alto, String criterio ){
        if(bajo < alto){
            int pi = particion (array, bajo, alto, criterio);
            sort(array, bajo, pi -1, criterio);
            sort(array, pi + 1, alto, criterio);
        }
    }
    
    private static int particion (E1ModeloQuicksortPersona[] array, int bajo, int alto, String criterio){
         E1ModeloQuicksortPersona pivote = array[alto];
        int i = (bajo - 1);
        
        for(int j = bajo; j < alto; j++){
            boolean condicion = false;
            
            if(criterio.equals("nombre")){
                //Ascendente nombre
                condicion = array[j].getNombre().compareTo(pivote.getNombre()) <= 0;
            }else if(criterio.equals("altura")){
                //Descendente altura
                condicion = array[j].getAltura() >= pivote.getAltura();
            }
            
            if(condicion){
                i++;
                 E1ModeloQuicksortPersona temp = array[i];
                array[i] = array [j];
                array [j] = temp;
            }
        }
        
         E1ModeloQuicksortPersona temp = array[i + 1];
        array[i + 1] = array[alto];
        array[alto] = temp;
        
        return i + 1;
    }
}
