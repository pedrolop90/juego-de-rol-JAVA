package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
    
    public Tecla arriba = new Tecla();
    public Tecla abajo = new Tecla();
    public Tecla izquierda = new Tecla();
    public Tecla derecha = new Tecla();
    public boolean correr;
    public boolean salir;
    
    public void ComprobarTecla(int keyEvent, boolean val) {
        switch (keyEvent) {
            case KeyEvent.VK_W:
                arriba.modificarTecla(val);
                break;
            case KeyEvent.VK_S:
                abajo.modificarTecla(val);
                break;
            case KeyEvent.VK_A:
                izquierda.modificarTecla(val);
                break;
            case KeyEvent.VK_D:
                derecha.modificarTecla(val);
                break;
            case KeyEvent.VK_SHIFT:
                correr = val;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        ComprobarTecla(e.getKeyCode(), true);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        ComprobarTecla(e.getKeyCode(), false);
    }
    
}
