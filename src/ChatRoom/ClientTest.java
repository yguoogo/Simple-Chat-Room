package ChatRoom;

import javax.swing.*;

/**
 * Created by yuguanxu on 12/17/16.
 */
public class ClientTest extends JFrame{
    public static void main(String[] args) {
        Client charlie;
        charlie = new Client("127.0.0.1");
        charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        charlie.startRunning();
    }
}
