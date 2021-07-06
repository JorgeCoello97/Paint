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
public class Configuracion1Color extends JPanel{
    private Model model;
    private final ConfiguracionColoresColor configuracionColoresColor;

    public Configuracion1Color(Model model) {
        this.model = model;
        setLayout(new GridLayout(1, 1));
        configuracionColoresColor = new ConfiguracionColoresColor(model.getColor1Defecto());

        add(configuracionColoresColor);
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
    //LISTENER
    public void setChangeListener(ChangeListener cl){
        configuracionColoresColor.setChangeListener(cl);
    }
}

