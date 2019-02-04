package elementos;

import graficos.Sprites;

public class Explosion {

    private final static int LADO_SPRITE = 32;
    
    public Explosion(int[] pixeles, int compensacionY, int compensacionX, int ancho, int alto) {

        for(int y = 0; y < LADO_SPRITE; y++){
            int posicionY = y + compensacionY;
            for(int x = 0; x < LADO_SPRITE; x++){
                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX > ancho|| posicionY < 0 ||
                        posicionY > alto) {
                    break;
                }
                pixeles[posicionX + posicionY * ancho] = Sprites.EXPLOSION
                        .pixeles[x + y * LADO_SPRITE];
            }
        }    
    }
}