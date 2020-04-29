package Controllers;

import Views.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.sql.SQLOutput;

import static Views.RegisterView.*;
import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class RegisterParentController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
        this.rview = rview;
        addExitAction(new RegisterController.ExitListeners(rview, false), rview.exit);
        addMinimizeAction(new RegisterController.MinimizeListeners(rview, false), rview.minimize);
        rview.addImageAction(new addImage_action());
    }

    class addImage_action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //String path=null;
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            //file extension
            FileNameExtensionFilter extension = new FileNameExtensionFilter("*Images", ".jpg", ".png", ".jpeg");
            chooser.addChoosableFileFilter(extension);
            int fileState = chooser.showSaveDialog(null);
            //check if the user select an image
            if (fileState == JFileChooser.APPROVE_OPTION) {
                //Remove old components- profile image and circle button
                rview.getContentPane().remove(rview.imageContainer);
                rview.getContentPane().remove(rview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                rview.imageContainer=new JLabel(rview.image);
                rview.imageContainer.setBounds(175,20,250,250);
                //Fitting the picture
                rview.imageContainer.setIcon(new ImageIcon(
                        new ImageIcon(selectedImage.getAbsolutePath()).getImage().getScaledInstance(rview.imageContainer.getWidth(),
                                rview.imageContainer.getHeight(), Image.SCALE_DEFAULT)));
                rview.add(rview.imageContainer);
                //Refresh view
                rview.repaint();
            }
        }
    }
}
