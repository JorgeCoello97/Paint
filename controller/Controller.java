package practicafinal.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.colorchooser.DefaultColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import practicafinal.model.Circulo;
import practicafinal.model.Linea;
import practicafinal.model.Model;
import practicafinal.model.Cuadrado;
import practicafinal.util.LoadImage;
import practicafinal.util.SaveImage;
import practicafinal.view.ConfiguracionConfirmacionDialog;
import practicafinal.view.SketcherAyudaDialog;
import practicafinal.view.SketcherPopUpDialog;
import practicafinal.view.VentanaConfiguracion;
import practicafinal.view.VentanaSketcher;

/*
 *
 * @author Yannick & Jorge
 */
public class Controller {
    private Model model;
    private VentanaSketcher ventanaSketcher;
    private VentanaConfiguracion ventanaConfiguracion;
    private ConfiguracionConfirmacionDialog confirmacionDialog;
    private SketcherPopUpDialog popUpColor1, popUpColor2;
    private SketcherAyudaDialog ayudaDialog;
    private boolean dibujarActivo = true;
    private boolean asignarValores = true;
    private SaveImage saveImage;
    private LoadImage loadImage;
    private Color color;
    
    public Controller(VentanaSketcher ventanaSketcher, VentanaConfiguracion ventanaConfiguracion, Model model) {
        this.ventanaSketcher = ventanaSketcher;
        this.ventanaConfiguracion = ventanaConfiguracion;
        this.model = model;
        
        saveImage = new SaveImage();
        loadImage = new LoadImage();
        
        color = new Color(0, 0, 0);
        
        ventanaConfiguracion.setActionListener(new ControllerActionListenerConfiguracion());
        ventanaConfiguracion.setChangeListenerSlider(new ControllerChangeListenerConfiguracion("JSLIDER"));
        ventanaConfiguracion.setChangeListenerColor(new ControllerChangeListenerConfiguracion("JCOLORCHOOSER_1"));
        
        ventanaConfiguracion.setChangeListenerColor1(new ControllerChangeListenerConfiguracion("JCOLORCHOOSER_1"));
        ventanaConfiguracion.setChangeListenerColor2(new ControllerChangeListenerConfiguracion("JCOLORCHOOSER_2"));
        
        ventanaSketcher.setActionListener(new ControllerActionListenerSketcher());
        ventanaSketcher.setMouseListener(new ControllerMouseListener());
        ventanaSketcher.setChangeListener(new ControllerChangeListenerSketcher());
        ventanaSketcher.setMouseMotionListener(new ControllerMouserMotionListener());
        
        confirmacionDialog = new ConfiguracionConfirmacionDialog(model);
        confirmacionDialog.setActionListener(new ControllerActionListenerConfiguracion());
        confirmacionDialog.setWindowListener(new ControllerWindowListenerConfiguracionConfirmacion());
        
        popUpColor1 = new SketcherPopUpDialog(model);
        popUpColor1.setActionListener(new ControllerActionListenerPopUp("JBUTTONS_1"));
        popUpColor1.setChangeListener(new ControllerChangeListenerPopUp("JCOLORCHOOSER_1"));
        popUpColor1.addWindowListener(new ControllerWindowListenerPopUp("POPUP_COLOR_1"));
        
        popUpColor2 = new SketcherPopUpDialog(model);
        popUpColor2.setActionListener(new ControllerActionListenerPopUp("JBUTTONS_2"));
        popUpColor2.setChangeListener(new ControllerChangeListenerPopUp("JCOLORCHOOSER_2"));
        popUpColor2.addWindowListener(new ControllerWindowListenerPopUp("POPUP_COLOR_2"));
        
        ayudaDialog = new SketcherAyudaDialog();
    
    }
    class ControllerWindowListenerConfiguracionConfirmacion extends WindowAdapter
    {    
        @Override
        public void windowClosing(WindowEvent e) {
            confirmacionDialog.setVisible(false);
            ventanaConfiguracion.setVisible(true);
        }
    }
    class ControllerWindowListenerPopUp extends WindowAdapter
    {   
        private String tipo_componente;

        public ControllerWindowListenerPopUp(String tipo_componente) {
            this.tipo_componente = tipo_componente;
        }
        
