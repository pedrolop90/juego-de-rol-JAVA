package principal.control;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.herramientas.cargadorRecursos;

public class Raton {

    private final Cursor cursor;

    public Raton() {
        Toolkit configuracion = Toolkit.getDefaultToolkit();

        BufferedImage icono = cargadorRecursos.cargarImagenCompatibleTransparente(Constantes.RUTA_CURSOR);

        Point punta = new Point(0, 0);
        this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor por Defecto");
    }

    public Cursor obtenerCursor() {
        return cursor;
    }

}
