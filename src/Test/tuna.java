package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by yuguanxu on 12/18/16.
 */
public class tuna extends JFrame{

    private JTextField item1;
    private JTextField item2;
    private JTextField item3;
    private JPasswordField passwordField;

    public tuna (){
        super("the title bar");
        setLayout(new FlowLayout());
        item1 = new JTextField(10);
        item2 = new JTextField("please enter", 20);
        item3 = new JTextField("unable to text");
        passwordField = new JPasswordField("",20);
        add(item1);
        add(item2);
        add(item3);
        add(passwordField);

        EventHandler handler = new EventHandler();
        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler);
        passwordField.addActionListener(handler);



    }

    private class EventHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "";
            if(e.getSource() == item1 ){
                message = " haha " + e.getActionCommand().toString();
            }else if(e.getSource() == item2){
                message = " item 2" + e.getActionCommand().toString();
            }else if(e.getSource() == item3){
                message = "item 3 "+ e.getActionCommand().toString();
            }else if(e.getSource() == passwordField){
                message = " password is" + e.getActionCommand().toString();
            }

            JOptionPane.showMessageDialog(null,message);

            tuna a = new tuna();
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            a.setSize(300,300);
            a.setVisible(true);

            a.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int i=JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?");
                    if(i==0)
                        System.exit(0);//cierra aplicacion
                }
            });

        }
    }
}
