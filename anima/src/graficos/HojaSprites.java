package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HojaSprites {
    
    private final int ancho;
    private final int alto;
    public final int[] pixeles;
    
    //coleccion hojas de sprites
    public static HojaSprites desierto = new HojaSprites(
            "/Texturas/Desierto.png", 320, 320);
    //fin coleccion
    
    public HojaSprites(final String ruta, final int ancho, final int alto) {
        
        this.alto = alto;
        this.ancho = ancho;
        
        pixeles = new int[ancho * alto];
        
        BufferedImage imagen;
        
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
}
