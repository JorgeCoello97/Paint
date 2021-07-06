package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class VentanaSketcher extends JFrame{
    private Model model;
    private DibujoPanel dibujoPanel;
    private final SketcherToolbarMenu toolbarMenu;
    private final SketcherToolbarHerramientas toolbarHerramientas;
    private final MenuBar menuBar;
    
    public VentanaSketcher(Model model){
        this.model = model;

        setTitle(model.getNombreArchivo());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 725);
        
        menuBar = new MenuBar();
        setJMenuBar(menuBar);        
        
        toolbarMenu = new SketcherToolbarMenu(model);
        toolbarHerramientas = new SketcherToolbarHerramientas();
        
        dibujoPanel = new DibujoPanel();
        
        add(toolbarHerramientas, BorderLayout.LINE_START);
        add(toolbarMenu, BorderLayout.NORTH);
        add(dibujoPanel, BorderLayout.CENTER);
    }
    
    //SKETCHER
    class DibujoPanel extends JPanel
    {    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D gd = (Graphics2D) g;
            gd.drawImage(model.getDibujoActual(), 0, 0, null);
        }
    }
    
    //GETTERS
    public SketcherToolbarHerramientas getToolbarHerramientas() {
        return toolbarHerramientas;
    }
    public SketcherToolbarMenu getToolbarMenu() {
        return toolbarMenu;
    }

    //METODOS UTILES
    public JButton addButtons(String nameButton, String imageName, String toolTip) {
        String imgLocation = "../images/"+imageName+".png";
        URL imagenURL = VentanaSketcher.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand("button".concat(nameButton));
        if(imagenURL != null)
            button.setIcon(new ImageIcon(imagenURL));
        else
            button.setText(nameButton);
        
        button.setToolTipText(toolTip);
        return button;
    }
    
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        toolbarHerramientas.setActionListener(actionListener);
        toolbarMenu.setActionListener(actionListener);
        menuBar.setActionListener(actionListener);
    }
    public void setMouseListener(MouseAdapter mouseAdapter){
        dibujoPanel.addMouseListener(mouseAdapter);
    }
    public void setChangeListener(ChangeListener changeListener){
        toolbarMenu.setChangeListener(changeListener);
    }
    public void setMouseMotionListener(MouseMotionListener motionListener){
        dibujoPanel.addMouseMotionListener(motionListener);
    }
}
