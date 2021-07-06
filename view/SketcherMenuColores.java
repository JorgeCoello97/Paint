package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuColores extends JPanel
{
    public static final String CARD_1_PALETA = "PALETA_COLOR";
    public static final String CARD_2_PALETAS = "PALETA_COLORES";
    
    private Model model;
    private CardLayout cardLayout;
    private SketcherMenuColoresUnColor menuColoresUnColor;
    private SketcherMenuColoresDosColores menuColoresDosColores;
    
    public SketcherMenuColores(Model model) {
        this.model = model;
        cardLayout = new CardLayout();
        
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        
        
        setLayout(cardLayout);
        menuColoresUnColor = new SketcherMenuColoresUnColor("COLOR 1", model.getColor1Defecto(), "buttonColor1Sketcher");
        menuColoresDosColores = new SketcherMenuColoresDosColores("COLOR 1", "COLOR 2",
                "buttonColor1Sketcher", "buttonColor2Sketcher");
        add(menuColoresUnColor, CARD_1_PALETA);
        add(menuColoresDosColores, CARD_2_PALETAS);

        if(model.isDegradadoDefecto())
            cardLayout.show(this, CARD_2_PALETAS);
        else
            cardLayout.show(this, CARD_1_PALETA);
    }
    
    class SketcherMenuColoresUnColor extends JPanel
    {
        private final SketcherMenuColoresTitulo coloresTitulo;
        private JButton buttonColor;
        public SketcherMenuColoresUnColor(String titulo, Color color, String actionCommnad) {
            setLayout(new BorderLayout(0,10));
            
            coloresTitulo = new SketcherMenuColoresTitulo(titulo);
            buttonColor = new JButton();
            buttonColor.setBackground(color);
            buttonColor.setToolTipText("Selecciona un color.");
            buttonColor.setContentAreaFilled(false);
            buttonColor.setOpaque(true);
            buttonColor.setBackground(color);
            buttonColor.setActionCommand(actionCommnad);
            
            add(coloresTitulo, BorderLayout.NORTH);
            add(buttonColor, BorderLayout.CENTER);
        }

        public void setColor(Color color) {
            buttonColor.setBackground(color);
        }
        //LISTENERS
        public void setActionListener(ActionListener actionListener){
            buttonColor.addActionListener(actionListener);
        }
    }
    class SketcherMenuColoresDosColores extends JPanel
    {
        private final SketcherMenuColoresUnColor coloresColor1, coloresColor2;
        public SketcherMenuColoresDosColores(String titulo_1, String titulo_2, String actionCommnad1,String actionCommnad2) {
            setLayout(new GridLayout(1, 2,10,0));
            
            coloresColor1 = new SketcherMenuColoresUnColor(titulo_1, model.getColor1Defecto(), actionCommnad1);
            coloresColor2 = new SketcherMenuColoresUnColor(titulo_2, model.getColor2Defecto(), actionCommnad2);
            
            add(coloresColor1);
            add(coloresColor2);
        }
        //SETTERS
        public void setColor1(Color color) {
            coloresColor1.setColor(color);
        }
        public void setColor2(Color color) {
            coloresColor2.setColor(color);
        }
        //LISTENERS
        public void setActionListener(ActionListener actionListener){
            coloresColor1.setActionListener(actionListener);
            coloresColor2.setActionListener(actionListener);
        }
    }
    class SketcherMenuColoresTitulo extends JPanel
    {
        private JLabel label_titulo;

        public SketcherMenuColoresTitulo(String titulo) {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            
            label_titulo = new JLabel(titulo);
            
            add(label_titulo);
        }
    }
    //SETTERS
    public void setColor1(Color color) {
        menuColoresDosColores.setColor1(color);
        menuColoresUnColor.setColor(color);
    }
    public void setColor2(Color color) {
        menuColoresDosColores.setColor2(color);
    }
    
    //METODOS
    public void MostrarPaletaColores(){
        cardLayout.show(this, CARD_2_PALETAS);
    }
    public void MostrarPaletaColor(){
        cardLayout.show(this, CARD_1_PALETA);
    }
    //LISTENER
    public void setActionListener(ActionListener actionListener){
        menuColoresUnColor.setActionListener(actionListener);
        menuColoresDosColores.setActionListener(actionListener);
    }
}
