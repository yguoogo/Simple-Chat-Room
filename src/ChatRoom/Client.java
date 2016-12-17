package ChatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by yuguanxu on 12/17/16.
 */
public class Client extends JFrame{
    private JTextField userText;
    private JTextArea chatWindow;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String serverIP;
    private Socket connection;

    public Client(String host){
        super("Client mofo!");
        serverIP = host;
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

    //connect to server
    public void startRunning(){
        try{
            connectToServer();
            setupStreams();
            whileChatting();

        }catch (EOFException eofException){
            showMessage("\n Client terminated connection");

        }catch (IOException ioException){
            ioException.printStackTrace();
        }finally{
            closeCrap();
        }
    }

    //connect to server
    private void connectToServer() throws IOException{
        showMessage("Attempting connection.. \n");
        connection = new Socket(InetAddress.getByName(serverIP),3000);
        showMessage("Connected to :" + connection.getInetAddress().getHostName());

    }

    //set up streams to send and receive messages
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Dude your streams are now good to go! \n");
    }

    // while chatting with server
    private void whileChatting() throws IOException{
        ableToType(true);
        do{
            try{
                message = (String) input.readObject();
                showMessage("\n" + message);
            }catch (ClassNotFoundException classNotFoundException){
                showMessage("\n I do not know that object type");
            }
        }while (!message.equals("SERVER - END"));
    }

    //close the streams and sockets
    private void closeCrap(){
        showMessage("\n closing crap down ...");
        ableToType(false);
        try{
            output.close();
            input.close();
            connection.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    // send message to server
    private void sendMessage(String message){
        try{
            output.writeObject("CLIENT - " + message);
            output.flush();
            showMessage("\n CLIENT - " + message);
        }catch (IOException ioExcpetion){
            chatWindow.append("\n something messed up sending message hoss!");
        }
    }

    //change/update chatwindow
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

    //get uer permission to tpye crap into the text box
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

}
