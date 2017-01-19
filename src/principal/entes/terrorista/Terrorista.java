package principal.entes.terrorista;

import java.awt.Graphics;
import principal.entes.Persona;
import principal.mapas.Mapa;

public class Terrorista extends Persona {
    private int posicionActualX = 100;
    private int posicionActualY = 100;

    public Terrorista(String ruta, double posicionX, double posicionY, Mapa mapa) {
        super(100, 300, 10, ruta, posicionX, posicionY, mapa);
    }

    public void dibujar(Graphics g) {
        super.dibujar(g,posicionX, posicionY);
    }

    @Override
    protected void determinarDireccion() {
        
    }

}