        @Override
        public void windowClosing(WindowEvent e) {
            if(tipo_componente.equals("POPUP_COLOR_1"))
            {
                popUpColor1.setVisible(false);
            }else if (tipo_componente.equals("POPUP_COLOR_2"))
            {
                popUpColor2.setVisible(false);
            }
        }
    }
    class ControllerActionListenerConfiguracion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            switch(command){
                case "radioButtonSi":
                    model.setDegradadoDefecto(true);
                    ventanaConfiguracion.MostrarColores();
                    confirmacionDialog.setEstadoDegradadoSi();
                    confirmacionDialog.setVisibleColor2(true);
                    
                    model.getLineaVistaPrevia().setDegradado(true);
                    model.getCirculoVistaPrevia().setDegradado(true);
                    model.getCuadradoVistaPrevia().setDegradado(true);
                   
                    if(model.getFiguraActualDefecto()== model.FIGURA_LINEA){
                        model.pintarLineaVistaPrevia();
                    }
                    else if(model.getFiguraActualDefecto()== model.FIGURA_CIRCULO){
                        model.pintarCirculoVistaPrevia();
                    }
                    else if(model.getFiguraActualDefecto()== model.FIGURA_CUADRADO){
                        model.pintarCuadradoVistaPrevia();
                    }
                    ventanaConfiguracion.RepintarVistaPrevia();
                    break;
                case "radioButtonNo":
                    model.setDegradadoDefecto(false);
                    ventanaConfiguracion.MostrarColor();
                    confirmacionDialog.setEstadoDegradadoNo();
                    confirmacionDialog.setVisibleColor2(false);
                    
                    model.getLineaVistaPrevia().setDegradado(false);
                    model.getCirculoVistaPrevia().setDegradado(false);
                    model.getCuadradoVistaPrevia().setDegradado(false);

                    
                    if(model.getFiguraActualDefecto()== model.FIGURA_LINEA){
                        model.pintarLineaVistaPrevia();
                    }
                    else if(model.getFiguraActualDefecto()== model.FIGURA_CIRCULO){
                        model.pintarCirculoVistaPrevia();
                    }
                    else if(model.getFiguraActualDefecto()== model.FIGURA_CUADRADO){
                        model.pintarCuadradoVistaPrevia();
                    }
                    ventanaConfiguracion.RepintarVistaPrevia();
                    break;
                case "comboBoxFigurasConfiguracion":
                    int figura = ventanaConfiguracion.getSeleccionFigura();
                    model.setFiguraActualDefecto(figura);
                    
