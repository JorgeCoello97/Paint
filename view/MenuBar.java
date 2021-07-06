package practicafinal.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/*
 *
 * @author Yannick & Jorge
 */
public class MenuBar extends JMenuBar {
    private JMenu menuArchivo;
    private JMenuItem itemSalir, itemAcercaDe;
    public MenuBar() {
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("itemSalir");
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        
        itemAcercaDe = new JMenuItem("Acerca de...");
        itemAcercaDe.setActionCommand("itemAcercaDe");
        itemAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        
        menuArchivo = new JMenu("Archivo");
        menuArchivo.add(itemAcercaDe);
        menuArchivo.add(itemSalir);
        
        add(menuArchivo);
    }
    public void setActionListener(ActionListener actionListener){
        itemSalir.addActionListener(actionListener);
        itemAcercaDe.addActionListener(actionListener);
    }
}