package Views;


import Models.Family;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import static Models.Family.getFamilyMembers;


public class FamilyTreeView extends BaseForHomeSeqView{
    public String username;
    public Tree familyTree;
    public ArrayList<TreeNode> parents;//holds all the parents in the family tree
    public ArrayList<TreeNode>children;//holds all the children in the family tree
    public JLabel background;
    public boolean isParent=true;
    public JLabel title;
    @Override
    public String getUsername(){
        return username;
    }
    public FamilyTreeView(String username){
        this.username=username;
        Font font=new Font("David",Font.ITALIC,40);
        /**=========================================================*/
        /**============Adding forest background====================*/
        /**=========================================================*/
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/forestBackground.jpg")));
        background.setBounds(0,0,getWidth(),getHeight());
       /**Title*/
        title=new JLabel("Family Hierarchy");
        title.setFont(new Font("David",Font.ITALIC,90));
        title.setForeground(new Color(255, 15, 15));
        title.setBackground(new Color(7,0,204));
        title.setOpaque(true);
        title.setBounds(getWidth()/2-340,20,680,80);

        Family.FamilyMembers familyMembers=getFamilyMembers(username);
        /**Now, we have the whole family data--*/
        //Identify the user-parent or child?
        familyTree=new Tree(familyMembers.members);
        for(UserData mem:familyTree.children){
            if(mem.uName.equals(username))
                isParent=false;
        }
        parents=new ArrayList<>();
        int totalParents=familyTree.parents.size();
        int i=0;
        JLabel text;
        //There are different propositions for more then 5 parents
        if(totalParents>=5){
            /**The width of each button is 145px and the distance between two buttons is 5px--->>(145+5)/2*/
            int xStartParents=getWidth()/2 - (totalParents*75)-150;
            for(UserData parent: familyTree.parents){
                text=new JLabel(parent.firstName);
                text.setForeground(Color.black);
                text.setFont(font);
                text.setBounds(xStartParents+i*190+45,170,200,200);
                add(text);
                JButton button=new JButton();
                if(parent.uName.equals(username))
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/myLeaf.png")));
                else
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/leaf.png")));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setBounds(xStartParents+i*190,150,190,200);
                i++;
                parents.add(new TreeNode(button,parent.uName,parent.firstName));
                add(button);
            }
        }
        else{//Less then 5 parents
            int xStartParents=getWidth()/2 - (totalParents*150);
            for(UserData parent: familyTree.parents){
                text=new JLabel(parent.firstName);
                text.setFont(font);
                text.setForeground(Color.black);
                text.setBounds(xStartParents+i*300+45,170,200,200);
                add(text);
                JButton button=new JButton();
                if(parent.uName.equals(username))
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/myLeaf.png")));
                else
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/leaf.png")));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setBounds(xStartParents+i*300,150,200,200);
                i++;
                parents.add(new TreeNode(button,parent.uName,parent.firstName));
                add(button);
            }
        }
        children=new ArrayList<>();
        int totalChildren=familyTree.children.size();
        i=0;
        if(totalChildren>=5) {
            int xStartChildren=getWidth()/2-(totalChildren*75)-150;
            for (UserData child : familyTree.children) {
                text=new JLabel(child.firstName);
                text.setFont(font);
                text.setForeground(Color.black);
                text.setBounds(xStartChildren+i*190+45,500,200,200);
                add(text);
                JButton button = new JButton();
                if(child.uName.equals(username))
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/myLeaf.png")));
                else
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/leaf.png")));
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setBounds(xStartChildren + i * 190, 500, 190, 200);
                i++;
                children.add(new TreeNode(button,child.uName,child.firstName));
                add(button);
            }
        }
        else {
            int xStartChildren=getWidth()/2-(totalChildren*150)-150;
            for (UserData child : familyTree.children) {
                text=new JLabel(child.firstName);
                text.setFont(font);
                text.setForeground(Color.black);
                text.setBounds(xStartChildren+i*300+60,500,240,200);
                add(text);
                JButton button = new JButton(child.firstName + '\n' + child.uName);
                /**We want to use a special color leaf for the current user in the family tree in order to
                 * distinguish between its profile and other's family profiles*/
                if(child.uName.equals(username))
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/myLeaf.png")));
                else
                    button.setIcon(new ImageIcon(getClass().getResource("/Icons/leaf.png")));
               /**In order to display only the leaf without the default options that are came with the button,
                * we'll change some settings*/
                button.setContentAreaFilled(false);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.setBounds(xStartChildren + i * 300, 500, 200, 200);
                i++;
                children.add(new TreeNode(button,child.uName,child.firstName));
                add(button);
            }
        }
        add(title);
        add(background);
        setVisible(true);
    }

    /**This class holds two separate lists of children anf parents in the current family*/
    public static class Tree{
        ArrayList<UserData> parents;
        ArrayList<UserData> children;
        /***/
        public Tree(HashMap<String, Family.memberData> members) {
            parents = new ArrayList<>();
            children = new ArrayList<>();
            members.forEach((user,data)->{
                UserData userdata=new UserData(data.firstName,user);
                if (data.isParent == true)
                    parents.add(userdata);
                 else
                    children.add(userdata);
            });
        }}
    public static class UserData{
        public String firstName;
        public String uName;
        public UserData(String firstName, String uName) {
            this.firstName = firstName;
            this.uName = uName;
        }
    }
    public static class TreeNode{
        public JButton button;
        public String uname;
        public String fname;
        public TreeNode(JButton button, String uname, String fname) {
            this.button = button;
            this.uname = uname;
            this.fname = fname;
        }
    }

    /**Listeners and actions*/
    public void addLeavesListener(MouseAdapter mal){
        parents.forEach((parentButton)->parentButton.button.addMouseListener(mal));
    }
    public void addLeafAction(ActionListener mal){
        parents.forEach(parent->parent.button.addActionListener(mal));
        children.forEach(child->child.button.addActionListener(mal));
    }
}
