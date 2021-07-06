package practicafinal.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import practicafinal.model.Model;

/*
 *
 * @author Yannick & Jorge
 */
public class SketcherMenuDegradadoFiguras extends JPanel{
    private Model model;
    private SketcherMenuDegradado menuDegradado;
    private SketcherMenuFiguras menuFiguras;
    
    public SketcherMenuDegradadoFiguras(Model model) {
        this.model = model;
        setLayout(new GridLayout(1, 2));
        
        menuDegradado = new SketcherMenuDegradado(model);
        menuFiguras = new SketcherMenuFiguras(model);
        
        add(menuDegradado);
        add(menuFiguras);
    }
    //GETTERS
    public SketcherMenuDegradado getMenuDegradado(){
        return menuDegradado;
    }

    public SketcherMenuFiguras getMenuFiguras(){
        return menuFiguras;
    }
    
    //LISTENERS
    public void setActionListener(ActionListener actionListener){
        menuDegradado.setActionListener(actionListener);
        menuFiguras.setActionListener(actionListener);
    }
}