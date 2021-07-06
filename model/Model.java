package practicafinal.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

/*
 *
 * @author Yannick & Jorge
 */
public class Model {
    
    public static final int FIGURA_LINEA = 0;
    public static final int FIGURA_CIRCULO = 1;
    public static final int FIGURA_CUADRADO = 2;
    
    public static final int SIN_DEGRADADO = 0;
    public static final int CON_DEGRADADO = 1;
    public static final String TITULO_POR_DEFECTO = "Sin título";
    public static final String FORMATO_IMAGEN = "jpg";
    
    //ARCHIVO
    private String nombre_archivo;
    private File archivo;
    
    //DIBUJO SKETCHER
    private Stack<BufferedImage> undo_stack, redo_stack;
    private BufferedImage dibujo;
    private Graphics2D graphics_dibujo;
    private boolean deshacer_activo;   
    private Stack<Integer> undoFiguras, redoFiguras;
    private int figura_actual;
    private int num_lineas;
    private int num_circulos;
    private int num_cuadrados;
    
    
    //VISTA PREVIA
    private BufferedImage vista_previa;
    private Graphics2D graphics_vista_previa;
    private Linea linea_vista_previa;
    private Circulo circulo_vista_previa;
    private Cuadrado cuadrado_vista_previa;
    
    //ATRIBUTOS VENTANA CONFIGURACION
    private int grosor_defecto;
    private Color color1_defecto, color2_defecto;
    private boolean degradado_defecto;
    private int figura_actual_defecto;
    
    //ATRIBUTOS VENTANA SKETCHER
    private int grosor;
    private Color color1, color2;
    private boolean degradado;
    private boolean defectoActivo;
    private boolean borrarActivo = false;
    
    public Model() {
        
        //VENTANA CONFIGURACIÓN
        this.nombre_archivo = TITULO_POR_DEFECTO;
        this.grosor_defecto = 20;
        this.color1_defecto = Color.BLACK;
        this.color2_defecto = Color.ORANGE;
        this.degradado_defecto = false;
        this.figura_actual_defecto = FIGURA_LINEA;
        
        //VENTANA SKETCHER
        this.grosor = grosor_defecto;
        this.color1 = color1_defecto;
        this.color2 = color2_defecto;
        this.degradado = degradado_defecto;
        this.figura_actual = figura_actual_defecto;
        
        //VISTA PREVIA
        inicializarCuadradoVistaPrevia();
        inicializarCirculoVistaPrevia();
        inicializarLineaVistaPrevia();

        //DIBUJO SKETCHER
        dibujo = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        
        graphics_dibujo = dibujo.createGraphics();
        graphics_dibujo.setPaint(new Color(255, 255, 255));
        graphics_dibujo.fillRect(0, 0, 1200, 800);
        
        
        undo_stack = new Stack<>();
        redo_stack = new Stack<>();
        
        undoFiguras = new Stack<>();
        redoFiguras = new Stack<>();
        
        
        this.num_lineas = 0;
        this.num_circulos = 0;
        this.num_cuadrados = 0;
        
        this.deshacer_activo = false;
        this.defectoActivo = true;
  
    }    

/******************************** SETTERS *************************************/
    /***** SETTERS VENTANA CONFIGURACIÓN **************************************/    
    public void setGrosorDefecto(int grosor_defecto) {
        this.grosor_defecto = grosor_defecto;
    }
    
    public void setColor1Defecto(Color color1_defecto) {
        this.color1_defecto = color1_defecto;
    }
    
    public void setColor2Defecto(Color color2_defecto) {
        this.color2_defecto = color2_defecto;
    }

    public void setDegradadoDefecto(boolean degradado_defecto) {
        this.degradado_defecto = degradado_defecto;
    }

    public void setFiguraActualDefecto(int figura_actual_defecto) {
        this.figura_actual_defecto = figura_actual_defecto;
    }
    
    /***** SETTERS VENTANA SKETCHER *******************************************/    
    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void setDegradado(boolean degradado) {
        this.degradado = degradado;
    }

    /***** SETTERS MODELO( ARCHIVO, DIBUJO, CONF_DEFECTO) *********************/
    public void setDefectoActivo(boolean defectoActivo) {
        this.defectoActivo = defectoActivo;
    }

    public void setBorrarActivo(boolean borrarActivo) {
        this.borrarActivo = borrarActivo;
    }

