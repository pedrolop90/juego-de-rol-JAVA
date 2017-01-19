package principal.entes.npc;

import principal.entes.Persona;
import principal.mapas.Mapa;
import principal.sprites.Sprite;

public class NPC extends Persona {

    public NPC(String ruta, double posicionX, double posicionY, Mapa mapa) {
        super(ruta, posicionX, posicionY, mapa, Sprite.INDESTRUCTIBLE);
    }

}
