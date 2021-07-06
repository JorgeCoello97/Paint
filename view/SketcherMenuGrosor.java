package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuGrosor extends JPanel{
    private Model model;
    private SketcherMenuGrosorTitulo menuGrosorTitulo;
    private SketcherMenuGrosorSlider menuGrosorSlider;
    
    public SketcherMenuGrosor(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(0, 10, 10, 10)));
        
        menuGrosorTitulo = new SketcherMenuGrosorTitulo("GROSOR");
        menuGrosorSlider = new SketcherMenuGrosorSlider();
        
        add(menuGrosorTitulo, BorderLayout.NORTH);
        add(menuGrosorSlider, BorderLayout.CENTER);
    }
    class SketcherMenuGrosorTitulo extends JPanel
    {
        private JLabel label_titulo;

        public SketcherMenuGrosorTitulo(String titulo) {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            label_titulo = new JLabel(titulo);
            
            add(label_titulo);
        }
        public void ActualizarTitulo(int tipo_figura)
        {
            if(tipo_figura == Model.FIGURA_LINEA){
                label_titulo.setText("GROSOR DE LA LINEA");
            }
            else if (tipo_figura == Model.FIGURA_CIRCULO){
                label_titulo.setText("RADIO DEL CIRCULO");
            }
            else if (tipo_figura == Model.FIGURA_CUADRADO){
                label_titulo.setText("ANCHO DEL CUADRADO");
            }       
        }
    }
    class SketcherMenuGrosorSlider extends JPanel
    {
        private JSlider slider;
        private Hashtable hashtable;

        public SketcherMenuGrosorSlider() {
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
        public void ActualizarSlider(int valor){
            slider.setValue(valor);
        }
        public void setChangeListener(ChangeListener changeListener) {
            slider.addChangeListener(changeListener);
        }
    }
    //SETTERS
    public void ActualizarSlider(int valor){
        menuGrosorSlider.ActualizarSlider(valor);
    }
    public void ActualizarTitulo(int tipo_figura){
        menuGrosorTitulo.ActualizarTitulo(tipo_figura);
    }
    //LISTENERS
    public void setChangeListener(ChangeListener changeListener) {
        menuGrosorSlider.setChangeListener(changeListener);
    }
}
