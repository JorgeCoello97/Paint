
package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JToolBar;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherToolbarMenu extends JToolBar {
    private Model model;
    private SketcherMenuDegradadoFiguras menuDegradadoFiguras;
    private SketcherMenuColoresGrosor menuColoresGrosor;
    private SketcherMenuContadorFiguras menuContadorFiguras;
    
    public SketcherToolbarMenu(Model model) {
        this.model = model;
        
        setLayout(new BorderLayout());
        setFloatable(false);
        setRollover(true);
        setBorderPainted(true);
        menuDegradadoFiguras = new SketcherMenuDegradadoFiguras(model);
        menuColoresGrosor = new SketcherMenuColoresGrosor(model);
        menuContadorFiguras = new SketcherMenuContadorFiguras(model);
        
        add(menuDegradadoFiguras, BorderLayout.WEST);
        add(menuColoresGrosor, BorderLayout.CENTER);
        add(menuContadorFiguras, BorderLayout.EAST);        
    }
    //GETTERS
    public SketcherMenuDegradadoFiguras getMenuDegradadoFiguras(){
        return menuDegradadoFiguras;
    }
    public SketcherMenuColoresGrosor getMenuColoresGrosor(){
        return menuColoresGrosor;
    }
    public SketcherMenuContadorFiguras getMenuContadorFiguras(){
        return menuContadorFiguras;
    }

    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        menuDegradadoFiguras.setActionListener(actionListener);
        menuColoresGrosor.setActionListener(actionListener);
    }
    public void setChangeListener(ChangeListener changeListener){
        menuColoresGrosor.setChangeListener(changeListener);
    }
}
