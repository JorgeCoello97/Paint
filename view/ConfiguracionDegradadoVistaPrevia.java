package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionDegradadoVistaPrevia extends JPanel{
    private Model model;
    private final ConfiguracionDegradado configuracionDegradado;
    private final ConfiguracionVistaPrevia configuracionVistaPrevia;
    private final ConfiguracionFiguras configuracionFiguras;
    
    public ConfiguracionDegradadoVistaPrevia(Model model) {
        this.model = model;
        
        setLayout(new BorderLayout(10, 10));

        configuracionDegradado = new ConfiguracionDegradado(model);
        configuracionFiguras = new ConfiguracionFiguras();
        configuracionVistaPrevia = new ConfiguracionVistaPrevia();
        
        add(configuracionDegradado, BorderLayout.WEST);
        add(configuracionFiguras, BorderLayout.EAST);
        add(configuracionVistaPrevia, BorderLayout.CENTER);
    }

    class ConfiguracionVistaPrevia extends JPanel
    {
        private final DibujoPanel dibujoPanel;

        public ConfiguracionVistaPrevia() {
            setLayout(new BorderLayout());        
            
            setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "VISTA PREVIA", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)));
            
            dibujoPanel = new DibujoPanel();
            
            //TOOLTIP VISTA PREVIA
            dibujoPanel.setToolTipText("Vista previa de la configuración utilizada actualmente.");
            
            add(dibujoPanel, BorderLayout.CENTER);
        }
        
        public void RepintarVistaPrevia() {
            dibujoPanel.repaint();
        }
    }    
    class DibujoPanel extends JPanel{
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gd = (Graphics2D) g;
            gd.drawImage(model.getVistaPrevia(), 0, 0, null);
        }
    }
    
    //LISTENER
    public void setActionListener(ActionListener actionListener) {
        configuracionDegradado.setActionListener(actionListener);
        configuracionFiguras.setActionListener(actionListener);
    }
    
    //METODOS
    public void RepintarVistaPrevia() {
        configuracionVistaPrevia.repaint();
    }
    public void seleccionarFigura(int opcion) {
        configuracionFiguras.seleccionarFigura(opcion);
    }
    public int getSeleccionFigura() {
        return configuracionFiguras.getSeleccionFigura();
    }

}
