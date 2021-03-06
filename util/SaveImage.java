package practicafinal.util;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Jorge y Yannick
 */
public class SaveImage extends JFileChooser {
    
    public SaveImage() {
        super();
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setDialogType(JFileChooser.SAVE_DIALOG);
        this.setVisible(true);
    }
  
    public File getFile() {
        int ret = showDialog(null, "Guardar imagen");

        if (ret == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        }
        else {
            return null;
        }
    }
}