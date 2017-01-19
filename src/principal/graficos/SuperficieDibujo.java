package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.maquinaEstado.GestorEstados;

public class SuperficieDibujo extends Canvas {

    private final int ANCHO;
    private final int ALTO;
    private Raton raton;

    public SuperficieDibujo(final int ancho, final int alto) {
        ANCHO = ancho;
        ALTO = alto;
        raton = new Raton();
        setIgnoreRepaint(true);
        setPreferredSize(new Dimension(ancho, alto));
        setCursor(raton.obtenerCursor());
        addKeyListener(GestorControles.teclado);
        setFocusable(true);
        requestFocus();
    }

    public void dibujar(final GestorEstados ge) {
        BufferStrategy buffer = getBufferStrategy();
        if (buffer == null) {
            createBufferStrategy(2);
            return;
        }
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        if (Constantes.PROPORCION_X != 1.0 || Constantes.PROPORCION_Y != 1.0) {
            g.scale(Constantes.PROPORCION_X, Constantes.PROPORCION_Y);
        }
        g.setColor(Color.black);
        g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        ge.dibujar(g);
        g.setColor(Color.red);
        g.drawString("FPS: " + GestorPrincipal.fps, 10, 20);
        g.drawString("APS: " + GestorPrincipal.aps, 10, 40);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        buffer.show();
    }

    public int obtenerAncho() {
        return ANCHO;
    }

    public int obtenerAltar() {
        return ALTO;
    }
}
