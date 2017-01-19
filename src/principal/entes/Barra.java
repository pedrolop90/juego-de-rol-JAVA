package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import principal.Constantes;

public class Barra {

    public static final int ANCHO = 100;
    public static final int ALTO = 10;
    private final int MAXIMO;
    private final int posicionX = Constantes.ANCHO_JUEGO-ANCHO;
    private final Color color;

    public Barra(final Color color, final int maxima) {
        this.color = color;
        MAXIMO = maxima;
    }

    public void dibujar(final Graphics g, final int ancho, final int posicionY) {
        int comp1 = 2;
        g.setColor(Color.white);
        g.drawRect(posicionX, posicionY * Barra.ALTO, ANCHO, Barra.ALTO);
        g.setColor(color.darker());
        g.fillRect(posicionX + 1, posicionY * Barra.ALTO + 1, ancho * 100 / MAXIMO-1, Barra.ALTO-1);

    }

}
