package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionGrosor extends JPanel {
    private Model model;
    private final ConfiguracionGrosorTitulo configuracionGrosorTitulo;
    private final ConfiguracionGrosorSlider configuracionGrosorSlider;
    
    public ConfiguracionGrosor(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("GROSOR"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        configuracionGrosorTitulo = new ConfiguracionGrosorTitulo();
        configuracionGrosorSlider = new ConfiguracionGrosorSlider();
        
        add(configuracionGrosorTitulo, BorderLayout.NORTH);
        add(configuracionGrosorSlider, BorderLayout.CENTER);
    }
    
    class ConfiguracionGrosorTitulo extends JPanel {
        private JLabel label_titulo;

        public ConfiguracionGrosorTitulo() {
            setLayout(new FlowLayout());
            setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            label_titulo = new JLabel("¿Como desea la anchura?");
            label_titulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            add(label_titulo);
        }
        
    }
    
    class ConfiguracionGrosorSlider extends JPanel {
        private final JSlider slider;
        private Hashtable hashtable;

        public ConfiguracionGrosorSlider() {
            setLayout(new BorderLayout());
            
            slider = new JSlider(0, 100, model.getGrosorDefecto());
            
            hashtable = new Hashtable();
            
            for(int i = 0; i <= 100; i += 5)
                hashtable.put(i, new JLabel(String.valueOf(i)));
            
            slider.setLabelTable(hashtable);
            slider.setPaintLabels(true);
            slider.setPaintTicks(true);
            
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(5);
            slider.setSnapToTicks(true);
            add(slider);
        }
        //LISTENERS
        public void setChangeListener(ChangeListener changeListener) {
            slider.addChangeListener(changeListener);
        }
    }
    //LISTENERS
    public void setChangeListenerSlider(ChangeListener cl) {
        configuracionGrosorSlider.setChangeListener(cl);
    }
    public void setChangeListener(ChangeListener changeListener) {
        configuracionGrosorSlider.setChangeListener(changeListener);
    }
}
