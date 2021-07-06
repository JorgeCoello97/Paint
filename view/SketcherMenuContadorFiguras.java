package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuContadorFiguras extends JPanel{
    private Model model;
    private final SketcherMenuContadorFigura contadorFiguraLinea;
    private final SketcherMenuContadorFigura contadorFiguraCirculos;
    private final SketcherMenuContadorFigura contadorFiguraCuadrado;
    
    public SketcherMenuContadorFiguras(Model model) {
        this.model = model;
        setLayout(new GridLayout(3, 1, 10, 0));
         setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
         
        contadorFiguraLinea = new SketcherMenuContadorFigura("Lineas:", "Número de lineas en el dibujo.");
        contadorFiguraCirculos = new SketcherMenuContadorFigura("Circulos:","Número de circulos en el dibujo.");
        contadorFiguraCuadrado = new SketcherMenuContadorFigura("Cuadrados:", "Número de cuadrados en el dibujo.");
        
        add(contadorFiguraLinea);
        add(contadorFiguraCuadrado);
        add(contadorFiguraCirculos);
    }
    
    class SketcherMenuContadorFigura extends JPanel
    {
        private JLabel label_figura;
        private JTextField field_contador;
        
        public SketcherMenuContadorFigura(String tipo_figura, String toolTip) {
            setLayout(new BorderLayout(10,0));
            
            label_figura = new JLabel(tipo_figura);
            label_figura.setToolTipText(toolTip);
            label_figura.setHorizontalAlignment(SwingConstants.CENTER);
            field_contador = new JTextField("0",2);
            field_contador.setHorizontalAlignment(SwingConstants.RIGHT);
            field_contador.setEditable(false);
            
            add(label_figura,BorderLayout.CENTER);
            add(field_contador, BorderLayout.EAST);
        }
        public void ActualizarContador(int valor){
            field_contador.setText(String.valueOf(valor));
        }
    }
    //METODOS
    public void reiniciarContadores()
    {
        contadorFiguraLinea.ActualizarContador(0);
        contadorFiguraCirculos.ActualizarContador(0);   
        contadorFiguraCuadrado.ActualizarContador(0);
    }
    public void actualizarContadorFiguraLinea(int valor) {
        contadorFiguraLinea.ActualizarContador(valor);
    }
    public void actualizarContadorFiguraCirculos(int valor) {
        contadorFiguraCirculos.ActualizarContador(valor);
    }
    public void actualizarContadorFiguraCuadrado(int valor) {
        contadorFiguraCuadrado.ActualizarContador(valor);
    }    
}