package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherPopUpDialog extends JDialog{
    private Model model;
    private final PopUpColor popUpColor;
    private final PopUpColorOpciones opciones;

    public SketcherPopUpDialog(Model model) {
        this.model = model;
        setTitle("ELIGE UN COLOR");
        setLayout(new BorderLayout());
        setVisible(false);
        setSize(550, 300);
        setResizable(false);
        popUpColor = new PopUpColor(model.getColor1Defecto());
        opciones = new PopUpColorOpciones();
        
        add(popUpColor, BorderLayout.CENTER);
        add(opciones, BorderLayout.SOUTH);
    }
    class PopUpColor extends JPanel{
        private final JColorChooser colorChooser;
        
        public PopUpColor(Color color) {
            setLayout(new BorderLayout());
            
            colorChooser = new JColorChooser(color);
            colorChooser.setPreviewPanel(new JPanel());
            
            AbstractColorChooserPanel[] Panels = colorChooser.getChooserPanels();
            String name = Panels[0].getDisplayName();

            for (AbstractColorChooserPanel panel : Panels) {
                if (!panel.getDisplayName().equals(name))
                colorChooser.removeChooserPanel(panel);
            }           
            add(colorChooser, BorderLayout.CENTER);
        }
        public void setChangeListener(ChangeListener changeListener){
            colorChooser.getSelectionModel().addChangeListener(changeListener);
        }
    }
    class PopUpColorOpciones extends JPanel
    {
        private final JButton buttonAceptar, buttonCancelar;

        public PopUpColorOpciones() {
            setLayout(new GridLayout(1, 2));
            buttonAceptar = new JButton("ACEPTAR");
            buttonAceptar.setActionCommand("buttonAceptarPopUp");
            buttonCancelar = new JButton("CANCELAR");
            buttonCancelar.setActionCommand("buttonCancelarPopUp");
            
            add(buttonAceptar);
            add(buttonCancelar);
        }
        public void setActionListener(ActionListener actionListener){
            buttonAceptar.addActionListener(actionListener);
            buttonCancelar.addActionListener(actionListener);
        }
    }
    
    //LISTENER
    public void setChangeListener(ChangeListener changeListener){
        popUpColor.setChangeListener(changeListener);
    }
    public void setActionListener(ActionListener actionListener){
        opciones.setActionListener(actionListener);
    }   
}
