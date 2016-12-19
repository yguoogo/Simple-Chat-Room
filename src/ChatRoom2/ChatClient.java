package ChatRoom2;

/**
 * Created by yuguanxu on 12/17/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class ChatClient extends JFrame implements Runnable
{  private Socket socket              = null;
    private Thread thread              = null;
    private DataInputStream  console   = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread client    = null;
    public String userName = "";

    private JTextField userText;
    private JTextArea chatWindow;

    public ChatClient(String serverName, int serverPort, String name)
    {
        super("Chat Room " + (serverPort-3000) + "    " + name);
        userName = name;
        System.out.println("Establishing connection. Please wait ...");
        try
        {   socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
            sendSystemMessage();
        }
        catch(UnknownHostException uhe)
        {  System.out.println("Host unknown: " + uhe.getMessage()); }
        catch(IOException ioe)
        {  System.out.println("Unexpected exception: " + ioe.getMessage()); }
    }


    public void run()
    {  while (true) {
        try {
            streamOut.writeUTF(console.readLine());
            streamOut.flush();
        } catch(IOException ioe) {
            System.out.println("Sending error: " + ioe.getMessage());
            stop();
        }
    }
    }



    public void handle(String msg) {
        if (msg.equals(".bye")) {
            System.out.println("Good bye. Press RETURN to exit ...");
            stop();
        } else {
            showMessage(msg);
        }
    }



    public void start() throws IOException
    {  console   = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
        setUpGUI();
        ableToType(true);
        if (thread == null)
        {
            client = new ChatClientThread(this, socket);
            //thread = new Thread(this);
            //thread.start();
        }
    }
    public void stop()
    {


        if (thread != null) {
            thread.stop();
            thread = null;
        }try {
        if (console   != null)  console.close();
        if (streamOut != null)  streamOut.close();
        if (socket    != null)  socket.close();
    } catch(IOException ioe) {
        System.out.println("Error closing ...");
    }


        client.close();
        client.stop();
    }

    private void setUpGUI(){
        userText = new JTextField();
        userText.setEditable(false);

        userText.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        sendMessage(event.getActionCommand());
                        userText.setText("");
                    }
                }
        );

        add(userText, BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        setSize(300,500);
        setVisible(true);
    }

    private void sendMessage(final String message){
        try {
            streamOut.flush();
            String display_message = userName + " - " + message + "\n";
            streamOut.writeUTF(display_message);
        } catch(IOException ioe) {
            System.out.println("Sending error: " + ioe.getMessage());
            stop();
        }
    }


    private void showMessage(final String message){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {

                        chatWindow.append(message);
                    }
                }
        );
    }

    private void ableToType(final boolean tof){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        userText.setEditable(tof);
                    }
                }
        );
    }

    private void sendSystemMessage(){
        try {
            streamOut.flush();
            String display_message = "System - Welcome " + userName + "!!! \n";
            streamOut.writeUTF(display_message);
        } catch(IOException ioe) {
            System.out.println("Sending error: " + ioe.getMessage());
            stop();
        }
    }

}