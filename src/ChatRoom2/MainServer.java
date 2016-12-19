package ChatRoom2;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by yuguanxu on 12/18/16.
 */
public class MainServer implements Runnable{

    private List allPortList;
    private ServerSocket mainServer = null;
    private Socket mainSocket = null;

    public MainServer(List portList, int mainPort){
        allPortList = portList;
        try {
            mainServer = new ServerSocket(mainPort,100);
        }catch (IOException ioException){
            System.out.println("Can not bind to port " + mainPort + ": " + ioException.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while(true){
                mainSocket = mainServer.accept();
            }
        }catch (IOException ioe){
            System.out.println("Server accept error: " + ioe);
        }
    }
}
