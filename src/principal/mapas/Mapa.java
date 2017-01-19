package principal.mapas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.entes.ObjetoColision;
import principal.entes.Persona;
import principal.herramientas.cargadorRecursos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Mapa {
    
    private final int ANCHO;
    private final int ALTO;
    private final int NN = 2;
    
    private final int MargenX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
    private final int MargenY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
    
    public final Sprite[] sprites;
    private boolean[] colisiones;
    private final ArrayList<Integer> mapa;
    public ArrayList<ObjetoColision> areasColision = new ArrayList<ObjetoColision>();
    private Mapa mapaTransporte;
    public ArrayList<Persona> personas;
    
    public Mapa(final String ruta, final boolean opaco) {
        String contenido = cargadorRecursos.leerArchivoTexto(ruta);
        String[] partes = contenido.split("\\*");
        ANCHO = Integer.parseInt(partes[0]);
        ALTO = Integer.parseInt(partes[1]);
        sprites = extraerSprites(partes[2].split("\\,"), partes[3].split("#"), opaco);
        mapa = extraerMapa(partes[4].split(" "));
        personas = new ArrayList<>();
    }
    
    private void agregarTerrorista() {
        
    }
    
    private Sprite[] extraerSprites(String[] hojasSprites, String[] reglas, final boolean opaco) {
        Sprite[] sprites = new Sprite[reglas.length];
        HojaSprites hoja = new HojaSprites("/imagenes/Texturas/" + Integer.parseInt(hojasSprites[0]) + ".png", Constantes.LADO_SPRITE, opaco);
        for (int i = 0, s = 0; i < reglas.length; i++) {
            String[] regla = reglas[i].split("-");
            if (Integer.parseInt(regla[1]) != Integer.parseInt(hojasSprites[s])) {
                hoja = new HojaSprites("/imagenes/Texturas/" + Integer.parseInt(regla[1]) + ".png", Constantes.LADO_SPRITE, opaco);
                s++;
            }
            sprites[Integer.parseInt(regla[0])] = new Sprite(hoja.obtenerSprite(Integer.parseInt(regla[2])).obtenerImagen(), Integer.parseInt(regla[3]));
        }
        return sprites;
    }
    
    private ArrayList<Integer> extraerMapa(final String[] mapaTexto) {
        ArrayList<Integer> mapa = new ArrayList<Integer>();
        for (int i = 0; i < mapaTexto.length; i++) {
            if (mapaTexto[i].length() == NN) {
                mapa.add(Integer.parseInt(mapaTexto[i]));
            } else {
                mapa.add(Integer.parseInt(mapaTexto[i].substring(0, NN)));
                mapa.add(Integer.parseInt(mapaTexto[i].substring(NN, mapaTexto[i].length())));
            }
        }
        return mapa;
    }
    
    public void actualizar(final double posicionX, final double posicionY) {
        if (!areasColision.isEmpty()) {
            areasColision.clear();
        }
        for (int y = 0; y < this.ALTO; y++) {
            for (int x = 0; x < this.ANCHO; x++) {
                double puntoX = x * Constantes.LADO_SPRITE - posicionX + MargenX;
                double puntoY = y * Constantes.LADO_SPRITE - posicionY + MargenY;
                int temp = sprites[mapa.get(x + y * this.ANCHO)].obtenerEstado();
                if (temp == Sprite.INDESTRUCTIBLE || temp == Sprite.DESTRUCTIBLE) {
                    areasColision.add(new ObjetoColision(sprites[mapa.get(x + y * this.ANCHO)], new Rectangle((int) puntoX, (int) puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE)));
                }
            }
        }
    }
    
    public void dibujar(final Graphics g, final double posicionX, final double posicionY) {
        for (int y = 0; y < ALTO; y++) {
            for (int x = 0; x < ANCHO; x++) {
                double puntoX = x * Constantes.LADO_SPRITE - posicionX + MargenX;
                double puntoY = y * Constantes.LADO_SPRITE - posicionY + MargenY;
                g.drawImage(sprites[mapa.get(x + y * ANCHO)].obtenerImagen(), (int) puntoX, (int) puntoY, null);
            }
        }
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).estaVivo()) {
                personas.get(i).dibujar(g, (int) personas.get(i).obtenerPosicionX(), (int) personas.get(i).obtenerPosicionY());
            } else {
                personas.remove(i);
                i--;
            }
        }
    }
    
    public Rectangle obtenerBorder(final int posicionX, final int posicionY, final int anchoJugador, final int altoJugador) {
        int x = MargenX - posicionX + anchoJugador;
        int y = MargenY - posicionY + altoJugador;
        int ancho = ANCHO * Constantes.LADO_SPRITE - anchoJugador * 2;
        int alto = ALTO * Constantes.LADO_SPRITE - altoJugador * 2;
        return new Rectangle(x, y, ancho, alto);
    }
    
}
