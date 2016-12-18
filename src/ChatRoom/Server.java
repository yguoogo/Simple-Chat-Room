package ChatRoom;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Created by yuguanxu on 12/10/16.
 */
public class Server extends JFrame implements Runnable{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    // constructor

    public Server(){
        super("DoDo");
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
        add(userText,BorderLayout.NORTH);
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow), BorderLayout.CENTER);
        //add(new JScrollPane());
        setSize(300,500);
        setVisible(true);
    }

    //set up and run the server
    public void startRunning(){
        try{
            server = new ServerSocket(3000,100);// second params  how many people can wait
            while(true){
                try{
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                    //connect and have conversation
                }catch (EOFException eofException){
                    showMessage("\n Server ended the connection! ");
                }finally {
                    closeCrap();
                }
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    // wait for connection, then display connection information

    private void waitForConnection() throws  IOException{
        showMessage("Waiting for some to connect ...\n");
        connection = server.accept();
        showMessage("Now connected to "+ connection.getInetAddress().getHostName());
    }

    //get stream to send and receive data
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup \n");
    }

    // during the chat conversation
    private void whileChatting() throws IOException{
        String message = " You are now connected!";
        sendMessage(message);
        ableToType(true);
        do{
            // have a conversation
            try{
                message = (String) input.readObject();  // the meaning of readObject
                showMessage("\n" + message);
            }catch(ClassNotFoundException classNotFoundException){
                showMessage("\n idk wtf that user send!");
            }
        }while (!message.equals("Client - END"));
    }

    // close stream and sockets after you are down chatting
    private void closeCrap(){
        showMessage("\n  Closing connections ......  \n");
        ableToType(false);

        try{
            output.close();
            input.close();
            connection.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    //send a message to client
    private void sendMessage(String message){
        try{
            output.writeObject("SERVER - "+ message);
            output.flush();
            showMessage("\n Server -" + message); // show on server screen
        }catch (IOException ioException){
            chatWindow.append("\n ERROR: DUDE I CANT SEND THAT MESSAGE");
        }
    }

    // updates chatWindow
    private void showMessage(final String m){
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        chatWindow.append(m);
                    }
                }
        );
    }

    // let the user tpye stuff into their box
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


    @Override
    public void run() {

    }
}
