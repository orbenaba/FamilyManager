package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class StartView extends Jframe {
    public JLabel title,background;//this intended to separate between all the characters in the title
    public JLabel login,register;
    private int width;

    public StartView(){
        super((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        setExtendedState(MAXIMIZED_BOTH);
        width=getWidth();
        setTitle("Start");
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/homeBack.jpg")));
        background.setBounds(0,0,width,getHeight());
        initTitle(width/2-400,50,60);
        init_Login_Register();

        add(login);
        add(register);
        add(title);
        add(background);
        setVisible(true);
    }


    public void initTitle(int x,int y,int size){
        title=new JLabel("Welcome to family manager");
        title.setFont(new Font("Arial", Font.ITALIC,size));
        title.setForeground(Color.blue);
        title.setBounds(x,y,800,size+10);
    }
    public void init_Login_Register(){
        register=new JLabel("Create a new family account!");
        login=new JLabel("Already have an account..");
        register.setForeground(Color.blue);
        login.setForeground(Color.blue);
        Font basic=new Font("Arial",Font.ITALIC,40);
        register.setBounds(width/2-400,350, 800,50);
        register.setFont(basic);
        login.setBounds(width/2-400,450,800,50);
        login.setFont(basic);
    }
    public void addRegisterAction(MouseAdapter mal){
        register.addMouseListener(mal);
    }
    public void addLoginAction(MouseAdapter mal){
        login.addMouseListener(mal);
    }

    public void addTitleListener(MouseAdapter mal){
        title.addMouseListener(mal);
    }
}
