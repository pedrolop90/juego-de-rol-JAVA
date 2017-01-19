package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {

    public static final int INDESTRUCTIBLE = 0;
    public static final int DESTRUCTIBLE = 1;
    public static final int PISO = 2;
    public static final int AGARRABLE = 3;
    public static final int TRANSPORTE = 4;

    private final BufferedImage imagen;
    private final int ANCHO;
    private final int ALTO;
    private final int estado;

    public Sprite(final BufferedImage imagen) {
        this.imagen = imagen;
        ANCHO = imagen.getWidth();
        ALTO = imagen.getHeight();
        this.estado = PISO;
    }

    public Sprite(final BufferedImage imagen, final int estado) {
        this.imagen = imagen;
        ANCHO = imagen.getWidth();
        ALTO = imagen.getHeight();
        this.estado = estado;

    }

    public int obtenerEstado() {
        return estado;
    }

    public BufferedImage obtenerImagen() {
        return imagen;
    }

    public int obtenerAncho() {
        return ANCHO;
    }

    public int obtenerAlto() {
        return ALTO;
    }

}
