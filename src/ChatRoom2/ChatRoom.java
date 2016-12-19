package ChatRoom2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by yuguanxu on 12/18/16.
 */

public class ChatRoom extends JFrame {

    private String serverName = "";
    private String userName = "";
    private JButton chatRoom1;
    private JButton chatRoom2;
    private JButton chatRoom3;
    private JButton chatRoom4;
    private JButton chatRoom5;
    private JButton chatRoom6;
    private ChatRoomHandler roomHandler = null;

    public ChatRoom(String _servername, String _userName){
        serverName = _servername;
        userName = _userName;
        setUpChatRoomList();
    }

    private void enterChatRoom(int roomPort){
        ChatClient chatRoom = new ChatClient(serverName, roomPort, userName);
        //chatRoom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatRoom.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatRoom.stop();
            }
        });
    }

    private void setUpChatRoomList(){
        setLayout(new FlowLayout());
        chatRoom1 = new JButton("Chat Room 1");
        chatRoom2 = new JButton("Chat Room 2");
        chatRoom3 = new JButton("Chat Room 3");
        chatRoom4 = new JButton("Chat Room 4");
        chatRoom5 = new JButton("Chat Room 5");
        chatRoom6 = new JButton("Chat Room 6");

        add(chatRoom1);
        add(chatRoom2);
        add(chatRoom3);
        add(chatRoom4);
        add(chatRoom5);
        add(chatRoom6);

        roomHandler = new ChatRoomHandler();
        chatRoom1.addActionListener(roomHandler);
        chatRoom2.addActionListener(roomHandler);
        chatRoom3.addActionListener(roomHandler);
        chatRoom4.addActionListener(roomHandler);
        chatRoom5.addActionListener(roomHandler);
        chatRoom6.addActionListener(roomHandler);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }

    private boolean checkPortAvaliable(int _port){

        boolean portTaken = false;
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(_port);
        } catch (IOException e) {
            portTaken = true;
        } finally {
            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) { e.printStackTrace(); }
        }

        return portTaken;
    }

    private class ChatRoomHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == chatRoom1){
                enterChatRoom(3000);
            }else if(e.getSource() == chatRoom2){
                enterChatRoom(3001);
            }else if(e.getSource() == chatRoom3){
                enterChatRoom(3002);
            }else if(e.getSource() == chatRoom4){
                enterChatRoom(3003);
            }else if(e.getSource() == chatRoom5){
                enterChatRoom(3004);
            }else if(e.getSource() == chatRoom6){
                enterChatRoom(3005);
            }
        }
    }

    public static void main(String[] args) {
        ChatRoom roomlist = new ChatRoom("127.0.0.1", "user1");
    }


}
