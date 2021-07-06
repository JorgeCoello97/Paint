package practicafinal.view;

import java.awt.GridLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherAyudaDialog extends JDialog{
    private final JButton button_dibujar, button_borrar, button_nuevo;
    private final JButton button_rehacer, button_deshacer;
    private final JButton button_guardar, button_cargar, button_config;
    private final JButton button_ayuda, button_restablecer;
    private final JTextField fieldDibujar, fieldBorrar, fieldNuevo;
    private final JTextField fieldRehacer, fieldDeshacer;
    private final JTextField fieldGuardar, fieldCargar, fieldConfig;
    private final JTextField fieldAyuda, fieldRestablecer;


    public SketcherAyudaDialog() {
        setLayout(new GridLayout(10, 2));
        setVisible(false);
        setResizable(false);
        setTitle("Informacion y ayuda para el usuario");
        setSize(600, 600);

        button_dibujar = addButtons("Dibujar", "lapiz", "Dibujar");
        button_dibujar.setEnabled(false);
        add(button_dibujar);
        
        fieldDibujar = new JTextField("Dibujar con los valores escogidos (Alt + X)");
        fieldDibujar.setEditable(false);
        fieldDibujar.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldDibujar);
        
        button_borrar = addButtons("Borrar", "borrador", "Borrar(Alt + Z)");
        button_borrar.setEnabled(false);
        add(button_borrar);
        
        fieldBorrar = new JTextField("Borrar con el grosor indicado (Alt + Z)");
        fieldBorrar.setEditable(false);
        fieldBorrar.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldBorrar);
        
        
        button_nuevo = addButtons("Nuevo", "nuevo_dibujo", "Nuevo archivo(Alt + N)");
        button_nuevo.setEnabled(false);
        add(button_nuevo);
        
        fieldNuevo = new JTextField("Limpia el lienzo por completo (Alt + N)");
        fieldNuevo.setEditable(false);
        fieldNuevo.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldNuevo);
        
        button_deshacer = addButtons("Deshacer", "deshacer_dibujo", "Deshacer acción(Alt + D)");
        button_deshacer.setEnabled(false);
        add(button_deshacer);
        
        fieldDeshacer = new JTextField("Deshacer ultima accion realizada (Alt + D)");
        fieldDeshacer.setEditable(false);
        fieldDeshacer.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldDeshacer);

        button_rehacer = addButtons("Rehacer", "rehacer_dibujo", "Rehacer acción(Alt + F)");
        button_rehacer.setEnabled(false);
        add(button_rehacer);
        
        fieldRehacer = new JTextField("Rehacer ultima accion realizada (Alt + F)");
        fieldRehacer.setEditable(false);
        fieldRehacer.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldRehacer);
        
        button_guardar = addButtons("Guardar", "guardar", "Guardar archivo(Alt + S)");
        button_guardar.setEnabled(false);
        add(button_guardar);
        
        fieldGuardar = new JTextField("Guardar archivo en formato JPG (Alt + S)");
        fieldGuardar.setEditable(false);
        fieldGuardar.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldGuardar);

        button_cargar = addButtons("Cargar", "cargar", "Cargar archivo(Alt + L)");
        button_cargar.setEnabled(false);
        add(button_cargar);
                
        fieldCargar = new JTextField("Cargar archivo (Alt + L)");
        fieldCargar.setEditable(false);
        fieldCargar.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldCargar);        
            
        button_ayuda = addButtons("Ayuda", "ayuda", "Informacion y ayuda(Alt + H)");
        button_ayuda.setEnabled(false);
        add(button_ayuda);
                
        fieldAyuda = new JTextField("Esta ventana; Informacion y ayuda (Alt + H)");
        fieldAyuda.setEditable(false);
        fieldAyuda.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldAyuda);
        
        button_config = addButtons("Configuracion", "configuracion", "Configuración por defecto(Alt + C)");
        button_config.setEnabled(false);
        add(button_config);
        
        fieldConfig = new JTextField("Configuracion para cambiar los valores por defecto (Alt + C)");
        fieldConfig.setEditable(false);
        fieldConfig.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldConfig);

        button_restablecer = addButtons("Restablecer", "restablecer", "Restablece los valores por defecto(Alt + R)");
        button_restablecer.setEnabled(false);
        add(button_restablecer);
        
        fieldRestablecer = new JTextField("Restablece los valores a por defecto (Alt + R)");
        fieldRestablecer.setEditable(false);
        fieldRestablecer.setHorizontalAlignment(SwingConstants.CENTER);
        add(fieldRestablecer);
        
        button_rehacer.setEnabled(false);
        button_deshacer.setEnabled(false);  
    }
    //METODOS UTILES
    public JButton addButtons(String nameButton, String imageName, String toolTip) {
        String imgLocation = "../images/"+imageName+".png";
        URL imagenURL = VentanaSketcher.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand("button".concat(nameButton));
        if(imagenURL != null)
            button.setIcon(new ImageIcon(imagenURL));
        else
            button.setText(nameButton);
        
        button.setToolTipText(toolTip);
        return button;
    }
}