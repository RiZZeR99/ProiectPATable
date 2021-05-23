package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionToServer {
    public ConnectionToServer(String address, int port) {
        try (Socket socket = new Socket(address, port); PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            //code

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
