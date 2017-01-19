package principal.entes;

import java.awt.Rectangle;
import principal.Constantes;
import principal.mapas.Mapa;
import principal.sprites.Sprite;

class Colision {

    protected double posicionX;
    protected double posicionY;

    protected final int ANCHO_JUGADOR = 16;
    protected final int ALTO_JUGADOR = 16;

    protected int direccion;
    protected final int tamañoLimites = 4;
    protected final PersonaColision[] limites;

    protected final int compensacionX = 16;
    protected final int compensacionY = 16;

    protected final Mapa mapa;

    public Colision(final Mapa mapa) {
        limites = new PersonaColision[tamañoLimites];
        limites[PersonaColision.ARRIBA] = new PersonaColision(1, Constantes.CENTRO_PANTALLA_X - compensacionY / 2, Constantes.CENTRO_PANTALLA_Y, compensacionY, 1);
        limites[PersonaColision.DERECHA] = new PersonaColision(-1, Constantes.CENTRO_PANTALLA_X + compensacionX / 2, Constantes.CENTRO_PANTALLA_Y, 1, compensacionX);
        limites[PersonaColision.ABAJO] = new PersonaColision(-1, Constantes.CENTRO_PANTALLA_X - compensacionY / 2, Constantes.CENTRO_PANTALLA_Y + compensacionY, compensacionY, 1);
        limites[PersonaColision.IZQUIERDA] = new PersonaColision(1, Constantes.CENTRO_PANTALLA_X - compensacionY / 2, Constantes.CENTRO_PANTALLA_Y, 1, compensacionX);
        this.mapa = mapa;
    }

    protected boolean enColision(final int velocidad) {
        for (int r = 0; r < mapa.areasColision.size(); r++) {
            Sprite temp = mapa.areasColision.get(r).obtenerSprite();
            Rectangle area = mapa.areasColision.get(r).obtenerRectangulo();
            int origenX = area.x;
            int origenY = area.y;
            if (direccion == PersonaColision.IZQUIERDA || direccion == PersonaColision.DERECHA) {
                origenX = limites[direccion].calcularOrigen(area.x, velocidad);
            } else {
                origenY = limites[direccion].calcularOrigen(area.y, velocidad);
            }
            if (limites[direccion].obtenerOrientacion().intersects(new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE))) {
              
                return true;
            }
        }
        return false;
    }

    protected boolean estaEnMapa(final int velocidadX, final int velocidadY) {
        int posicionFuturaX = (int) posicionX + velocidadX * (int) Constantes.VELOCIDAD_MOVIMIENTO;
        int posicionFuturaY = (int) posicionY + velocidadY * (int) Constantes.VELOCIDAD_MOVIMIENTO;
        final Rectangle bordesMapa = mapa.obtenerBorder(posicionFuturaX, posicionFuturaY, ANCHO_JUGADOR, ALTO_JUGADOR);
        for (int i = 0; i < limites.length; i++) {
            if (limites[i].obtenerOrientacion().intersects(bordesMapa)) {
                return true;
            }
        }
        return false;
    }

    public double obtenerPosicionX() {
        return posicionX;
    }

    public double obtenerPosicionY() {
        return posicionY;
    }

}
