package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionColores extends JPanel{
    public static final String CARD_1_PALETA = "PALETA_COLOR";
    public static final String CARD_2_PALETAS = "PALETA_COLORES";
    
    private Model model;
    private CardLayout cardLayout;
    private ConfiguracionUnColor unColor;
    private ConfiguracionDosColores dosColores;
    
    public ConfiguracionColores(Model model) {
        this.model = model;
        cardLayout = new CardLayout(0,10);
        setLayout(cardLayout);
        
        unColor = new ConfiguracionUnColor();
        dosColores = new ConfiguracionDosColores();
        
        add(unColor, CARD_1_PALETA);
        add(dosColores, CARD_2_PALETAS);
        
        if(model.isDegradadoDefecto())
            cardLayout.show(this, CARD_2_PALETAS);
        else
            cardLayout.show(this, CARD_1_PALETA);
    }
    class ConfiguracionDosColores extends JPanel{
        private Configuracion2Colores configuracionColores;
        private ConfiguracionColoresTitulo configuracionColoresTitulo;
        
        public ConfiguracionDosColores() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder("COLORES"));
            
            configuracionColores = new Configuracion2Colores(model);
            configuracionColoresTitulo = new ConfiguracionColoresTitulo();
            add(configuracionColoresTitulo, BorderLayout.NORTH);
            add(configuracionColores, BorderLayout.CENTER);
        }
        public void setChangeListenerColor1(ChangeListener cl) {
            configuracionColores.setChangeListener1(cl);    
        }
        public void setChangeListenerColor2(ChangeListener cl) {
            configuracionColores.setChangeListener2(cl);
        }
    }
    class ConfiguracionUnColor extends JPanel{
        private Configuracion1Color configuracionColor;
        private ConfiguracionColoresTitulo configuracionColoresTitulo;
        
        public ConfiguracionUnColor() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createTitledBorder("COLOR"));
            
            
            configuracionColor = new Configuracion1Color(model);
            configuracionColoresTitulo = new ConfiguracionColoresTitulo();
            
            add(configuracionColoresTitulo, BorderLayout.NORTH);
            add(configuracionColor, BorderLayout.CENTER);
        }
     
        public void setChangeListenerColor(ChangeListener cl) {
            configuracionColor.setChangeListener(cl);
        }
    }
    
    class ConfiguracionColoresTitulo extends JPanel{
        private JLabel label_titulo;

        public ConfiguracionColoresTitulo() {
            setLayout(new FlowLayout());
            
            label_titulo = new JLabel("¿Que colores desea para expresar su arte?");
            label_titulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            add(label_titulo);
        }
    }
    //METODOS
    public void MostrarPaletaColores(){
        cardLayout.show(this, CARD_2_PALETAS);
    } 
    public void MostrarPaletaColor(){
        cardLayout.show(this, CARD_1_PALETA);
    }
     
    //LISTENERS
    public void setChangeListenerColor(ChangeListener cl) {
        unColor.setChangeListenerColor(cl);
    }
    
    public void setChangeListenerColor1(ChangeListener cl) {
        dosColores.setChangeListenerColor1(cl);

    }
    
    public void setChangeListenerColor2(ChangeListener cl) {
        dosColores.setChangeListenerColor2(cl);
    }
}
