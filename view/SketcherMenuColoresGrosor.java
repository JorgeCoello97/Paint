package practicafinal.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuColoresGrosor extends JPanel{
    private Model model;
    private SketcherMenuColores menuColores;
    private SketcherMenuGrosor menuGrosor;
    public SketcherMenuColoresGrosor(Model model) {
        this.model = model;
        setLayout(new BorderLayout());
        
        menuColores = new SketcherMenuColores(model);
        menuGrosor = new SketcherMenuGrosor(model);
        
        add(menuColores,BorderLayout.WEST);
        add(menuGrosor, BorderLayout.CENTER);
    }
    //GETTERS
    public SketcherMenuColores getMenuColores() {
        return menuColores;
    }
    public SketcherMenuGrosor getMenuGrosor() {
        return menuGrosor;
    }
    
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        menuColores.setActionListener(actionListener);
    }
    public void setChangeListener(ChangeListener changeListener){
        menuGrosor.setChangeListener(changeListener);
    }
}