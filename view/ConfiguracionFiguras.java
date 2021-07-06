package practicafinal.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionFiguras extends JPanel{
    private ConfiguracionFigurasOpciones opciones;
        
    public ConfiguracionFiguras() {
        setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder(null, "FIGURAS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
        BorderFactory.createEmptyBorder(30, 10, 10, 10)));
        
        setLayout(new FlowLayout());
            
        opciones = new ConfiguracionFigurasOpciones();
        
        add(opciones);  
    }
    class ConfiguracionFigurasOpciones extends JPanel
    {
        private final JComboBox<String> comboBox_Figuras;
        //FIGURAS
        private String[] nombre_figuras = {"LINEA","CIRCULO","CUADRADO"};
    
        public ConfiguracionFigurasOpciones() {
                setLayout(new FlowLayout(FlowLayout.CENTER));
            
            comboBox_Figuras = new JComboBox(nombre_figuras);
            comboBox_Figuras.setActionCommand("comboBoxFigurasConfiguracion");
            comboBox_Figuras.setToolTipText("Elige el tipo de figura.");
            
            add(comboBox_Figuras);
        }
        public void seleccionarFigura(int opcion){
            comboBox_Figuras.setSelectedIndex(opcion);
        }
        public int getSeleccionFigura(){
            return comboBox_Figuras.getSelectedIndex();
        }
        //LISTENERS
        public void setActionListener(ActionListener actionListener){
            comboBox_Figuras.addActionListener(actionListener);
        }
    }
    public void seleccionarFigura(int opcion){
        opciones.seleccionarFigura(opcion);
    }
    public int getSeleccionFigura(){
        return opciones.getSeleccionFigura();
    }
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        opciones.setActionListener(actionListener);
    }
}
