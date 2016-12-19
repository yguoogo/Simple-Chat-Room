package ChatRoom2;

/**
 * Created by yuguanxu on 12/17/16.
 */
import java.net.*;
import java.io.*;

public class ChatServerThread extends Thread
{
    private ChatServer       server    = null;
    private Socket           socket    = null;
    private int              ID        = -1;
    private ObjectInputStream  streamIn  =  null;
    private ObjectOutputStream streamOut = null;


    public ChatServerThread(ChatServer _server, Socket _socket) {
        super();
        server = _server;
        socket = _socket;
        ID     = socket.getPort();
    }




    public void send(String msg) {
        try {
            streamOut.writeObject(msg);
            streamOut.flush();
        } catch(IOException ioe) {
            System.out.println(ID + " ERROR sending: " + ioe.getMessage());
            server.remove(ID);
            stop();
        }
    }




    public int getID()
    {  return ID;
    }


    public void run()
    {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                server.handle(ID, streamIn.readObject().toString());
            } catch(IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                stop();
            } catch (ClassNotFoundException classNotFoundException){
                System.out.println("Class not found from received data");
            }
        }
    }
    public void open() throws IOException
    {
        streamIn = new ObjectInputStream(socket.getInputStream());
        streamOut = new ObjectOutputStream(socket.getOutputStream());
    }
    public void close() throws IOException
    {  if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
        if (streamOut != null) streamOut.close();
    }
}