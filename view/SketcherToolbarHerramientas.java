package practicafinal.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherToolbarHerramientas extends JToolBar{
    private final JButton button_dibujar, button_borrar, button_nuevo;
    private final JButton button_rehacer, button_deshacer;
    private final JButton button_guardar, button_cargar, button_config;
    private final JButton button_ayuda, button_restablecer;

    public SketcherToolbarHerramientas() {
        super("HERRAMIENTAS");
        setBorderPainted(true);
        setFloatable(true);
        setRollover(true);
        setOrientation(JToolBar.VERTICAL);
        button_dibujar = addButtons("Dibujar", "lapiz", "Dibujar(Alt + X)");
        button_dibujar.setMnemonic(KeyEvent.VK_X);
        add(button_dibujar);
        
        button_borrar = addButtons("Borrar", "borrador", "Borrar(Alt + Z)");
        button_borrar.setMnemonic(KeyEvent.VK_Z);
        add(button_borrar);
        
        button_nuevo = addButtons("Nuevo", "nuevo_dibujo", "Nuevo dibujo(Alt + N)");
        button_nuevo.setMnemonic(KeyEvent.VK_N);
        add(button_nuevo);
        addSeparator();
        
        button_deshacer = addButtons("Deshacer", "deshacer_dibujo", "Deshacer acción(Alt + D)");
        button_deshacer.setMnemonic(KeyEvent.VK_D);
        add(button_deshacer);

        button_rehacer = addButtons("Rehacer", "rehacer_dibujo", "Rehacer acción(Alt + F)");
        button_rehacer.setMnemonic(KeyEvent.VK_F);
        add(button_rehacer);
        addSeparator();
        
        button_guardar = addButtons("Guardar", "guardar", "Guardar archivo(Alt + S)");
        button_guardar.setMnemonic(KeyEvent.VK_S);
        add(button_guardar);

        button_cargar = addButtons("Cargar", "cargar", "Cargar archivo(Alt + L)");
        button_cargar.setMnemonic(KeyEvent.VK_L);
        add(button_cargar);
        addSeparator();
            
        button_ayuda = addButtons("Ayuda", "ayuda", "Informacion y ayuda(Alt + H)");
        button_ayuda.setMnemonic(KeyEvent.VK_H);
        add(button_ayuda);
        
        button_config = addButtons("Configuracion", "configuracion", "Configuración por defecto(Alt + C)");
        button_config.setMnemonic(KeyEvent.VK_C);
        add(button_config);
        addSeparator();
        
        button_restablecer = addButtons("Restablecer", "restablecer", "Restablece los valores por defecto(Alt + R)");
        button_restablecer.setMnemonic(KeyEvent.VK_R);
        add(button_restablecer);
        
        button_rehacer.setEnabled(false);
        button_deshacer.setEnabled(false);
    }
    
    //GETTERS
    public JButton getButtonRehacer() {
        return button_rehacer;
    }
    public JButton getButtonDeshacer() {
        return button_deshacer;
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
    
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        button_dibujar.addActionListener(actionListener);
        button_borrar.addActionListener(actionListener);
        button_nuevo.addActionListener(actionListener);
        button_deshacer.addActionListener(actionListener);
        button_rehacer.addActionListener(actionListener);
        button_guardar.addActionListener(actionListener);
        button_cargar.addActionListener(actionListener);
        button_ayuda.addActionListener(actionListener);
        button_config.addActionListener(actionListener);
        button_restablecer.addActionListener(actionListener);
    }
}
