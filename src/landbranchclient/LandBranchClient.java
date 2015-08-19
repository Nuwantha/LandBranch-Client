/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package landbranchclient;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import las.views.FrontPage;
import las.views.StartSplash;

/**
 *
 * @author DinsuG
 */
public class LandBranchClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        try {
            // UIManager.setLookAndFeel(new GraphiteLookAndFeel());
            UIManager.setLookAndFeel(new AcrylLookAndFeel());
            // UIManager.setLookAndFeel(new McWinLookAndFeel());
            // UIManager.setLookAndFeel(new TextureLookAndFeel());       
            new StartSplash().setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LandBranchClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
