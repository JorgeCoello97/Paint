package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */

public class Configuracion2Colores extends JPanel{
    private Model model;
    private final ConfiguracionColoresColor configuracionColoresColor1, configuracionColoresColor2;

    public Configuracion2Colores(Model model) {
        this.model = model;
        setLayout(new GridLayout(1, 2));
        
        configuracionColoresColor1 = new ConfiguracionColoresColor(model.getColor1Defecto());
        configuracionColoresColor2 = new ConfiguracionColoresColor(model.getColor2Defecto());
        
        add(configuracionColoresColor1);
        add(configuracionColoresColor2);
    }
    
    class ConfiguracionColoresColor extends JPanel{
        private ConfiguracionColoresColorCuerpo configuracionColoresColorCuerpo;

        public ConfiguracionColoresColor(Color color) {
            setLayout(new BorderLayout());
            configuracionColoresColorCuerpo = new ConfiguracionColoresColorCuerpo(color);
            add(configuracionColoresColorCuerpo);
        }
        class ConfiguracionColoresColorCuerpo extends JPanel{
            private final JColorChooser colorChooser;

            public ConfiguracionColoresColorCuerpo(Color color) {
                setLayout(new BorderLayout());
            
                colorChooser = new JColorChooser(color);
                colorChooser.setPreviewPanel(new JPanel());
            
                AbstractColorChooserPanel[] Panels = colorChooser.getChooserPanels();
                String name = Panels[0].getDisplayName();

                for (AbstractColorChooserPanel panel : Panels) {
                    if (!panel.getDisplayName().equals(name))
                        colorChooser.removeChooserPanel(panel);
                }
                add(colorChooser);
            }
            public void setChangeListener(ChangeListener cl){
                colorChooser.getSelectionModel().addChangeListener(cl);
            }
        }
        public void setChangeListener(ChangeListener cl){
            configuracionColoresColorCuerpo.setChangeListener(cl);
        }
    }
    //LISTENERS
    public void setChangeListener1(ChangeListener cl){
            configuracionColoresColor1.setChangeListener(cl);
    }
    public void setChangeListener2(ChangeListener cl){
            configuracionColoresColor2.setChangeListener(cl);
    }
}
