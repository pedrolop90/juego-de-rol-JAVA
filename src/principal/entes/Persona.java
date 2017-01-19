package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.mapas.Mapa;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Persona extends Colision {

    protected boolean enMovimiento;
    protected int animacion;
    protected int estado;

    protected final int MAXIMA_RESISTENCIA;
    protected final int MAXIMA_RECUPERACION = 50;
    protected int recuperacion;
    protected boolean recuperado = true;

    protected final HojaSprites hs;
    protected BufferedImage imagenActual;
    protected int vida;
    protected int resistencia;
    protected int armadura;

    public final ArrayList<Barra> barras;
    protected final Inventario inventario;

    public Persona(final int vida,
            final int resistencia,
            final int armadura,
            final String ruta, final double posicionX, final double posicionY, final Mapa mapa) {
        super(mapa);
        this.vida = vida;
        MAXIMA_RESISTENCIA = resistencia;
        this.resistencia = resistencia;
        this.armadura = armadura;
        hs = new HojaSprites(ruta, Constantes.LADO_SPRITE, false, Sprite.DESTRUCTIBLE);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        barras = new ArrayList<>();
        barras.add(new Barra(Color.red, vida));
        barras.add(new Barra(Color.gray, armadura));
        barras.add(new Barra(Color.green, resistencia));
        inventario = new Inventario();
    }

    public Persona(final String ruta, final double posicionX, final double posicionY, final Mapa mapa, final int estado) {
        super(mapa);
        this.vida = Integer.MAX_VALUE;
        MAXIMA_RESISTENCIA = resistencia;
        this.resistencia = Integer.MAX_VALUE;
        this.armadura = Integer.MAX_VALUE;
        hs = new HojaSprites(ruta, Constantes.LADO_SPRITE, false, estado);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        barras = new ArrayList<>();
        barras.add(new Barra(Color.red, vida));
        barras.add(new Barra(Color.gray, armadura));
        barras.add(new Barra(Color.green, resistencia));
        inventario = new Inventario();
    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();
    }

    private void cambiarAnimacionEstado() {
        animacion++;
        if (animacion > 30) {
            animacion = 0;
        }
        if (animacion < 10) {
            estado = 1;
        } else if (animacion < 20) {
            estado = 0;
        } else {
            estado = 2;
        }
    }

    public void animar() {
        if (!enMovimiento) {
            estado = 0;
            animacion = 0;
        }
        imagenActual = hs.obtenerSprite(direccion, estado).obtenerImagen();
    }

    protected void determinarDireccion() {
    }

    protected void mover(final int velocidadX, final int velocidadY, boolean correr) {
        enMovimiento = true;
        cambiarDireccion(velocidadX, velocidadY);
        if (estaEnMapa(velocidadX, velocidadY)) {
            if (velocidadX != 0 && !enColision(velocidadX)) {
                posicionX += velocidadX * Constantes.VELOCIDAD_MOVIMIENTO;
                if (correr && resistencia > 0) {
                    resistencia--;
                }
            } else if (velocidadY != 0 && !enColision(velocidadY)) {
                posicionY += velocidadY * Constantes.VELOCIDAD_MOVIMIENTO;
                if (correr && resistencia > 0) {
                    resistencia--;
                }
            }
        }
    }

    protected void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == -1) {
            direccion = PersonaColision.IZQUIERDA;
        } else if (velocidadX == 1) {
            direccion = PersonaColision.DERECHA;
        }
        if (velocidadY == -1) {
            direccion = PersonaColision.ARRIBA;
        } else if (velocidadY == 1) {
            direccion = PersonaColision.ABAJO;
        }
    }

    public void dibujar(final Graphics g, final double centroX, final double centroY) {
        g.drawImage(imagenActual, (int) centroX, (int) centroY, null);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public int obtenerVida() {
        return vida;
    }

    public int obtenerResistencia() {
        return resistencia;
    }

    public int obtenerArmadura() {
        return armadura;
    }

}
