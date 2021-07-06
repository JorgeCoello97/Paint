package practicafinal.view;

import java.awt.FlowLayout;
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
public class SketcherMenuDegradado extends JPanel{
    private Model model;
    private SketcherMenuDegradadoTitulo titulo;
    private SketcherMenuDegradadoOpciones opciones;
    
    public SketcherMenuDegradado(Model model) {
        this.model = model;
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        setLayout(new GridLayout(2,1));
        
        titulo = new SketcherMenuDegradadoTitulo();
        opciones = new SketcherMenuDegradadoOpciones();
        
        add(titulo);
        add(opciones);   
    }
    class SketcherMenuDegradadoTitulo extends JPanel
    {
        private final JLabel label_titulo;
        public SketcherMenuDegradadoTitulo() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            
            label_titulo = new JLabel("DEGRADADO");
            
            add(label_titulo);
        }
    }
    class SketcherMenuDegradadoOpciones extends JPanel
    {
        private final JRadioButton radioButtonSi, radioButtonNo;
        private ButtonGroup group;
        public SketcherMenuDegradadoOpciones() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            
            group = new ButtonGroup();
            
            radioButtonSi = new JRadioButton("Si");
            radioButtonSi.setSelected(false);
            radioButtonSi.setToolTipText("Si, con degradado.");
            radioButtonSi.setActionCommand("radioButtonSiSketcher");
            
            radioButtonNo = new JRadioButton("No");
            radioButtonNo.setSelected(false);          
            radioButtonNo.setToolTipText("No, sin degradado.");
            radioButtonNo.setActionCommand("radioButtonNoSketcher");
            
            group.add(radioButtonSi);
            group.add(radioButtonNo);
            
            if(model.isDegradadoDefecto())
                radioButtonSi.setSelected(true);
            else
                radioButtonNo.setSelected(true);
            
            add(radioButtonSi);
            add(radioButtonNo);
        }

        public void setOpcionDegradado(boolean opcion) {
            if(opcion == true)
                radioButtonSi.setSelected(true);
            else
                radioButtonNo.setSelected(true);
        }
        //LISTENERS
        public void setActionListener(ActionListener actionListener){
            radioButtonSi.addActionListener(actionListener);
            radioButtonNo.addActionListener(actionListener);
        }
    }
    //SETTERS
    public void setOpcionDegradado(boolean opcion){
        opciones.setOpcionDegradado(opcion);
    }
    //LISTENER
    public void setActionListener(ActionListener actionListener){
        opciones.setActionListener(actionListener);
    }
}
