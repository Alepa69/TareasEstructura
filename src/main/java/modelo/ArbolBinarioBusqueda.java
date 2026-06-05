
package modelo;

import java.util.ArrayList;

public class ArbolBinarioBusqueda<T> extends ArbolBinario {
    public ArbolBinarioBusqueda() {

        super();

    }

    public <T extends Comparable> void insertar(T dato) {
        super.setRaiz(insertar(dato, super.getRaiz()));
    }

    public <T extends Comparable> Nodo insertar(T dato, Nodo r) {
        if (r == null) {
            r = new Nodo(dato);
        } else if (dato.compareTo(r.getDato()) < 0) {
            Nodo izd;
            izd = insertar(dato, r.getRamaIzq());
            r.setRamaIzq(izd);
            // insertar(dato,r.getRamaIzq());
        } else if (dato.compareTo(r.getDato()) > 0) {
            Nodo drch;
            drch = insertar(dato, r.getRamaDrch());
            r.setRamaDrch(drch);
            // insertar(dato,r.getRamaDrch());
        } else {
            System.out.println("Duplicado!");
        }
        return r;
    }

    public ArrayList NID() {

        ArrayList a = new ArrayList();
        return preOrdenNID(super.getRaiz(), a);

    }

    public ArrayList IND() {
        ArrayList a = new ArrayList();
        return inOrdenIND(super.getRaiz(), a);
    }

    public ArrayList IDN() {
        ArrayList a = new ArrayList();
        return postOrdenIDN(super.getRaiz(), a);
    }

    public <T extends Comparable> void quitar(T dato) {
        super.setRaiz(eliminar(dato, super.getRaiz()));
    }

    public <T extends Comparable> Nodo eliminar(T dato, Nodo r) {

        if (r == null) { // r : parte del arbol //si no hay hoja recorrio todo el arbol
            System.out.println("No existe para eliminar!");
        } else if (dato.compareTo(r.getDato()) < 0) {
            Nodo izq;
            izq = eliminar(dato, r.getRamaIzq());
            r.setRamaIzq(izq);
        } else if (dato.compareTo(r.getDato()) > 0) {
            Nodo drch;
            drch = eliminar(dato, r.getRamaDrch());
            r.setRamaDrch(drch);
            // eliminar(dato, r.getRamaDrch());
        } else {
            Nodo q;
            q = r;

            if (q.getRamaIzq() == null) {
                r = q.getRamaDrch();
            } else if (q.getRamaDrch() == null) {
                r = q.getRamaIzq();
            } else {

                q = aplicarReglaDosHijos(q);
            }

            q = null; // para eliminar
        }

        return r;
    }

    private Nodo aplicarReglaDosHijos(Nodo actual) {
        Nodo aux, ant; // nodo auxiliar: para trasladar de nodo en nodo ---- nodo ant: nodo anterior
        ant = actual;
        aux = actual.getRamaIzq(); // buscar mas a la derecha de la rama izquierda
        while (aux.getRamaDrch() != null) { // si hay algo en la rama derecha
            ant = aux; // salva
            aux = aux.getRamaDrch();
        }
        actual.setDato(aux.getDato()); // set para cambiar el valor
        if (ant == actual) {
            ant.setRamaIzq(aux.getRamaIzq());
        } else {
            ant.setRamaDrch(aux.getRamaIzq());
        }
        return aux;
    }

    // Método público para llamar desde el exterior
    public int contarNodosPadres() {
        return contarNodosPadres(super.getRaiz());
    }

    // Método privado recursivo
    private int contarNodosPadres(Nodo r) {
        // Si el nodo es nulo o es una hoja (no tiene hijos), no es un padre
        if (r == null || (r.getRamaIzq() == null && r.getRamaDrch() == null)) {
            return 0;
        }

        // Si llegamos aquí, el nodo actual TIENE al menos un hijo, por lo tanto es
        // padre.
        // Sumamos 1 y seguimos buscando en sus ramas.
        return 1 + contarNodosPadres(r.getRamaIzq()) + contarNodosPadres(r.getRamaDrch());
    }

