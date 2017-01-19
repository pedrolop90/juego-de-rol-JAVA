package principal.graficos;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Ventana extends JFrame {

    private final String TITULO;

    public Ventana(final String titulo, final SuperficieDibujo sd) {
        TITULO = titulo;
        configurarVentana(sd);
    }

    private void configurarVentana(final SuperficieDibujo sd) {
        setTitle(TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
