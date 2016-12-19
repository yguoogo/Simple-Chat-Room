package Test;

import javax.swing.*;

/**
 * Created by yuguanxu on 12/18/16.
 */
public class TeseEnmu {
    public static void main(String[] args) {
        Signal signal = Signal.Green;
        Gui gui = new Gui();

        /*gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300,200);
        gui.setVisible(true);*/

       /* String fn = JOptionPane.showInputDialog("Enter the first number");
        String sn = JOptionPane.showInputDialog("Enter the second number");

        int num1 = Integer.parseInt(fn);
        int num2 = Inte  ger.parseInt(sn);
        int sum = num1 + num2;

        JOptionPane.showMessageDialog(null, "The sum is " + sum, "the title", JOptionPane.PLAIN_MESSAGE);*/

        tuna test = new tuna();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setSize(300,300);
        test.setVisible(true);

    }
}