    public void setNombreArchivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }
  
    public void setArchivo(File archivo) {
        try{
            this.archivo = archivo;
            if(!archivo.getName().contains(FORMATO_IMAGEN))
                this.nombre_archivo = archivo.getName().concat(".".concat(FORMATO_IMAGEN));
            else
                this.nombre_archivo = archivo.getName();            
        }
        catch(Exception e){
        }   
    }

    public void setDibujo(BufferedImage dibujo) {
        this.dibujo = dibujo;
    }

    public void setFiguraActual(int figura_actual) {
        this.figura_actual = figura_actual;
    }

    public void setNumLineas(int num_lineas) {
        this.num_lineas = num_lineas;
    }

    public void setNumCirculos(int num_circulos) {
        this.num_circulos = num_circulos;
    }

    public void setNumCuadrados(int num_cuadrados) {
        this.num_cuadrados = num_cuadrados;
    }
    
    
    
/**************************** FIN SETTERS *************************************/    
    
/******************************** GETTERS *************************************/
    /***** GETTERS VENTANA CONFIGURACIÓN **************************************/
    public int getGrosorDefecto() {
        return grosor_defecto;
    }

    public Color getColor1Defecto() {
        return color1_defecto;
    }

    public Color getColor2Defecto() {
        return color2_defecto;
    }

    public boolean isDegradadoDefecto() {
        return degradado_defecto;
    }

    public int getFiguraActualDefecto() {
        return figura_actual_defecto;
    }
    
    
    /***** GETTERS VENTANA SKETCHER *******************************************/
    public int getGrosor() {
        return grosor;
    }

    public Color getColor1() {
        return color1;
    }
    
    public Color getColor2() {    
        return color2;
    }

    public boolean isDegradado() {
        return degradado;
    }
    
    
    /***** GETTERS MODELO( ARCHIVO, DIBUJO, CONF_DEFECTO) *********************/
    
    public boolean isDefectoActivo() {
        return defectoActivo;
    }

    public boolean isBorrarActivo() {
        return borrarActivo;
    }
    
    public Linea getLineaVistaPrevia() {
        return linea_vista_previa;
    }
    public Circulo getCirculoVistaPrevia() {
        return circulo_vista_previa;
    }
    public Cuadrado getCuadradoVistaPrevia(){
        return cuadrado_vista_previa;
    }
    public String getNombreArchivo() {
        return nombre_archivo;
    }

    public File getArchivo() {
        return archivo;
    }

    public BufferedImage getDibujo() {
        return dibujo;
    }
    public BufferedImage getDibujoActual() {
        return dibujo;
    }
    
    public BufferedImage getVistaPrevia() {
        return vista_previa;
    }
    
    public boolean isDeshacerActivo() {
        return deshacer_activo;
    }

    public int getNumLineas() {
        return num_lineas;
    }

    public int getNumCirculos() {
        return num_circulos;
    }

    public int getNumCuadrados() {
        return num_cuadrados;
    }

    public int getFiguraActual() {
        return figura_actual;
    }

    public Stack<Integer> getUndoFiguras() {
        return undoFiguras;
    }

    public Stack<Integer> getRedoFiguras() {
        return redoFiguras;
    }
    
    
