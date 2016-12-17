package ChatRoom;

import javax.swing.*;

/**
 * Created by yuguanxu on 12/17/16.
 */
public class ServerTest {
    public static void main(String[] args) {
        Server sally = new Server();
        sally.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sally.startRunning();
    }
}
