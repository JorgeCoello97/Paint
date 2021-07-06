package practicafinal.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuFiguras extends JPanel{
    private Model model;
    private SketcherMenuFigurasTitulo titulo;
    private SketcherMenuFigurasOpciones opciones;
    
    public SketcherMenuFiguras(Model model) {
        this.model = model;
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        setLayout(new GridLayout(2,1));
        
        titulo = new SketcherMenuFigurasTitulo();
        opciones = new SketcherMenuFigurasOpciones();
        
        add(titulo);
        add(opciones);
        
    }
    class SketcherMenuFigurasTitulo extends JPanel
    {
        private final JLabel label_titulo;
        public SketcherMenuFigurasTitulo() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            
            label_titulo = new JLabel("FIGURAS");
            
            add(label_titulo);
        }
    }
    class SketcherMenuFigurasOpciones extends JPanel
    {
        private final JComboBox<String> comboBox_Figuras;
        //FIGURAS
        private String[] nombre_figuras = {"LINEA","CIRCULO","CUADRADO"};
        
        public SketcherMenuFigurasOpciones() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            
            comboBox_Figuras = new JComboBox(nombre_figuras);
            comboBox_Figuras.setActionCommand("comboBoxFigurasSketcher");
            comboBox_Figuras.setToolTipText("Elige el tipo de figura.");
            
            add(comboBox_Figuras);
        }
        public void seleccionarFigura(int opcion)
        {
            comboBox_Figuras.setSelectedIndex(opcion);
        }
        public int getSeleccionFigura()
        {
            return comboBox_Figuras.getSelectedIndex();
        }
        public void setActionListener(ActionListener actionListener)
        {
            comboBox_Figuras.addActionListener(actionListener);
        }
    }
    //SETTERS
    public void seleccionarFigura(int opcion)
    {
        opciones.seleccionarFigura(opcion);
    }
    public int getSeleccionFigura()
    {
        return opciones.getSeleccionFigura();
    }
    
    //LISTENER
    public void setActionListener(ActionListener actionListener)
    {
        opciones.setActionListener(actionListener);
    }
}