/**************************** FIN GETTERS *************************************/
    
    
/******************** METODOS DEL DIBUJO SKETCHER *****************************/
    public void nuevoDibujo()
    {
        dibujo.flush();
        dibujo = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        graphics_dibujo = dibujo.createGraphics();

        graphics_dibujo.setPaint(new Color(255, 255, 255));
        graphics_dibujo.fillRect(0, 0, 1200, 800);
        
        undoFiguras.clear();
    }

    public boolean deshacerDibujo()
    {
        boolean ok = false;
        if(undo_stack.size() > 0)
        {
            if(borrarActivo && undo_stack.size() > 0)
                setUndoImage(undo_stack.pop());
            else{
                if(undo_stack.size() > 0)        
                    setUndoImage(undo_stack.pop());
                
                if(undo_stack.size() > 0)
                    setUndoImage(undo_stack.pop());
            }
            ok = true;
        }

        
        return ok;
    }
    
    public boolean rehacerDibujo()
    {
        boolean ok = false;
        if(redo_stack.size() > 0)
        {
            if(borrarActivo && redo_stack.size() > 0)
                setRedoImage(redo_stack.pop());
            else{
                if(redo_stack.size() > 0)
                    setRedoImage(redo_stack.pop());
                
                if(redo_stack.size() > 0)
                    setRedoImage(redo_stack.pop());                
            }
            
            ok = true;
        }
        return ok;
    }
    
    public void setUndoImage(BufferedImage io)
    {
        graphics_dibujo = (Graphics2D) io.getGraphics();
        graphics_dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics_dibujo.drawImage(io, 0, 0, io.getWidth(), io.getHeight(), null);
        dibujo = io;
        redo_stack.push(io);
    }
    
    public void setRedoImage(BufferedImage io)
    {
        graphics_dibujo = (Graphics2D) io.getGraphics();
        graphics_dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics_dibujo.drawImage(io, 0, 0, io.getWidth(), io.getHeight(), null);
        dibujo = io;
        undo_stack.push(io);
    }
    
    public void saveToStack(BufferedImage io)
    {
        BufferedImage bi = new BufferedImage(io.getWidth(), io.getHeight(), io.getType());
        Graphics2D gd = bi.createGraphics();
        gd.drawImage(io, 0, 0, null);
        undo_stack.push(bi);
    }
    
    public void pintarCuadrado (Cuadrado cuadrado){
        saveToStack(dibujo);

        GradientPaint gradientPaint = new GradientPaint(
                cuadrado.getX()*2, cuadrado.getY()*2, cuadrado.getColor1(),
                cuadrado.getX()/2, cuadrado.getY()/2, cuadrado.getColor2());
        
        graphics_dibujo = (Graphics2D)dibujo.createGraphics();
        
        if(cuadrado.isDegradado())
            graphics_dibujo.setPaint(gradientPaint);
        else
            graphics_dibujo.setColor(cuadrado.getColor1());
        
        graphics_dibujo.setStroke(new BasicStroke(cuadrado.getGrosor()));

        graphics_dibujo.fillRect(cuadrado.getX(), cuadrado.getY(),
                cuadrado.getWidth(), cuadrado.getHeight());
        
        saveToStack(dibujo);
        
        redo_stack.clear();
        redoFiguras.clear();
        
        graphics_dibujo.dispose();
        
        undoFiguras.push(getFiguraActual());
        
        if(deshacer_activo)
            deshacer_activo = false;
    }
    
    public void pintarCirculo (Circulo circulo){
        saveToStack(dibujo);

        GradientPaint gradientPaint = new GradientPaint(
                circulo.getX()*2, circulo.getY()*2, circulo.getColor1(),
                circulo.getWidth()/2, circulo.getHeight()/2, circulo.getColor2());
        
        graphics_dibujo = (Graphics2D)dibujo.createGraphics();
        
        if(circulo.isDegradado())
            graphics_dibujo.setPaint(gradientPaint);
        else
            graphics_dibujo.setColor(circulo.getColor1());
        
        graphics_dibujo.setStroke(new BasicStroke(circulo.getGrosor()));

        graphics_dibujo.fillOval(circulo.getX(), circulo.getY(),
                circulo.getWidth(), circulo.getHeight());
        
        saveToStack(dibujo);
        
        redo_stack.clear();
        redoFiguras.clear();
        
        graphics_dibujo.dispose();
        
        undoFiguras.push(getFiguraActual());
        
        if(deshacer_activo)
            deshacer_activo = false;
    }
    
    public void pintarLinea (Linea linea){
        saveToStack(dibujo);

        GradientPaint gradientPaint = new GradientPaint(
                linea.getOrigen_x(), linea.getOrigen_y(), linea.getColor1(),
                linea.getDestino_x(), linea.getDestino_y(), linea.getColor2());
        
        graphics_dibujo = (Graphics2D)dibujo.createGraphics();
        
        if(linea.isDegradado())
            graphics_dibujo.setPaint(gradientPaint);
        else
            graphics_dibujo.setColor(linea.getColor1());
        
        graphics_dibujo.setStroke(new BasicStroke(linea.getGrosor()));

        graphics_dibujo.drawLine(
                linea.getOrigen_x(), linea.getOrigen_y(), 
                linea.getDestino_x(), linea.getDestino_y());
        
        saveToStack(dibujo);
        
        redo_stack.clear();
        redoFiguras.clear();
        
        graphics_dibujo.dispose();
        
        undoFiguras.push(getFiguraActual());
        
        if(deshacer_activo)
            deshacer_activo = false;
    }
    
    public void borrador(int x, int y){

        borrarActivo = true;

        graphics_dibujo = (Graphics2D)dibujo.createGraphics();
        
        graphics_dibujo.setColor(Color.WHITE);
        if (isDefectoActivo()) 
            graphics_dibujo.fillRect(x, y, getGrosorDefecto(), getGrosorDefecto());
        else
            graphics_dibujo.fillRect(x, y, getGrosor(), getGrosor());

        
        redo_stack.clear();
        
        graphics_dibujo.dispose();
        
        if(deshacer_activo)
            deshacer_activo = false;
                
    }
    
    public void pintarLineaVistaPrevia() {
        GradientPaint gradientPaint;
    
        graphics_vista_previa = (Graphics2D)vista_previa.getGraphics();
        
        graphics_vista_previa.setColor(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 900, 150);
        
        if(linea_vista_previa.isDegradado())
        {
           gradientPaint = new GradientPaint(
                linea_vista_previa.getOrigen_x(), linea_vista_previa.getOrigen_y(),
                linea_vista_previa.getColor1(), linea_vista_previa.getDestino_x(),
                linea_vista_previa.getDestino_y(), linea_vista_previa.getColor2());
           
            graphics_vista_previa.setPaint(gradientPaint);
        }
        else
            graphics_vista_previa.setColor(linea_vista_previa.getColor1());
        
        graphics_vista_previa.setStroke(new BasicStroke(linea_vista_previa.getGrosor()));
        
        graphics_vista_previa.drawLine(
                linea_vista_previa.getOrigen_x(), linea_vista_previa.getOrigen_y(),
                linea_vista_previa.getDestino_x(), linea_vista_previa.getDestino_y());
        
        graphics_vista_previa.dispose();
    }
    public void pintarCirculoVistaPrevia (){

        GradientPaint gradientPaint;
        
        graphics_vista_previa = (Graphics2D)vista_previa.createGraphics();
        
        graphics_vista_previa.setColor(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 900, 150);
        
        if(circulo_vista_previa.isDegradado()){
            gradientPaint = new GradientPaint(
                circulo_vista_previa.getX()*2, circulo_vista_previa.getY()*2,
                circulo_vista_previa.getColor1(), circulo_vista_previa.getWidth()/2,
                circulo_vista_previa.getHeight()/2, circulo_vista_previa.getColor2());
                
            graphics_vista_previa.setPaint(gradientPaint);
        }
        else
            graphics_vista_previa.setColor(circulo_vista_previa.getColor1());
        
        graphics_vista_previa.setStroke(new BasicStroke(circulo_vista_previa.getGrosor()));

        graphics_vista_previa.fillOval(
                circulo_vista_previa.getX() - (circulo_vista_previa.getWidth()/2),
                circulo_vista_previa.getY() - (circulo_vista_previa.getWidth()/2),
                circulo_vista_previa.getWidth(), circulo_vista_previa.getHeight());
    
        graphics_vista_previa.dispose();
        
    }
