package principal.entes.jugador;

import java.awt.Graphics;
import principal.Constantes;
import principal.control.GestorControles;
import principal.entes.Persona;
import principal.mapas.Mapa;

public class Jugador extends Persona {

    public Jugador(final String ruta, final double posicionX, final double posicionY, final Mapa mapa) {
        super(100, 300, 10, ruta, posicionX, posicionY, mapa);
    }

    @Override
    public void actualizar() {
        resistencia();
        super.actualizar();
    }

    public void dibujar(Graphics g) {
        final double centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        final double centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        super.dibujar(g, centroX, centroY);
    }

    public void resistencia() {
        if (GestorControles.teclado.correr && resistencia > 0) {
            Constantes.VELOCIDAD_MOVIMIENTO = 2;
            recuperado = false;
            recuperacion = 0;
        } else {
            Constantes.VELOCIDAD_MOVIMIENTO = 1;
            if (!recuperado && recuperacion < MAXIMA_RECUPERACION) {
                recuperacion++;
            }
            if (recuperacion == MAXIMA_RECUPERACION && resistencia < MAXIMA_RESISTENCIA) {
                resistencia++;
            }
        }
    }

    @Override
    protected void determinarDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();
        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }
        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            mover(velocidadX, velocidadY, GestorControles.teclado.correr);
        } else {
            if ((velocidadX == -1 && velocidadY == -1)) {
                determinarUltimaTecla(GestorControles.teclado.izquierda.obtenerUltimaPulsacion(), GestorControles.teclado.arriba.obtenerUltimaPulsacion(), velocidadX, velocidadY);
            }
            if ((velocidadX == -1 && velocidadY == 1)) {
                determinarUltimaTecla(GestorControles.teclado.izquierda.obtenerUltimaPulsacion(), GestorControles.teclado.abajo.obtenerUltimaPulsacion(), velocidadX, velocidadY);
            }
            if ((velocidadX == 1 && velocidadY == -1)) {
                determinarUltimaTecla(GestorControles.teclado.derecha.obtenerUltimaPulsacion(), GestorControles.teclado.arriba.obtenerUltimaPulsacion(), velocidadX, velocidadY);
            }
            if ((velocidadX == 1 && velocidadY == 1)) {
                determinarUltimaTecla(GestorControles.teclado.derecha.obtenerUltimaPulsacion(), GestorControles.teclado.abajo.obtenerUltimaPulsacion(), velocidadX, velocidadY);
            }
        }
    }

    protected void determinarUltimaTecla(final long primera, final long segunda, final int velocidadX, final int velocidadY) {
        if (primera > segunda) {
            mover(velocidadX, 0, GestorControles.teclado.correr);
        } else {
            mover(0, velocidadY, GestorControles.teclado.correr);
        }
    }

    protected int evaluarVelocidadX() {
        int velocidadX = 0;
        if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
            velocidadX = -1;
        } else if (!GestorControles.teclado.izquierda.estaPulsada() && GestorControles.teclado.derecha.estaPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    protected int evaluarVelocidadY() {
        int velocidadY = 0;
        if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
            velocidadY = -1;
        } else if (!GestorControles.teclado.arriba.estaPulsada() && GestorControles.teclado.abajo.estaPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;
    }

}
