package graficos;

import elementos.Explosion;
import elementos.Fondo;
import elementos.Pelota;

public class Pantalla {
    
    private final int ancho;
    private final int alto;
    
    public final int[] pixeles;
    
    // temporal
    private final static int LADO_SPRITE = 32;
    private final static int MASCARA_SPRITE = LADO_SPRITE - 1;
    // fin temporal

    public Pantalla(int ancho, int alto) {
        
        this.ancho = ancho;
        this.alto = alto;
        
        pixeles = new int[ancho * alto];
    }
    
    public void Limpiar() {
        
        for(int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }
    
    public void mostrar(int x, int y, int i, int j){
        
         Fondo fondo = new Fondo(pixeles, ancho, alto);
         Pelota pelota1 = new Pelota(pixeles, x, y,  ancho, alto);
         Pelota pelota2 = new Pelota(pixeles, i, j, ancho, alto);
        if((y+32)==j){
            Explosion explosion = new Explosion(pixeles, 268, 386, ancho, alto);
         }
    }
    /*//fin temporal
    public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
        for(int y = 0; y < cuadro.sprite.getLado(); y++){
            int posicionY = y + compensacionY;
            for(int x = 0; x < cuadro.sprite.getLado(); x++){
                int posicionX = x + compensacionX;
                if (posicionX < 0 || posicionX > ancho|| posicionY < 0 ||
                        posicionY > alto) {
                    break;
                }
                pixeles[posicionX + posicionY * ancho] = cuadro.sprite.
                        pixeles[x + y * cuadro.sprite.getLado()];
            }
        }        
    }*/
}