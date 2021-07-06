package practicafinal.model;

import java.awt.Color;

/*
 *
 * @author Yannick & Jorge
 */
public class Linea extends Figura{
private int origen_x, origen_y;
    private int destino_x, destino_y;
    //CONSTRUCTORES
    public Linea() {
        this.origen_x = 0;
        this.origen_y = 0;
        this.destino_x = 50;
        this.destino_y = 50;
    }
    
    public Linea(int origen_x, int origen_y, int destino_x, int destino_y, Color color1, int grosor) {
        this.origen_x = origen_x;
        this.origen_y = origen_y;
        this.destino_x = destino_x;
        this.destino_y = destino_y;
        this.color1 = color1;
        this.grosor = grosor;
        this.degradado = false;
    }
    
    public Linea(int origen_x, int origen_y, int destino_x, int destino_y, Color color1, Color color2, int grosor) {
        this.origen_x = origen_x;
        this.origen_y = origen_y;
        this.destino_x = destino_x;
        this.destino_y = destino_y;
        this.color1 = color1;
        this.color2 = color2;
        this.grosor = grosor;
        this.degradado = true;
    }
    
    //SETTERS
    public void setOrigen_x(int origen_x) {
        this.origen_x = origen_x;
    }

    public void setOrigen_y(int origen_y) {
        this.origen_y = origen_y;
    }

    public void setDestino_x(int destino_x) {
        this.destino_x = destino_x;
    }

    public void setDestino_y(int destino_y) {
        this.destino_y = destino_y;
    }
    
    //GETTERS

    public int getOrigen_x() {
        return origen_x;
    }

    public int getOrigen_y() {
        return origen_y;
    }

    public int getDestino_x() {
        return destino_x;
    }

    public int getDestino_y() {
        return destino_y;
    }
    
}
