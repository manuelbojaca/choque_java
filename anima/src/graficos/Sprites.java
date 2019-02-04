package graficos;

public final class Sprites {
    
    private final int lado;
    
    private int x;
    private int y;
    
    public int[] pixeles;
    private final HojaSprites hoja;
    
    //coleccion hojas de sprites
    public static Sprites FONDO = new Sprites(32, 4, 0, HojaSprites.desierto);
    public static Sprites PELOTA = new Sprites(32, 0, 1, HojaSprites.desierto);
    public static Sprites EXPLOSION = new Sprites(32, 2, 0, HojaSprites.desierto);
    //fin coleccion

    public Sprites(final int lado, final int columna, final int fila, 
            final HojaSprites hoja){
        
        this.lado = lado;
        this.hoja = hoja;
        
        pixeles = new int[lado * lado];
        
        this.x = columna * lado;
        this.y = fila * lado;
        
        for(int y = 0; y < lado; y++){
            for(int x = 0; x < lado; x++){
                pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y)
                        * hoja.getAncho()];
            }
        }
    }   
    public int getLado () {
        return lado;
    }
}