package principal.herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class cargadorRecursos {

    public static BufferedImage cargarImagenCompatibleOpaca(String ruta) {
        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen2 = gc.createCompatibleImage(imagen.getWidth(null), imagen.getWidth(null), Transparency.OPAQUE);
        Graphics g = imagen2.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        return imagen2;
    }

    public static BufferedImage cargarImagenCompatibleTransparente(String ruta) {
        Image imagen = null;
        try {
            imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage imagen2 = gc.createCompatibleImage(imagen.getWidth(null), imagen.getWidth(null), Transparency.TRANSLUCENT);
        Graphics g = imagen2.getGraphics();
        g.drawImage(imagen, 0, 0, null);
        g.dispose();
        return imagen2;
    }

    public static String leerArchivoTexto(final String ruta) {
        StringBuilder contenido = new StringBuilder();
        InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));
        String linea = "";
        try {
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (entradaBytes != null) {
                    entradaBytes.close();
                }
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return contenido.toString();
    }

}
