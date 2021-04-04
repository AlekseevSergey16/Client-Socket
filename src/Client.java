import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static int SERVER_PORT = 8081;
    private static InetAddress SERVER_ADDRESS;

    static {
        try {
            SERVER_ADDRESS = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        if (args.length == 2) {
            SERVER_ADDRESS = InetAddress.getByName(args[0]);
            SERVER_PORT = Integer.parseInt(args[1]);
        }

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            while (true) {
                String msg = reader.readLine();
                if (msg.equals("")) {
                    break;
                }
                printWriter.println(msg);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
