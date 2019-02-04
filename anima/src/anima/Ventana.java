package anima;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import graficos.Pantalla;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;

public class Ventana extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;

    private static JFrame ventana;
    private static Thread thread;
    private static Pantalla pantalla;
    
    private static volatile boolean enFuncionamiento = false;
    
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    private static final String NOMBRE = "Juego";
    
    private static int aps = 0;
    private static int fps = 0;
    
    private static int x = 268;
    private static int y = 0;
    private static int i = 268;
    private static int j = 768;
    
    //Imagen en Buffer en blanco
    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, 
            BufferedImage.TYPE_INT_RGB);
    //Devuelve arr[] de ints
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().
            getDataBuffer()).getData();
    //((casting) devuelve raster, vuelve datos arr[]) devuelve datos
    
    public Ventana(){
        
        setPreferredSize(new Dimension(ANCHO, ALTO));
        
        pantalla = new Pantalla(ANCHO, ALTO); 
        
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        iniciar();
        
        
    }
    
    private synchronized void iniciar(){
        
        enFuncionamiento = true;
        thread = new Thread(this, "Graficos");
        thread.start();
    }
    
    private synchronized void detener() {
    
        enFuncionamiento = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    private void actualizar() {

        y++;
        j--;
        aps++;
            
    }
    
    private void mostrar() {
        
        BufferStrategy estrategia = getBufferStrategy();
        
        if (estrategia == null){
            createBufferStrategy(3);
            return;
        }
        
        pantalla.Limpiar();
        pantalla.mostrar(x, y, i, j);
        
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        //(de que arr[] cp, ini[], a que arr[] cp, ini[], tamaño arr[]) 
        /*for (int i = 0; i < pixeles.length; i++){
            pixeles[i] = pantalla.pixeles[i];
        }*/
        
        //Obj Dibuja lo que este en la estrategia o Buffer
        Graphics g = estrategia.getDrawGraphics();
        
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        //(BufferImage, desde donde dibujar, alto ,ancho)
        //Vacea g o destruye memoria usada por g
        g.dispose();
        
        estrategia.show();
        
        fps++;
    }

    @Override
    public void run() {
        
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        
        requestFocus();

        while (enFuncionamiento) {
            
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido =  inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
                
            while(delta >= 1){
            
                actualizar();
                delta--;
            }
            
            mostrar();
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}