                    if(figura == model.FIGURA_LINEA){
                        model.pintarLineaVistaPrevia();
                    }
                    else if(figura == model.FIGURA_CIRCULO){
                        model.pintarCirculoVistaPrevia();
                    }
                    else if(figura == model.FIGURA_CUADRADO){
                        model.pintarCuadradoVistaPrevia();
                    }
                    ventanaConfiguracion.RepintarVistaPrevia();
                    break;
                case "buttonAceptar":
                    confirmacionDialog.setVisible(true);
                    ventanaConfiguracion.setVisible(false);
                    confirmacionDialog.setLocationRelativeTo(null);
                    confirmacionDialog.setFigura(model.getFiguraActualDefecto());
                    break;
                case "buttonConfirmar":
                    confirmacionDialog.setVisible(false);
                    ventanaSketcher.setVisible(true);
                    ventanaSketcher.setLocationRelativeTo(null);
                    if(asignarValores){
                        ayudaDialog.setLocationRelativeTo(null);
                        ayudaDialog.setVisible(true);
                        model.asignarValores();
                        if(model.isDegradadoDefecto())
                        {
                            //RADIO BUTTON SI (SELECCIONADO)
                            ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                                .getMenuDegradado().setOpcionDegradado(true);
                        
                            //MUESTRA EL SEGUNDO COLOR TAMBIÉN
                            ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                    .getMenuColores().MostrarPaletaColores();
                        
                            //MUESTRO LOS COLORES 
                            ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                    .getMenuColores().setColor1(model.getColor1Defecto());
                            ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                    .getMenuColores().setColor2(model.getColor2Defecto());
                        
                        }else
                        {
                            //RADIO BUTTON NO (SELECCIONADO)
                            ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                                 .getMenuDegradado().setOpcionDegradado(false);
                        
                            //SOLO MUESTRO EL PRIMER COLOR
                            ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                    .getMenuColores().MostrarPaletaColor();
                        
                            //MUESTRO EL COLOR
                            ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                    .getMenuColores().setColor1(model.getColor1Defecto());
                        }
                        ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                                .getMenuFiguras().seleccionarFigura(model.getFiguraActual());
                        //MUESTRO EL GROSOR (SELECCIONADO)
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuGrosor().ActualizarSlider(model.getGrosorDefecto());
                    
                        asignarValores = false;
                    } 
                    break;
                case "buttonVolver":
                    ventanaConfiguracion.setVisible(true);
                    confirmacionDialog.setVisible(false);
                    break;
                case "itemSalir":
                    System.exit(0);
                    break;
                case "itemAcercaDe":
                    JOptionPane.showMessageDialog(ventanaConfiguracion, "AUTORES: YANNICK Y JORCH", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }
    class ControllerActionListenerSketcher implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            switch(command){
                case "buttonDibujar":
                    dibujarActivo = true;
                    model.setBorrarActivo(false);
                    break;
                case "buttonBorrar":
                    dibujarActivo = false;
                    ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().reiniciarContadores();                    
                    model.setNumCirculos(0);
                    model.setNumCuadrados(0);
                    model.setNumLineas(0);
                    model.vaciarColeccionFiguras();
                    break;
                case "buttonNuevo":
                    model.nuevoDibujo();
                    ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(false);
                    ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(false);
                    model.reiniciarContadoresFiguras();
                    ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().reiniciarContadores();                    
                    ventanaSketcher.repaint();
                    break;
                case "buttonDeshacer":
                    if(model.deshacerDibujo()){
                        if(!model.isBorrarActivo() && !model.getUndoFiguras().isEmpty()){
                            if(model.getUndoFiguras().peek() == model.FIGURA_LINEA){
                                model.setNumLineas(model.getNumLineas() - 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraLinea(model.getNumLineas());                        
                            }
                            else if(model.getUndoFiguras().peek() == model.FIGURA_CIRCULO){
                                model.setNumCirculos(model.getNumCirculos()- 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraCirculos(model.getNumCirculos());                        
                            }
                            else if(model.getUndoFiguras().peek() == model.FIGURA_CUADRADO){
                                model.setNumCuadrados(model.getNumCuadrados()- 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraCuadrado(model.getNumCuadrados());                        
                            }
                            model.getRedoFiguras().push(model.getUndoFiguras().pop());
                        }
                        else if(model.isBorrarActivo())
                            model.setBorrarActivo(false);
                        
                    ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(true);
                    }
                    else{
                        ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(false);
                    }
                    ventanaSketcher.repaint();
                    break;
                case "buttonRehacer":
                    if(model.rehacerDibujo()){
                        if(!model.isBorrarActivo() && !model.getRedoFiguras().isEmpty()){
                            if(model.getRedoFiguras().peek() == model.FIGURA_LINEA){
                                model.setNumLineas(model.getNumLineas() + 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraLinea(model.getNumLineas());                        
                            }
                            else if(model.getRedoFiguras().peek() == model.FIGURA_CIRCULO){
                                model.setNumCirculos(model.getNumCirculos() + 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraCirculos(model.getNumCirculos());                        
                            }
                            else if(model.getRedoFiguras().peek() == model.FIGURA_CUADRADO){
                                model.setNumCuadrados(model.getNumCuadrados() + 1);
                                ventanaSketcher.getToolbarMenu().getMenuContadorFiguras().
                                    actualizarContadorFiguraCuadrado(model.getNumCuadrados());                        
                            }
                            model.getUndoFiguras().push(model.getRedoFiguras().pop());
                        }
                        ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(true);
                    }
                    else
                        ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(false);
                    ventanaSketcher.repaint();
                    break;
                case "buttonGuardar":
                    String nombreArchivo = "";
                    if(model.getNombreArchivo() == Model.TITULO_POR_DEFECTO)
                    {
                        nombreArchivo = JOptionPane.showInputDialog(
                                ventanaSketcher,
                                "Escribe el nombre de tu dibujo: ", "GUARDAR DIBUJO", 
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        model.setNombreArchivo(nombreArchivo);
                    }
                    if(nombreArchivo != null){
                        if(model.guardarDibujo()){
                            JOptionPane.showMessageDialog(ventanaSketcher, "Guardado con éxito.");
                            ventanaSketcher.setTitle( model.getNombreArchivo() );
                        }
                        else
                            JOptionPane.showMessageDialog(ventanaSketcher, "No se ha podido guardar.");                        
                    }
                    else if(nombreArchivo == null){
                        nombreArchivo = model.TITULO_POR_DEFECTO;
                        model.setNombreArchivo(nombreArchivo);
                    }     
                    break;
                case "buttonCargar":
                    model.setArchivo(loadImage.getFile());
                    model.cargarDibujo();
                    ventanaSketcher.repaint();
                    break;
                case "buttonAyuda":
                    ayudaDialog.setLocationRelativeTo(null);
                    ayudaDialog.setVisible(true);
                    break;
                case "buttonConfiguracion":
                    ventanaConfiguracion.setVisible(true);
                    ventanaSketcher.setVisible(false);
                    break;
                case "buttonRestablecer":
                    model.asignarValores();
                    if(model.isDegradadoDefecto())
                    {
                        //RADIO BUTTON SI (SELECCIONADO)
                        ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                            .getMenuDegradado().setOpcionDegradado(true);
                    
                        //MUESTRA EL SEGUNDO COLOR TAMBIÉN
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().MostrarPaletaColores();
                    
                        //MUESTRO LOS COLORES 
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().setColor1(model.getColor1Defecto());
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().setColor2(model.getColor2Defecto());
                        
                    }
                    else
                    {
                        //RADIO BUTTON NO (SELECCIONADO)
                        ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                                .getMenuDegradado().setOpcionDegradado(false);
                    
                        //SOLO MUESTRO EL PRIMER COLOR
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().MostrarPaletaColor();
                    
                        //MUESTRO EL COLOR
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().setColor1(model.getColor1Defecto());
                    }
                    ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                            .getMenuFiguras().seleccionarFigura(model.getFiguraActual());
                    //MUESTRO EL GROSOR (SELECCIONADO)
                    ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                            .getMenuGrosor().ActualizarSlider(model.getGrosorDefecto());
                    break;
                case "radioButtonSiSketcher":
                    model.setDefectoActivo(false);
                    model.setDegradado(true);
                    ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                            .getMenuDegradado().setOpcionDegradado(true);
                    //ASIGNAMOS EL 2º COLOR POR SI SE HAN ESCOGIDO 2 COLORES EN LA CONFIGURACION POR DEFECTO Y LUEGO CANCELADO EL DEGRADADO
                    //TENIENDO ASI DIFERENTE 2ºCOLOR SIN HABER SIDO ACTUALIZADO
                    ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                            .getMenuColores().setColor2(model.getColor2Defecto());
                    ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                            .getMenuColores().MostrarPaletaColores();
                    break;
                case "radioButtonNoSketcher":
                    model.setDefectoActivo(false);
                    model.setDegradado(false);
                    ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras()
                            .getMenuDegradado().setOpcionDegradado(false);
                    ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                            .getMenuColores().MostrarPaletaColor();
                    break;
                case "comboBoxFigurasSketcher":
                    int figura = ventanaSketcher.getToolbarMenu().getMenuDegradadoFiguras().getMenuFiguras().getSeleccionFigura();
                    model.setFiguraActual(figura);
                    ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuGrosor().ActualizarTitulo(model.getFiguraActual()); 
                    break;
                case "buttonColor1Sketcher":
                    popUpColor1.setVisible(true);
                    popUpColor1.setLocationRelativeTo(null);
                    color = model.getColor1();
                    break;
                case "buttonColor2Sketcher":
                    popUpColor2.setVisible(true);
                    popUpColor2.setLocationRelativeTo(null);
                    color = model.getColor2();
                    break;
                case "itemSalir":
                    System.exit(0);
                    break;
                case "itemAcercaDe":
                    JOptionPane.showMessageDialog(ventanaSketcher, "AUTORES: YANNICK Y JORGE", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }
    class ControllerActionListenerPopUp implements ActionListener
    {
        private String tipo_componente;

        public ControllerActionListenerPopUp(String tipo_componente) {
            this.tipo_componente = tipo_componente;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(tipo_componente.equals("JBUTTONS_1"))
            {
                switch(command)
                {
                    case "buttonAceptarPopUp":
                        popUpColor1.setVisible(false);
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().setColor1(model.getColor1());
                        model.setDefectoActivo(false);
                        break;
                    case "buttonCancelarPopUp":
                        popUpColor1.setVisible(false);
                        model.setColor1(color);
                        break;
                }
            }
            else if(tipo_componente.equals("JBUTTONS_2"))
            {
                switch(command)
                {
                    case "buttonAceptarPopUp":
                        popUpColor2.setVisible(false);
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                                .getMenuColores().setColor2(model.getColor2());
                        model.setDefectoActivo(false);
                        break;
                    case "buttonCancelarPopUp":
                        popUpColor2.setVisible(false);
                        model.setColor2(color);
                        break;
                }
            }
        }
        
    }
    class ControllerChangeListenerPopUp implements ChangeListener{
        private DefaultColorSelectionModel colorSelectionModel;
        private String componente;

        public ControllerChangeListenerPopUp(String componente) {
            this.componente = componente;
        }
        
        @Override
        public void stateChanged(ChangeEvent e) {
            colorSelectionModel = (DefaultColorSelectionModel) e.getSource();
            
            if (componente.equals("JCOLORCHOOSER_1"))
            {
                model.setColor1(colorSelectionModel.getSelectedColor());
            }
            else if(componente.equals("JCOLORCHOOSER_2"))
            {
                model.setColor2(colorSelectionModel.getSelectedColor());
            }
        }
        
    }
    class ControllerChangeListenerConfiguracion implements ChangeListener{
        private JSlider slider;
        private DefaultColorSelectionModel colorSelectionModel;
        private String componente;

        public ControllerChangeListenerConfiguracion(String componente) {
            this.componente = componente;
        }
        
        @Override
        public void stateChanged(ChangeEvent e) {
            
            if(componente.equals("JSLIDER"))
            {
                slider = (JSlider) e.getSource();
                model.setGrosorDefecto(slider.getValue());
                confirmacionDialog.setEstadoGrosor(model.getGrosorDefecto());
                
                model.getLineaVistaPrevia().setGrosor(model.getGrosorDefecto());
                
                model.getCirculoVistaPrevia().setWidth(model.getGrosorDefecto());
                model.getCirculoVistaPrevia().setHeight(model.getGrosorDefecto());
                model.getCirculoVistaPrevia().setGrosor(model.getGrosorDefecto());
                
                model.getCuadradoVistaPrevia().setWidth(model.getGrosorDefecto());
                model.getCuadradoVistaPrevia().setHeight(model.getGrosorDefecto());
                model.getCuadradoVistaPrevia().setGrosor(model.getGrosorDefecto());
            }
            else if (componente.equals("JCOLORCHOOSER_1"))
            {
                colorSelectionModel = (DefaultColorSelectionModel) e.getSource();
                model.setColor1Defecto(colorSelectionModel.getSelectedColor());
                confirmacionDialog.setEstadoColor1(model.getColor1Defecto());
                
                model.getLineaVistaPrevia().setColor1(model.getColor1Defecto());
                
                model.getCirculoVistaPrevia().setColor1(model.getColor1Defecto());
                
                model.getCuadradoVistaPrevia().setColor1(model.getColor1Defecto());                
            }
            else if(componente.equals("JCOLORCHOOSER_2"))
            {
                colorSelectionModel = (DefaultColorSelectionModel) e.getSource();
                model.setColor2Defecto(colorSelectionModel.getSelectedColor());
                confirmacionDialog.setEstadoColor2(model.getColor2Defecto());
                
                model.getLineaVistaPrevia().setColor2(model.getColor2Defecto());
                
                model.getCirculoVistaPrevia().setColor2(model.getColor2Defecto());

                model.getCuadradoVistaPrevia().setColor2(model.getColor2Defecto());                
            }
            if(model.getFiguraActualDefecto()== model.FIGURA_LINEA){
                model.pintarLineaVistaPrevia();
            }
            else if(model.getFiguraActualDefecto()== model.FIGURA_CIRCULO){
                model.pintarCirculoVistaPrevia();
            }
            else if(model.getFiguraActualDefecto()== model.FIGURA_CUADRADO){
                model.pintarCuadradoVistaPrevia();
            }
            ventanaConfiguracion.RepintarVistaPrevia();
        }
        
    }
    class ControllerChangeListenerSketcher implements ChangeListener{
        private JSlider slider;
        
        @Override
        public void stateChanged(ChangeEvent e) {
                slider = (JSlider) e.getSource();
                model.setGrosor(slider.getValue());
                model.setDefectoActivo(false);
        }
        
    }
    class ControllerMouseListener extends MouseAdapter
    {
        private Linea linea;
        private Cuadrado cuadrado;
        private Circulo circulo;
        private boolean click = true;

        @Override
        public void mouseClicked(MouseEvent e) {
            if(dibujarActivo)
            {
                if(model.getFiguraActual() == model.FIGURA_LINEA)
                {
                    if(click)
                    {
                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            linea = new Linea();
                            linea.setOrigen_x(e.getX());
                            linea.setOrigen_y(e.getY());
                            linea.setGrosor(model.getGrosor());
                            linea.setDegradado(model.isDegradado());
                            linea.setColor1(model.getColor1());

                            if(linea.isDegradado())
                                linea.setColor2(model.getColor2());

                            click = false;
                        }                
                    }
                    else
                    {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            linea.setDestino_x(e.getX());
                            linea.setDestino_y(e.getY());

                            model.pintarLinea(linea);

                            model.setNumLineas(model.getNumLineas()+1);
                            ventanaSketcher.getToolbarMenu().getMenuContadorFiguras()
                                .actualizarContadorFiguraLinea(model.getNumLineas());

                            if(!model.isDeshacerActivo())
                                ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(false);

                            ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(true);
                            ventanaSketcher.repaint();


                            click = true;
                        }
                    }
                }
                else if (model.getFiguraActual() == model.FIGURA_CIRCULO)
                {
                    if(e.getButton() == MouseEvent.BUTTON1)
                    {
                        circulo = new Circulo();
                        circulo.setX(e.getX()-(model.getGrosor()/2));
                        circulo.setY(e.getY()-(model.getGrosor()/2));
                        circulo.setDegradado(model.isDegradado());
                        circulo.setWidth(model.getGrosor());
                        circulo.setHeight(model.getGrosor());
                        circulo.setColor1(model.getColor1());

                        if(circulo.isDegradado())
                            circulo.setColor2(model.getColor2());

                        model.pintarCirculo(circulo);
                        model.setNumCirculos(model.getNumCirculos()+1);
                        ventanaSketcher.getToolbarMenu().getMenuContadorFiguras()
                                .actualizarContadorFiguraCirculos(model.getNumCirculos());
                        
                        if(!model.isDeshacerActivo())
                            ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(false);

                        ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(true);
                        ventanaSketcher.repaint();
                    }
                }
                else if (model.getFiguraActual() == model.FIGURA_CUADRADO)
                {
                    if(e.getButton() == MouseEvent.BUTTON1)
                    {
                        cuadrado = new Cuadrado();
                        cuadrado.setX(e.getX()-(model.getGrosor()/2));
                        cuadrado.setY(e.getY()-(model.getGrosor()/2));
                        cuadrado.setDegradado(model.isDegradado());
                        cuadrado.setWidth(model.getGrosor());
                        cuadrado.setHeight(model.getGrosor());
                        cuadrado.setColor1(model.getColor1());

                        if(cuadrado.isDegradado())
                            cuadrado.setColor2(model.getColor2());
                        model.pintarCuadrado(cuadrado);
                        model.setNumCuadrados(model.getNumCuadrados()+1);
                        ventanaSketcher.getToolbarMenu().getMenuContadorFiguras()
                                .actualizarContadorFiguraCuadrado(model.getNumCuadrados());
                        
                        if(!model.isDeshacerActivo())
                            ventanaSketcher.getToolbarHerramientas().getButtonRehacer().setEnabled(false);

                        ventanaSketcher.getToolbarHerramientas().getButtonDeshacer().setEnabled(true);
                        ventanaSketcher.repaint();
                    }
                }    
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {  
            if(dibujarActivo)
            {
                if(e.getButton() == MouseEvent.BUTTON3) {
                    if(model.getGrosor() < 100)
                    {
                        model.setGrosor(model.getGrosor() + 5);
                        ventanaSketcher.getToolbarMenu().getMenuColoresGrosor()
                            .getMenuGrosor().ActualizarSlider(model.getGrosor());
                    }
                }
            }
        }

    }
    class ControllerMouserMotionListener extends MouseMotionAdapter
    {
        @Override
        public void mouseDragged(MouseEvent e) {
            if(dibujarActivo ==  false)
            {
                if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK)
                {
                    model.borrador(e.getX(), e.getY());
                    ventanaSketcher.repaint();
                }
            }
        } 
    }
}
