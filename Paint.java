package practicafinal;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import practicafinal.controller.Controller;
import practicafinal.model.Model;
import practicafinal.view.VentanaConfiguracion;
import practicafinal.view.VentanaSketcher;

/*
 *
 * @author Yannick & Jorge
 */
public class PracticaFinal {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Model model = new Model();
        VentanaConfiguracion ventanaConfiguracion = new VentanaConfiguracion(model);
        VentanaSketcher ventanaSketcher = new VentanaSketcher(model);
        Controller controller = new Controller(ventanaSketcher, ventanaConfiguracion, model);
        
        ventanaConfiguracion.setLocationRelativeTo(null);
        ventanaConfiguracion.setVisible(true);
        ventanaSketcher.setVisible(false);
        

    }
    
}
