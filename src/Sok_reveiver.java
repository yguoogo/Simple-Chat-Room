import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by yuguanxu on 12/10/16.
 */
public class Sok_reveiver {
    public static void main(String[] args) throws Exception {
        Sok_reveiver sok_reveiver = new Sok_reveiver();
        sok_reveiver.run();
    }
    public void run() throws Exception {
        Socket socket = new Socket("localhost",444);
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("Hello to Server, message from receiver");

        InputStreamReader IR = new InputStreamReader(socket.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String message = BR.readLine();
        System.out.println(message);
    }
}
