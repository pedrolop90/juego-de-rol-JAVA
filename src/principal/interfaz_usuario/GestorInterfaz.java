package principal.interfaz_usuario;

import java.awt.Graphics;
import principal.entes.jugador.Jugador;

public class GestorInterfaz {
    
    public static final GestorInterfaz gi = new GestorInterfaz();

    public void dibujar(final Graphics g, Jugador jugador) {
        jugador.barras.get(0).dibujar(g, jugador.obtenerVida(), (0 * 2 + 1));
        jugador.barras.get(1).dibujar(g, jugador.obtenerArmadura(), (1 * 2 + 1));
        jugador.barras.get(2).dibujar(g, jugador.obtenerResistencia(), (2 * 2 + 1));
    }

}
