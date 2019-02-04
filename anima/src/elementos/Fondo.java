package elementos;

import graficos.Sprites;

public class Fondo {
    
    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_SPRITE = LADO_SPRITE - 1;

    public Fondo(int[] pixeles, int ancho, int alto) {
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++){
                
                pixeles[x + y * ancho] = Sprites.FONDO.pixeles[
                        (x & MASCARA_SPRITE) + (y & MASCARA_SPRITE) * 
                        LADO_SPRITE];
            }
        }
    }
}    