    /**************************************
     * METODOS GUIA
     *******************************************************/
    // =========================================================================
    // 1. PESO DEL ÁRBOL (Número total de nodos que tiene el árbol)
    // =========================================================================
    public int calcularPeso() {
        return calcularPeso(super.getRaiz());
    }

    private int calcularPeso(Nodo r) {
        if (r == null) {
            return 0;
        }
        // El peso es 1 (el nodo actual) más el peso de sus dos subárboles
        return 1 + calcularPeso(r.getRamaIzq()) + calcularPeso(r.getRamaDrch());
    }

    // =========================================================================
    // 2. MOSTRAR NODOS CON DOS HIJOS
    // =========================================================================
    public void mostrarNodosDosHijos() {
        System.out.println("Nodos con dos hijos:");
        mostrarNodosDosHijos(super.getRaiz());
        System.out.println(); // Salto de línea al final
    }

    private void mostrarNodosDosHijos(Nodo r) {
        if (r != null) {
            // Si tiene ambos hijos, cumple la condición
            if (r.getRamaIzq() != null && r.getRamaDrch() != null) {
                System.out.print(r.getDato() + " ");
            }
            // Continuar recorriendo el árbol
            mostrarNodosDosHijos(r.getRamaIzq());
            mostrarNodosDosHijos(r.getRamaDrch());
        }
    }

    // =========================================================================
    // 3. MÁXIMO VALOR GUARDADO (En un ABB, está siempre lo más a la derecha)
    // =========================================================================
    public Object encontrarMaximo() {
        if (super.isEmpty()) {
            System.out.println("El árbol está vacío.");
            return null;
        }
        return encontrarMaximo(super.getRaiz());
    }

    private Object encontrarMaximo(Nodo r) {
        // Seguimos la rama derecha hasta que ya no haya más nodos
        if (r.getRamaDrch() == null) {
            return r.getDato();
        }
        return encontrarMaximo(r.getRamaDrch());
    }

    // =========================================================================
    // 4. MÍNIMO VALOR GUARDADO (En un ABB, está siempre lo más a la izquierda)
    // =========================================================================
    public Object encontrarMinimo() {
        if (super.isEmpty()) {
            System.out.println("El árbol está vacío.");
            return null;
        }
        return encontrarMinimo(super.getRaiz());
    }

    private Object encontrarMinimo(Nodo r) {
        // Seguimos la rama izquierda hasta encontrar el último elemento
        if (r.getRamaIzq() == null) {
            return r.getDato();
        }
        return encontrarMinimo(r.getRamaIzq());
    }

    // =========================================================================
    // 5. SUMATORIA DE VALORES EN LA RAMA DERECHA DE LA RAÍZ
    // =========================================================================
    public int sumarRamaDerecha() {
        if (super.isEmpty() || super.getRaiz().getRamaDrch() == null) {
            return 0;
        }
        // Empezamos a sumar a partir del hijo derecho de la raíz principal
        return sumarTodoElArbol(super.getRaiz().getRamaDrch());
    }

    // Método auxiliar para sumar todos los enteros de un subárbol
    private int sumarTodoElArbol(Nodo r) {
        if (r == null) {
            return 0;
        }
        // Intentamos castear el dato a entero para poder sumarlo
        int valorNodo = 0;
        if (r.getDato() instanceof Integer) {
            valorNodo = (Integer) r.getDato();
        }
        return valorNodo + sumarTodoElArbol(r.getRamaIzq()) + sumarTodoElArbol(r.getRamaDrch());
    }

    // =========================================================================
    // 6. CONTAR ELEMENTOS IMPARES
    // =========================================================================
    public int contarImpares() {
        return contarImpares(super.getRaiz());
    }

