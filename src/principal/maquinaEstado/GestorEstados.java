package principal.maquinaEstado;

import java.awt.Graphics;
import principal.maquinaEstado.estados.juego.GestorJuego;

public class GestorEstados {

    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;

    public GestorEstados() {
        iniciarEstados();
        iniciarEstadoActual(0);
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[1];
        estados[0] = new GestorJuego();
    }

    private void iniciarEstadoActual(final int estado) {
        estadoActual = estados[estado];
    }

    public void actualizar() {
        estadoActual.actualizar();
    }

    public void dibujar(final Graphics g) {
        estadoActual.dibujar(g);
    }

    public void cambiarEstadoActual(final int nuevoEstado) {
        estadoActual = estados[nuevoEstado];
    }

    public EstadoJuego obtenerEstadoActual() {
        return estadoActual;
    }

}
