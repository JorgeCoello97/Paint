package practicafinal.model;

/*
 *
 * @author Yannick & Jorge
 */
public class Cuadrado extends Figura{
    private int x;
    private int y;
    private int width;
    private int height;

    public Cuadrado() {
        super();
        this.x = 10;
        this.y = 10;
        this.width = 100;
        this.height = 100;
    }
    
    //SETTERS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    //GETTERS

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
