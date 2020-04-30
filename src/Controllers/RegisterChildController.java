package Controllers;

import Views.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Views.RegisterView.*;
import static java.sql.Types.NULL;

public class RegisterChildController {
    private RegisterChildView rview;

    public RegisterChildController(RegisterChildView rview) {
        this.rview = rview;
        new RegisterHumanController(new RegisterHumanView());
    }
}