    private int contarImpares(Nodo r) {
        if (r == null) {
            return 0;
        }
        int actualEsImpar = 0;
        if (r.getDato() instanceof Integer) {
            int valor = (Integer) r.getDato();
            if (valor % 2 != 0) {
                actualEsImpar = 1; // Sumamos 1 si es impar
            }
        }
        return actualEsImpar + contarImpares(r.getRamaIzq()) + contarImpares(r.getRamaDrch());
    }

    // =========================================================================
    // 7. DEVUELVE LA ALTURA RECIBIENDO LA RAÍZ
    // =========================================================================
    // (Nota: Ya tenías uno en ArbolBinario, pero este cumple el requisito exacto de
    // la guía)
    public int obtenerAltura(Nodo r) {
        if (r == null) {
            return 0;
        }
        // La altura de un nodo es 1 más el máximo entre la altura izquierda y derecha
        return 1 + Math.max(obtenerAltura(r.getRamaIzq()), obtenerAltura(r.getRamaDrch()));
    }

    // =========================================================================
    // 8. RECORRIDO POR NIVELES (Usa una estructura de cola auxiliar/ArrayList)
    // =========================================================================
    public void recorridoPorNiveles() {
        if (super.isEmpty())
            return;

        ArrayList<Nodo> cola = new ArrayList<>();
        cola.add(super.getRaiz());

        System.out.println("Recorrido por niveles:");
        while (!cola.isEmpty()) {
            Nodo actual = cola.remove(0); // Desencolar el primero
            System.out.print(actual.getDato() + " ");

            // Encolar hijos
            if (actual.getRamaIzq() != null) {
                cola.add(actual.getRamaIzq());
            }
            if (actual.getRamaDrch() != null) {
                cola.add(actual.getRamaDrch());
            }
        }
        System.out.println();
    }

    // =========================================================================
    // 12. LONGITUD DEL ÁRBOL (Número de arcos en el camino más largo: Altura - 1)
    // =========================================================================
    public int encontrarLongitud() {
        if (super.isEmpty()) {
            return 0;
        }
        // La definición técnica de longitud o profundidad máxima suele ser Altura - 1
        return obtenerAltura(super.getRaiz()) - 1;
    }

