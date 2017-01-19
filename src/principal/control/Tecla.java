package principal.control;

public class Tecla {

    private long ultimaPulsacion = System.nanoTime();
    private boolean pulsada = false;

    public void modificarTecla(boolean val) {
        pulsada = val;
        ultimaPulsacion = System.nanoTime();
    }

    public boolean estaPulsada() {
        return pulsada;
    }

    public long obtenerUltimaPulsacion() {
        return ultimaPulsacion;
    }

}
