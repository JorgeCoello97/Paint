package practicafinal.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionDegradado extends JPanel
{
    private Model model;
    private final ConfiguracionDegradadoTitulo configuracionDegradadoTitulo;
    private final ConfiguracionDegradadoOpcion configuracionDegradadoOpcion;
    
    public ConfiguracionDegradado(Model model) {
        this.model = model;
        
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "DEGRADADO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(30, 10, 10, 10)));
        
        configuracionDegradadoTitulo = new ConfiguracionDegradadoTitulo();
        configuracionDegradadoOpcion = new ConfiguracionDegradadoOpcion();
        
        add(configuracionDegradadoTitulo);
        add(configuracionDegradadoOpcion);
    }
    class ConfiguracionDegradadoTitulo extends JPanel
    {
        private final JLabel label_titulo;

        public ConfiguracionDegradadoTitulo() {
            setLayout(new FlowLayout());
            label_titulo = new JLabel("¿Desea usted degradado?");
            label_titulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            add(label_titulo);
        } 
    }
    
    class ConfiguracionDegradadoOpcion extends JPanel
    {
        private final JRadioButton radio_button_si, radio_button_no;
        private final ButtonGroup group;

        public ConfiguracionDegradadoOpcion() {
            setLayout(new FlowLayout());
            
            radio_button_si = new JRadioButton("SI");
            radio_button_si.setSelected(false);
            radio_button_si.setActionCommand("radioButtonSi");
            
            radio_button_no = new JRadioButton("NO");
            radio_button_no.setSelected(false);
            radio_button_no.setActionCommand("radioButtonNo");
            
            if(model.isDegradadoDefecto())
                radio_button_si.setSelected(true);
            else
                radio_button_no.setSelected(true);
            
            group = new ButtonGroup();
            group.add(radio_button_si);
            group.add(radio_button_no);
            
            add(radio_button_si);
            add(radio_button_no);
        }
        public void setActionListener(ActionListener actionListener){
            radio_button_si.addActionListener(actionListener);
            radio_button_no.addActionListener(actionListener);
        }
    }
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        configuracionDegradadoOpcion.setActionListener(actionListener);
    }
}
