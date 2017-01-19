package principal.entes;

import java.awt.Rectangle;
import principal.sprites.Sprite;

public class ObjetoColision {

    private final Sprite sprite;
    private final Rectangle rectangulo;

    public ObjetoColision(final Sprite sprite, final Rectangle rectangle) {
        this.sprite = sprite;
        this.rectangulo = rectangle;
    }
    
    public Sprite obtenerSprite() {
        return sprite;
    }

    public Rectangle obtenerRectangulo() {
        return rectangulo;
    }

}