    // =========================================================================
    // 13. MOSTRAR NODOS EN LA RAMA MÁS LARGA
    // =========================================================================
    public String obtenerRamaMasLargaStr() {
        if (super.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        obtenerRamaMasLargaStr(super.getRaiz(), sb);
        String res = sb.toString().trim();
        return res.replace(" ", " -> ");
    }

    private void obtenerRamaMasLargaStr(Nodo r, StringBuilder sb) {
        if (r == null)
            return;
        sb.append(r.getDato()).append(" ");
        int altIzq = obtenerAltura(r.getRamaIzq());
        int altDrch = obtenerAltura(r.getRamaDrch());
        if (altIzq >= altDrch) {
            obtenerRamaMasLargaStr(r.getRamaIzq(), sb);
        } else {
            obtenerRamaMasLargaStr(r.getRamaDrch(), sb);
        }
    }

    // =========================================================================
    // 9. COMPROBAR SI DOS ÁRBOLES SON ESPEJOS RECIBIENDO SUS RAÍCES
    // =========================================================================
    public boolean sonEspejos(Nodo r1, Nodo r2) {
        // Si ambos están vacíos, son espejos
        if (r1 == null && r2 == null) {
            return true;
        }
        // Si uno está vacío y el otro no, no son espejos
        if (r1 == null || r2 == null) {
            return false;
        }
        // Deben tener el mismo dato, y la rama izq de uno debe ser espejo de la der del
        // otro
        return r1.getDato().equals(r2.getDato())
                && sonEspejos(r1.getRamaIzq(), r2.getRamaDrch())
                && sonEspejos(r1.getRamaDrch(), r2.getRamaIzq());
    }

    // =========================================================================
    // 10. CONSTRUIR Y DEVOLVER UN ÁRBOL ESPEJO A PARTIR DEL ACTUAL
    // =========================================================================
    public ArbolBinarioBusqueda<T> construirArbolEspejo() {
        ArbolBinarioBusqueda<T> espejo = new ArbolBinarioBusqueda<>();
        espejo.setRaiz(clonarYInvertir(super.getRaiz()));
        return espejo;
    }

    private Nodo clonarYInvertir(Nodo r) {
        if (r == null) {
            return null;
        }
        // Creamos un nuevo nodo con el mismo dato pero invertimos sus ramas
        Nodo nuevo = new Nodo(r.getDato());
        nuevo.setRamaIzq(clonarYInvertir(r.getRamaDrch()));
        nuevo.setRamaDrch(clonarYInvertir(r.getRamaIzq()));
        return nuevo;
    }

    // =========================================================================
    // 11. COMPROBAR SI EL ÁRBOL ESTÁ COMPLETAMENTE LLENO
    // =========================================================================
    public boolean isArbolLleno() {
        if (super.isEmpty()) {
            return true;
        }
        int longitud = encontrarLongitud(); // Método 12 ya implementado
        int numHojas = contarHojas(super.getRaiz());

        // Según la guía: el número de hojas debe ser igual a 2^longitud
        return numHojas == Math.pow(2, longitud);
    }

    private int contarHojas(Nodo r) {
        if (r == null)
            return 0;
        if (r.getRamaIzq() == null && r.getRamaDrch() == null)
            return 1;
        return contarHojas(r.getRamaIzq()) + contarHojas(r.getRamaDrch());
    }

    // =========================================================================
    // EXTRA: Método auxiliar para retornar los Nodos de dos hijos como String
    // =========================================================================
    public String obtenerNodosDosHijosStr() {
        if (super.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        obtenerNodosDosHijosStr(super.getRaiz(), sb);
        String res = sb.toString().trim();
        return res.isEmpty() ? "Ninguno" : res.replace(" ", ", ");
    }

    private void obtenerNodosDosHijosStr(Nodo r, StringBuilder sb) {
        if (r == null)
            return;

        // Si el nodo tiene ambos hijos, lo agregamos al StringBuilder
        if (r.getRamaIzq() != null && r.getRamaDrch() != null) {
            sb.append(r.getDato()).append(" ");
        }

        // Seguimos recorriendo el árbol de forma recursiva
        obtenerNodosDosHijosStr(r.getRamaIzq(), sb);
        obtenerNodosDosHijosStr(r.getRamaDrch(), sb);
    }

    // =========================================================================
    // EXTRA: Método auxiliar para retornar el recorrido por niveles como String
    // =========================================================================
    public String obtenerNodosPorNivelStr() {
        if (super.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        ArrayList<Nodo> cola = new ArrayList<>();
        cola.add(super.getRaiz());

        while (!cola.isEmpty()) {
            Nodo actual = cola.remove(0);
            sb.append(actual.getDato()).append(", ");
            if (actual.getRamaIzq() != null)
                cola.add(actual.getRamaIzq());
            if (actual.getRamaDrch() != null)
                cola.add(actual.getRamaDrch());
        }
        if (sb.length() > 0)
            sb.setLength(sb.length() - 2); // Quitar última coma
        return sb.toString();
    }

    public void insertarInverso(T clave) {
        Nodo nuevo = new Nodo(clave);
        if (super.getRaiz() == null) {
            super.setRaiz(nuevo);
        } else {
            insertarInverso(super.getRaiz(), nuevo);
        }
    }

    private void insertarInverso(Nodo pad, Nodo nue) {
        // Lógica invertida: Si es menor o igual, va a la DERECHA
        if (((Comparable) nue.getDato()).compareTo(pad.getDato()) <= 0) {
            if (pad.getRamaDrch() == null) {
                pad.setRamaDrch(nue);
            } else {
                insertarInverso(pad.getRamaDrch(), nue);
            }
        } else { // Si es mayor, va a la IZQUIERDA
            if (pad.getRamaIzq() == null) {
                pad.setRamaIzq(nue);
            } else {
                insertarInverso(pad.getRamaIzq(), nue);
            }
        }
    }

}
