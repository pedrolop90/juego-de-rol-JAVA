package principal.entes;

import java.awt.Rectangle;
import principal.Constantes;

public class PersonaColision {

    public static final int ARRIBA = 1;
    public static final int DERECHA = 2;
    public static final int ABAJO = 0;
    public static final int IZQUIERDA = 3;

    private final int orientacion;
    private final Rectangle rectangulo;

    public PersonaColision(final int origen, final int x, final int y, final int ancho, final int alto) {
        this.rectangulo = new Rectangle(x, y, ancho, alto);
        this.orientacion = origen;
    }

    public int calcularOrigen(final int actual, final int velocidad) {
        return actual + velocidad * (int) Constantes.VELOCIDAD_MOVIMIENTO + orientacion * (3 * (int) Constantes.VELOCIDAD_MOVIMIENTO);
    }

    public Rectangle obtenerOrientacion() {
        return rectangulo;
    }

}
