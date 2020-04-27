package Views;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import static java.awt.Font.BOLD;

public class StartView extends JFrame {
    JPanel [][]matrixPanels;//cubes matrix background
    public JLabel exit,minimize;
    public Border frameExMin;
    public JLabel title;//this intended to separate between all the characters in the title
    public JLabel login,register;

    public StartView(){
        setSize(800,800);
        setTitle("Start");
        setLocationRelativeTo(null);//Center of screen's user
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//kill current process
        setUndecorated(true);//get rid of the ugly frame
        initMatrix(0,0,getWidth()/8,getHeight()/8);
        //add exit and minimize labels
        exit=new JLabel("X");
        minimize=new JLabel("-");
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
        init_Exit_Minimize(getWidth()-70,0,35,exit,minimize,frameExMin);
        initTitle(20,50,55);
        init_Login_Register();


        add(login);
        add(register);
        add(title);
        add(exit);
        add(minimize);
        addMatrixPanels();
        setVisible(true);
    }

    //set a beautiful background with matrix cube
    public void initMatrix(int x,int y,int width,int height){
        this.matrixPanels=new JPanel[8][8];
        int j;
        for(int i=0;i<8;i++)
            for(j=0;j<8;j++) {
                matrixPanels[i][j] = new JPanel();
                matrixPanels[i][j].setBounds(x+i*width,y+j*height,width,height);
                if((i%2==0&&j%2==0)||(i*j)%2==1)
                    matrixPanels[i][j].setBackground(new Color(96, 104, 55));
                else
                    matrixPanels[i][j].setBackground(new Color(149, 181, 130));
            }
    }
    //add the jlabel matrix to the main panel
    public void addMatrixPanels(){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                add(matrixPanels[i][j]);
    }
    //initialize exit and minimize
    //prevent code duplication by declaring the func as a static func.
    public static void init_Exit_Minimize(int x,int y,int size,JLabel exit,JLabel minimize,Border frameExMin)
    {
        minimize.setBounds(x,y,size,size);
        exit.setBounds(x+size,y,size,size);
        exit.setFont(new Font("Arial", BOLD,size));
        minimize.setFont(new Font("Arial", BOLD,size));
        exit.setForeground(Color.black);
        minimize.setForeground(Color.black);
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setBorder(frameExMin);
        minimize.setBorder(frameExMin);
    }
    //initialize title
    public void initTitle(int x,int y,int size){
        title=new JLabel("Welcome to family manager");
        title.setFont(new Font("Arial", Font.BOLD,size));
        title.setForeground(Color.black);
        title.setBounds(x,y,800,size);
    }
    //initialize login and register buttons
    public void init_Login_Register(){
        register=new JLabel("Create a new family account!");
        login=new JLabel("Already have an account..");
        register.setForeground(Color.yellow);
        login.setForeground(Color.yellow);
        Font basic=new Font("Arial",Font.ITALIC,40);
        register.setBounds(50,500, 800,50);
        register.setFont(basic);
        login.setBounds(50,600,800,50);
        login.setFont(basic);
    }

    //Add listener to minimize and exit labels via Start Controller
    public void addMinimizeAction(MouseAdapter mal) {
        minimize.addMouseListener(mal);
    }
    public void addExitAction(MouseAdapter mal){
        exit.addMouseListener(mal);
    }
    public void addRegisterAction(MouseAdapter mal){
        register.addMouseListener(mal);
    }
    public void addLoginAction(MouseAdapter mal){
        login.addMouseListener(mal);
    }
}
