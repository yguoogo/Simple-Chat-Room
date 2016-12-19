package Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yuguanxu on 12/18/16.
 */
public class Gui extends JFrame{
    private JButton reg;
    private JButton custom;
    public Gui(){
        super("the title");
        setLayout(new FlowLayout());

        reg = new JButton("reg button");
        add(reg);

        //Icon b = new ImageIcon(getClass().getResource("sample"));

        custom = new JButton("Custom");
    }
}
