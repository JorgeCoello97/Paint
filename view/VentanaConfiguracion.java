package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class VentanaConfiguracion extends JFrame{
    private Model model;
    private final ConfiguracionDegradadoVistaPrevia configuracionDegradadoVistaPrevia;
    private final ConfiguracionColores configuracionColores;
    private final ConfiguracionGrosor configuracionGrosor;
    private final ConfiguracionAceptar configuracionAceptar;
    private final MenuBar menuBar;
    
    public VentanaConfiguracion(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        setTitle("Configuración por defecto");
        setResizable(false);
        
        configuracionDegradadoVistaPrevia = new ConfiguracionDegradadoVistaPrevia(model);
        configuracionColores = new ConfiguracionColores(model);
        configuracionGrosor = new ConfiguracionGrosor(model);
        configuracionAceptar = new ConfiguracionAceptar();
        
        menuBar = new MenuBar();
        setJMenuBar(menuBar);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        
        add(configuracionDegradadoVistaPrevia, BorderLayout.NORTH);
        add(configuracionColores, BorderLayout.CENTER);
        add(configuracionGrosor, BorderLayout.SOUTH);
        add(configuracionAceptar, BorderLayout.EAST);
    }
    class ConfiguracionAceptar extends JPanel{
        private final JButton button_aceptar;
        
        public ConfiguracionAceptar() {
            setLayout(new BorderLayout());
            
            button_aceptar = addButtons("Aceptar", "aceptar","Confirmar configuración.");
            button_aceptar.setContentAreaFilled(false);
            button_aceptar.setActionCommand("buttonAceptar");

            add(button_aceptar, BorderLayout.CENTER);
        }
        public void setActionListener(ActionListener actionListener){
            button_aceptar.addActionListener(actionListener);
        }
    }
    
    //LISTENERS♦
    public void setChangeListenerSlider(ChangeListener changeListener) {
        configuracionGrosor.setChangeListenerSlider(changeListener);
    }
    
    public void setChangeListenerColor1(ChangeListener changeListener) {
        configuracionColores.setChangeListenerColor1(changeListener);
    }
    
    public void setChangeListenerColor2(ChangeListener changeListener) {
        configuracionColores.setChangeListenerColor2(changeListener);
    }
    
    public void setChangeListenerColor(ChangeListener changeListener) {
        configuracionColores.setChangeListenerColor(changeListener);
    }
    
    public void setActionListener(ActionListener actionListener){
        configuracionDegradadoVistaPrevia.setActionListener(actionListener);
        configuracionAceptar.setActionListener(actionListener);
        menuBar.setActionListener(actionListener);
    }
    
    //METODOS
    public void MostrarColores() {
        configuracionColores.MostrarPaletaColores();
    }
    
    public void MostrarColor() {
        configuracionColores.MostrarPaletaColor();
    }

    public void RepintarVistaPrevia() {
        configuracionDegradadoVistaPrevia.RepintarVistaPrevia();
    }
    
    public void seleccionarFigura(int opcion) {
        configuracionDegradadoVistaPrevia.seleccionarFigura(opcion);
    }
    public int getSeleccionFigura() {
        return configuracionDegradadoVistaPrevia.getSeleccionFigura();
    }
    
    public JButton addButtons(String nameButton, String imageName, String toolTip) {
        String imgLocation = "../images/"+imageName+".png";
        URL imagenURL = VentanaSketcher.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand("button".concat(nameButton));
        button.setToolTipText(toolTip);
        if(imagenURL != null)
            button.setIcon(new ImageIcon(imagenURL));
        else
            button.setText(nameButton);
        return button;
    }
}