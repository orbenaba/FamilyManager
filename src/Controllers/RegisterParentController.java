package Controllers;

import Views.*;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.util.Calendar;

import static Views.RegisterView.*;

public class RegisterParentController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
        this.rview=rview;
        new RegisterHumanController(new RegisterHumanView());
    }

}
