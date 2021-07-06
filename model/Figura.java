package practicafinal.model;

import java.awt.Color;

/*
 *
 * @author Yannick & Jorge
 */
public class Figura{
    protected Color color1, color2;
    protected int grosor;
    protected boolean degradado;

    public Figura() {
        this.color1 = Color.BLACK;
        this.color2 = Color.BLUE;
        this.grosor = 20;
        this.degradado = false;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }
    
    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public int getGrosor() {
        return grosor;
    }

    public boolean isDegradado() {
        return degradado;
    }
    
    
}
