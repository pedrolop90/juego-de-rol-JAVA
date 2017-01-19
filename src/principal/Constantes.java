package principal;

import java.awt.Toolkit;

public class Constantes {

    public static final int LADO_SPRITE = 32;
    public static final int LADO_TILE = 32;

    public static int ANCHO_JUEGO = 640;
    public static int ALTO_JUEGO = 360;
    public static int CENTRO_PANTALLA_X = Constantes.ANCHO_JUEGO / 2;
    public static int CENTRO_PANTALLA_Y = Constantes.ALTO_JUEGO / 2;

    public static int ANCHO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int ALTO_PANTALLA_COMPLETA = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static double PROPORCION_X = Constantes.ANCHO_PANTALLA_COMPLETA / Constantes.ANCHO_JUEGO;
    public static double PROPORCION_Y = Constantes.ALTO_PANTALLA_COMPLETA / Constantes.ALTO_JUEGO;

    public static double VELOCIDAD_MOVIMIENTO = 1;

    public static String NIVEL1_MAPA1 = "/mapa/nivel1/1";
    public static String SOMBRAS = "/mapa/sombras";
    public static String MAPA_1 = "/mapa/prueba";
    public static String RUTA_PERSONAJE_1 = "/imagenes/Personajes/2.png";
    public static String RUTA_PERSONAJE_2 = "/imagenes/Personajes/1.png";
    public static String RUTA_CURSOR = "/imagenes/iconos/iconoCursor.png";

    public static int ESTADOACTUAL = 0;

}
