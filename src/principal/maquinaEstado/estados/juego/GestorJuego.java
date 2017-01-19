package principal.maquinaEstado.estados.juego;

import java.awt.Graphics;
import principal.Constantes;
import principal.entes.jugador.Jugador;
import principal.interfaz_usuario.GestorInterfaz;
import principal.mapas.Mapa;
import principal.maquinaEstado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

    private Mapa mapa = new Mapa(Constantes.NIVEL1_MAPA1, true);
    private Jugador jugador = new Jugador(Constantes.RUTA_PERSONAJE_1, Constantes.CENTRO_PANTALLA_X / 2 + Constantes.LADO_SPRITE / 2, Constantes.CENTRO_PANTALLA_Y / 2, mapa);

    @Override
    public void actualizar() {
        mapa.actualizar(jugador.obtenerPosicionX(), jugador.obtenerPosicionY());
        jugador.actualizar();
    }

    @Override
    public void dibujar(Graphics g) {
        mapa.dibujar(g, jugador.obtenerPosicionX(), jugador.obtenerPosicionY());
        jugador.dibujar(g);
        GestorInterfaz.gi.dibujar(g, jugador);
    }

}