public void pintarCuadradoVistaPrevia (){

        GradientPaint gradientPaint;

        graphics_vista_previa = (Graphics2D)vista_previa.createGraphics();
        
        graphics_vista_previa.setColor(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 900, 150);
        
        if(cuadrado_vista_previa.isDegradado()){
            gradientPaint = new GradientPaint(
                cuadrado_vista_previa.getX()*2, cuadrado_vista_previa.getY()*2,
                cuadrado_vista_previa.getColor1(), cuadrado_vista_previa.getX()/2,
                cuadrado_vista_previa.getY()/2, cuadrado_vista_previa.getColor2());
                
            graphics_vista_previa.setPaint(gradientPaint);
        }
        else
            graphics_vista_previa.setColor(cuadrado_vista_previa.getColor1());
        
        graphics_vista_previa.setStroke(new BasicStroke(cuadrado_vista_previa.getGrosor()));

        graphics_vista_previa.fillRect(
                cuadrado_vista_previa.getX() - (cuadrado_vista_previa.getWidth()/2),
                cuadrado_vista_previa.getY() - (cuadrado_vista_previa.getWidth()/2),
                cuadrado_vista_previa.getWidth(), cuadrado_vista_previa.getHeight());
    
        graphics_vista_previa.dispose();
        
    }
        
    
    public void asignarValores() {
        this.grosor = grosor_defecto;
        this.color1 = color1_defecto;
        this.color2 = color2_defecto;
        this.degradado = degradado_defecto;
        this.figura_actual = figura_actual_defecto;
    }
    public void reiniciarContadoresFiguras()
    {
        this.num_lineas = 0;
        this.num_circulos = 0;
        this.num_cuadrados = 0;
    }
    
    public void inicializarLineaVistaPrevia(){
        vista_previa = new BufferedImage(800, 100, BufferedImage.TYPE_INT_RGB);
        graphics_vista_previa = vista_previa.createGraphics();
        graphics_vista_previa.setPaint(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 800, 100);
        
        linea_vista_previa = new Linea();
        linea_vista_previa.setDegradado(degradado_defecto);
        linea_vista_previa.setOrigen_x(225);
        linea_vista_previa.setOrigen_y(50);
        linea_vista_previa.setDestino_x(425);
        linea_vista_previa.setDestino_y(50);
        linea_vista_previa.setColor1(color1_defecto);
        linea_vista_previa.setColor2(color2_defecto);
        linea_vista_previa.setGrosor(grosor_defecto);
        
        graphics_vista_previa.setPaint(linea_vista_previa.getColor1());
        graphics_vista_previa.setStroke(new BasicStroke(linea_vista_previa.getGrosor()));
        graphics_vista_previa.drawLine(linea_vista_previa.getOrigen_x(), linea_vista_previa.getOrigen_y(),
                linea_vista_previa.getDestino_x(), linea_vista_previa.getDestino_y());        
    }
    public void inicializarCirculoVistaPrevia(){
        vista_previa = new BufferedImage(800, 100, BufferedImage.TYPE_INT_RGB);
        graphics_vista_previa = vista_previa.createGraphics();
        graphics_vista_previa.setPaint(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 800, 100);
        
        circulo_vista_previa = new Circulo();
        circulo_vista_previa.setDegradado(degradado_defecto);
        circulo_vista_previa.setX(325 - (getGrosor()/2));
        circulo_vista_previa.setY(60 - (getGrosor()/2));
        circulo_vista_previa.setWidth(grosor_defecto);
        circulo_vista_previa.setHeight(grosor_defecto);
        circulo_vista_previa.setColor1(color1_defecto);
        circulo_vista_previa.setColor2(color2_defecto);
        circulo_vista_previa.setGrosor(grosor_defecto);       
    }
    public void inicializarCuadradoVistaPrevia(){
        vista_previa = new BufferedImage(800, 100, BufferedImage.TYPE_INT_RGB);
        graphics_vista_previa = vista_previa.createGraphics();
        graphics_vista_previa.setPaint(new Color(255, 255, 255));
        graphics_vista_previa.fillRect(0, 0, 800, 100);
        
        cuadrado_vista_previa = new Cuadrado();
        cuadrado_vista_previa.setDegradado(degradado_defecto);
        cuadrado_vista_previa.setX(325 - (getGrosor()/2));
        cuadrado_vista_previa.setY(60 - (getGrosor()/2));
        cuadrado_vista_previa.setWidth(grosor_defecto);
        cuadrado_vista_previa.setHeight(grosor_defecto);
        cuadrado_vista_previa.setColor1(color1_defecto);
        cuadrado_vista_previa.setColor2(color2_defecto);
        cuadrado_vista_previa.setGrosor(grosor_defecto);       
    }
    public void vaciarColeccionFiguras(){
        undoFiguras.clear();
    }
    public boolean guardarDibujo() {
        boolean guardado;
        try {
            if(!nombre_archivo.contains(FORMATO_IMAGEN))
                nombre_archivo = nombre_archivo.concat(".".concat(FORMATO_IMAGEN));
            
            archivo = new File(nombre_archivo);
            guardado = ImageIO.write(dibujo, FORMATO_IMAGEN, archivo);
        } 
        catch (IOException e) {
            System.out.println("Motivo: " + e.getLocalizedMessage());
            guardado = false;
        }
        return guardado;
    }
    
    public boolean cargarDibujo() {
        boolean cargado;
        try {
            this.dibujo = ImageIO.read(archivo);
            cargado = true;
        }
        catch (Exception e) {
            System.out.println("Motivo: " + e.getLocalizedMessage());
            cargado = false;
        }
        return cargado;
    }
/********************FIN METODOS DEL DIBUJO SKETCHER **************************/

}
