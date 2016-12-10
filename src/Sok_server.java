import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yuguanxu on 12/10/16.
 */
public class Sok_server {
    public static void main(String[] args) throws Exception{
        Sok_server server = new Sok_server();
        server.run();
    }

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(444);
        Socket socket = serverSocket.accept();
        InputStreamReader IR = new InputStreamReader(socket.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String message = BR.readLine();
        System.out.println(message);

        if(message != null){
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Hello from server, message received");
        }
    }
}
