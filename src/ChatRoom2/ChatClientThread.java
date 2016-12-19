package ChatRoom2;

/**
 * Created by yuguanxu on 12/17/16.
 */
import java.net.*;
import java.io.*;

public class ChatClientThread extends Thread
{
    private Socket           socket   = null;
    private ChatClient       client   = null;
    private ObjectInputStream  streamIn = null;

    public ChatClientThread(ChatClient _client, Socket _socket)
    {
        client   = _client;
        socket   = _socket;
        open();
        start();
    }

    public void open() {
        try {
            streamIn  = new ObjectInputStream(socket.getInputStream());
        } catch(IOException ioe) {
            System.out.println("Error getting input stream: " + ioe);
            client.stop();
        }
    }




    public void close() {
        try {
            if (streamIn != null) streamIn.close();
        } catch(IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }




    public void run() {
        while (true) {
            try {
                client.handle(streamIn.readObject().toString());
            } catch(IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.stop();
            }catch (ClassNotFoundException classNotFoundException){
                System.out.println("Class not found from received data");
            }
        }
    }


}