package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class ConfiguracionConfirmacionDialog extends JDialog {
    private Model model;
    private final ConfiguracionConfirmacionDialogCuerpo confirmacionDialogCuerpo;

    public ConfiguracionConfirmacionDialog(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 300);
        setTitle("Confirmación");
        setResizable(false);
        
        confirmacionDialogCuerpo = new ConfiguracionConfirmacionDialogCuerpo();
        
        add(confirmacionDialogCuerpo);
        
    }
    class ConfiguracionConfirmacionDialogCuerpo extends JPanel
    {
        private final JButton button_confirmar, button_volver;
        private final ConfiguracionConfirmacionEstado estado;
        public ConfiguracionConfirmacionDialogCuerpo() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(null, "CONFIRMACIÓN CONFIGURACIÓN", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION),
            BorderFactory.createEmptyBorder(30, 10, 10, 10)));
            
            button_confirmar = addButtons("Confirmar", "aceptar",
                    "Confirmar estado actual de la configuración por defecto.");
            button_confirmar.setContentAreaFilled(false);
            button_confirmar.setActionCommand("buttonConfirmar");

            button_volver = addButtons("Volver", "volver", 
                    "Volver a la configuración.");
            button_volver.setContentAreaFilled(false);
            button_volver.setActionCommand("buttonVolver");

            estado = new ConfiguracionConfirmacionEstado();
            add(button_confirmar, BorderLayout.EAST);
            add(button_volver, BorderLayout.SOUTH);
            add(estado, BorderLayout.CENTER);
        }
        //METODOS
        public ConfiguracionConfirmacionEstado getEstado() {
            return estado;
        }
        public void seleccionarFigura(int opcion) {
            estado.seleccionarFigura(opcion);
        }
        //LISTENERS
        public void setActionListener(ActionListener actionListener)
        {
            button_confirmar.addActionListener(actionListener);
            button_volver.addActionListener(actionListener);
        }
    }
    class ConfiguracionConfirmacionEstado extends JPanel{
        private ConfiguracionConfirmacionEstadoArriba estadoArriba;
        private ConfiguracionConfirmacionEstadoAbajo estadoAbajo;
        
        public ConfiguracionConfirmacionEstado() {
            setLayout(new GridLayout(2, 1, 0, 10));
            
            estadoArriba = new ConfiguracionConfirmacionEstadoArriba();
            add(estadoArriba);
            
            estadoAbajo = new ConfiguracionConfirmacionEstadoAbajo();
            add(estadoAbajo);
        }
        //SETTERS
        public void setEstadoGrosor(int valor){
            estadoAbajo.setEstadoGrosor(valor);
        }
        public void setEstadoColor1(Color color){
            estadoAbajo.setEstadoColor1(color);
        }
        public void setEstadoColor2(Color color){
            estadoAbajo.setEstadoColor2(color);
        }
        public void setEstadoDegradadoNo(){
            estadoArriba.setEstadoDegradadoNo();
        }
        public void setEstadoDegradadoSi(){
            estadoArriba.setEstadoDegradadoSi();
        }
        public void setVisibleColor2(boolean opcion){
            estadoAbajo.setVisibleColor2(opcion);
        }
        public void seleccionarFigura(int opcion){
            estadoArriba.seleccionarFigura(opcion);
        }
    }
    class ConfiguracionConfirmacionEstadoArriba extends JPanel{
        private ConfiguracionConfirmacionEstadoDegradado estadoDegradado;
        private ConfiguracionConfirmacionEstadoFiguras estadoFiguras;

        public ConfiguracionConfirmacionEstadoArriba() {
            setLayout(new GridLayout(1, 2, 10, 0));
            
            estadoDegradado = new ConfiguracionConfirmacionEstadoDegradado();
            add(estadoDegradado);

            estadoFiguras = new ConfiguracionConfirmacionEstadoFiguras();
            add(estadoFiguras);
        }
        class ConfiguracionConfirmacionEstadoDegradado extends JPanel{
            private final JLabel label_degradado;
            private final JRadioButton radio_button_si, radio_button_no;
            private ButtonGroup group;
        
            public ConfiguracionConfirmacionEstadoDegradado() {
                setLayout(new GridLayout(1, 3));
                        
                label_degradado = new JLabel("Degradado:");
                add(label_degradado);
            
                radio_button_si = new JRadioButton("SI");
                radio_button_si.setEnabled(false);
            
                radio_button_no = new JRadioButton("NO");
                radio_button_no.setEnabled(false);
            
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
            //SETTERS
            public void setEstadoDegradadoSi() {
                radio_button_si.setSelected(true);
                radio_button_no.setSelected(false);
            }
            public void setEstadoDegradadoNo() {
                radio_button_si.setSelected(false);
                radio_button_no.setSelected(true);
            }
        }
        class ConfiguracionConfirmacionEstadoFiguras extends JPanel{
            private final JComboBox<String> comboBox_Figuras;
            private JLabel label;
            private String[] nombre_figuras = {"LINEA","CIRCULO","CUADRADO"};
        
            public ConfiguracionConfirmacionEstadoFiguras() {
        
                setLayout(new GridLayout(2, 1));
            
                label = new JLabel("Figura:");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                add(label);
            
                comboBox_Figuras = new JComboBox(nombre_figuras);
                comboBox_Figuras.setEnabled(false);
                comboBox_Figuras.setToolTipText("Elige el tipo de figura.");
            
                add(comboBox_Figuras);
            }
            public void seleccionarFigura(int opcion) {
                comboBox_Figuras.setSelectedIndex(opcion);
            }
        }
        //SETTERS
        public void setEstadoDegradadoNo() {
            estadoDegradado.setEstadoDegradadoNo();
        }
        public void setEstadoDegradadoSi() {
            estadoDegradado.setEstadoDegradadoSi();
        }
        public void seleccionarFigura(int opcion) {
            estadoFiguras.seleccionarFigura(opcion);
        }      
    }
    class ConfiguracionConfirmacionEstadoAbajo extends JPanel{
        private ConfiguracionConfirmacionEstadoColores estadoColores;
        private ConfiguracionConfirmacionEstadoGrosor estadoGrosor;

        public ConfiguracionConfirmacionEstadoAbajo() {
            setLayout(new BorderLayout());
            
            estadoColores = new ConfiguracionConfirmacionEstadoColores();
            estadoColores.setVisibleColor2(false);
            add(estadoColores, BorderLayout.EAST);
            
            estadoGrosor = new ConfiguracionConfirmacionEstadoGrosor();
            add(estadoGrosor, BorderLayout.CENTER);
        }
        class ConfiguracionConfirmacionEstadoColores extends JPanel{
            private final JLabel label_color1, label_color2;
            private final JTextField textField_color1, textField_color2;

            public ConfiguracionConfirmacionEstadoColores() {
                setLayout(new GridLayout(1, 4, 10, 40));
            
                label_color1 = new JLabel("Color1: ");
                label_color1.setHorizontalAlignment(SwingConstants.CENTER);
                textField_color1 = new JTextField(4);
                textField_color1.setEditable(false);
                textField_color1.setBackground(model.getColor1Defecto());
            
                add(label_color1);
                add(textField_color1);

                label_color2 = new JLabel("Color2: ");
                label_color2.setHorizontalAlignment(SwingConstants.CENTER);
                textField_color2 = new JTextField(4);
                textField_color2.setEditable(false);
                textField_color2.setBackground(model.getColor2Defecto());
            
                add(label_color2);
                add(textField_color2);
            }
            //SETTERS
            public void setVisibleColor2(boolean opcion){
                label_color2.setVisible(opcion);
                textField_color2.setVisible(opcion);
            }
            public void setEstadoColor1(Color color) {
                textField_color1.setBackground(color);
            }
            public void setEstadoColor2(Color color) {
                textField_color2.setBackground(color);
            }   
        }
        class ConfiguracionConfirmacionEstadoGrosor extends JPanel{
            private final JSlider slider_grosor;
            private final JLabel label_grosor;
            private final Hashtable hashtable;

            public ConfiguracionConfirmacionEstadoGrosor() {
                setLayout(new BorderLayout());
                label_grosor = new JLabel("Grosor:");
                add(label_grosor, BorderLayout.NORTH);
                slider_grosor = new JSlider(0, 100, model.getGrosorDefecto());
                slider_grosor.setEnabled(false);
            
                hashtable = new Hashtable();
            
                for(int i = 0; i <= 100; i += 10)
                    hashtable.put(i, new JLabel(String.valueOf(i)));
            
                slider_grosor.setLabelTable(hashtable);
                slider_grosor.setPaintLabels(true);
                slider_grosor.setPaintTicks(true);
                slider_grosor.setMinorTickSpacing(1);
                slider_grosor.setMajorTickSpacing(10);
            
                add(slider_grosor, BorderLayout.CENTER);
            }
            public void setEstadoGrosor(int valor){
                slider_grosor.setValue(valor);
            }
        }
        //SETTERS
        public void setEstadoGrosor(int valor){
            estadoGrosor.setEstadoGrosor(valor);
        }
        public void setEstadoColor1(Color color){
            estadoColores.setEstadoColor1(color);
        }
        public void setEstadoColor2(Color color){
            estadoColores.setEstadoColor2(color);
        }
        public void setVisibleColor2(boolean opcion){
            estadoColores.setVisibleColor2(opcion);
        }
    }

    //SETTERS
    public void setFigura(int opcion) {
        confirmacionDialogCuerpo.seleccionarFigura(opcion);
    }
    public void setEstadoGrosor(int valor){
        confirmacionDialogCuerpo.getEstado().setEstadoGrosor(valor);
    }
    public void setEstadoColor1(Color color){
        confirmacionDialogCuerpo.getEstado().setEstadoColor1(color);
    }
    public void setEstadoColor2(Color color){
        confirmacionDialogCuerpo.getEstado().setEstadoColor2(color);
    }
    public void setEstadoDegradadoNo(){
        confirmacionDialogCuerpo.getEstado().setEstadoDegradadoNo();   
    }
    public void setEstadoDegradadoSi(){
        confirmacionDialogCuerpo.getEstado().setEstadoDegradadoSi();
    }
    public void setVisibleColor2(boolean opcion){
        confirmacionDialogCuerpo.getEstado().setVisibleColor2(opcion);
    }
    //UTIL
    public JButton addButtons(String nameButton, String imageName, String toolTip) {
        String imgLocation = "../images/"+imageName+".png";
        URL imagenURL = VentanaSketcher.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand("button".concat(nameButton));
        button.setToolTipText(toolTip);
        if(imagenURL != null)
            button.setIcon(new ImageIcon(imagenURL));
        else
            button.setText(nameButton);
        return button;
    }
    
    //LISTENERS
    public void setActionListener(ActionListener al) {
        confirmacionDialogCuerpo.setActionListener(al);
    }
    public void setWindowListener(WindowListener windowListener){
        this.addWindowListener(windowListener);
    }
}
