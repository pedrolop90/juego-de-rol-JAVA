package principal;

import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaEstado.GestorEstados;

public class GestorPrincipal {

    private boolean enFuncionamiento = false;
    private final String TITULO;
    private final int ANCHO;
    private final int ALTO;

    private SuperficieDibujo sd;
    private Ventana ventana;
    private GestorEstados ge;

    public static int fps = 0;
    public static int aps = 0;

    private GestorPrincipal(final String titulo, final int ancho, final int alto) {
        ANCHO = ancho;
        ALTO = alto;
        TITULO = titulo;
    }

    private void iniciarJuego() {
        enFuncionamiento = true;
        iniciar();
        iniciarBuclePrincipal();
    }

    private void iniciar() {
        sd = new SuperficieDibujo(ANCHO, ALTO);
        ventana = new Ventana(TITULO, sd);
        ge = new GestorEstados();
    }

    private void iniciarBuclePrincipal() {
        int actualizaciones = 0, framesAcumulados = 0;
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        double tiempoTranscurrido;
        double delta = 0;
        while (enFuncionamiento) {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            while (delta >= 1) {
                actualizaciones++;
                actualizar();
                delta--;
            }
            framesAcumulados++;
            dibujar();
            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                referenciaContador = System.nanoTime();
                GestorPrincipal.fps = framesAcumulados;
                GestorPrincipal.aps = actualizaciones;
                framesAcumulados = 0;
                actualizaciones = 0;
            }
        }
    }

    private void actualizar() {
        ge.actualizar();
    }

    private void dibujar() {
        ge.cambiarEstadoActual(Constantes.ESTADOACTUAL);
        sd.dibujar(ge);
    }

    public static void main(String[] args) {
        GestorPrincipal principal = new GestorPrincipal("Juego Rol", Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);
        principal.iniciarJuego();
    }

}
