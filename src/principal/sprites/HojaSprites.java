package principal.sprites;

import java.awt.image.BufferedImage;
import principal.herramientas.cargadorRecursos;

public class HojaSprites {

    private final int anchoPixeles;
    private final int altoPixeles;
    private final int NSpritesAncho;
    private final int NSpritesAlto;
    private final int anchoSprite;
    private final int altoSprite;
    private BufferedImage imagen;
    private Sprite[] sprites;
    private final int estado;

    public HojaSprites(final String ruta, final int tamañoSprite, final boolean hojaOpaca) {
        final BufferedImage imagen;
        if (hojaOpaca) {
            imagen = cargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = cargadorRecursos.cargarImagenCompatibleTransparente(ruta);
        }
        anchoPixeles = imagen.getWidth();
        altoPixeles = imagen.getHeight();
        NSpritesAncho = anchoPixeles / tamañoSprite;
        NSpritesAlto = altoPixeles / tamañoSprite;
        anchoSprite = tamañoSprite;
        altoSprite = tamañoSprite;
        sprites = new Sprite[NSpritesAlto * NSpritesAncho];
        extraerSpriteImagen(imagen);
        this.estado = Sprite.PISO;
    }

    public HojaSprites(final String ruta, final int tamañoSprite, final boolean hojaOpaca, final int estado) {
        final BufferedImage imagen;
        if (hojaOpaca) {
            imagen = cargadorRecursos.cargarImagenCompatibleOpaca(ruta);
        } else {
            imagen = cargadorRecursos.cargarImagenCompatibleTransparente(ruta);
        }
        anchoPixeles = imagen.getWidth();
        altoPixeles = imagen.getHeight();
        NSpritesAncho = anchoPixeles / tamañoSprite;
        NSpritesAlto = altoPixeles / tamañoSprite;
        anchoSprite = tamañoSprite;
        altoSprite = tamañoSprite;
        sprites = new Sprite[NSpritesAlto * NSpritesAncho];
        extraerSpriteImagen(imagen);
        this.estado = estado;
    }

    private void extraerSpriteImagen(final BufferedImage imagen) {
        for (int y = 0; y < NSpritesAlto; y++) {
            for (int x = 0; x < NSpritesAncho; x++) {
                final int posicionX = x * anchoSprite;
                final int posicionY = y * altoSprite;
                sprites[x + y * NSpritesAncho] = new Sprite(imagen.getSubimage(posicionX, posicionY, anchoSprite, altoSprite));
            }
        }
    }

    public Sprite obtenerSprite(final int indice) {
        return sprites[indice];
    }

    public Sprite obtenerSprite(final int x, final int y) {
        return sprites[x + y * NSpritesAncho];
    }

    public int obtenerAncho() {
        return anchoSprite;
    }

    public int obtenAlto() {
        return altoSprite;
    }
}
