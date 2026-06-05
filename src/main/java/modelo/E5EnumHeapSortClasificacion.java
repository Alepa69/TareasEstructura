
package modelo;

/**
 *
 * @author PC
 */
public enum E5EnumHeapSortClasificacion {
    DE_CONSUMO,
    DE_LIMPIEZA,
    ELECTRODOMESTICOS,
    OTROS;

    @Override
    public String toString() {
        switch (this) {
            case DE_CONSUMO:
                return "De Consumo";
            case DE_LIMPIEZA:
                return "De Limpieza";
            case ELECTRODOMESTICOS:
                return "Electrodomésticos";
            case OTROS:
                return "Otros";
            default:
                return super.toString();
        }
    }
}
