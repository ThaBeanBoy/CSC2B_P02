package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Language detection server
 * @author TG Chipoyera 220150124
 * @version P02
 * @see ServerSocket
 */
public class Server {
    public Server(int port) throws IOException {
        try(ServerSocket server = new ServerSocket(port);){
            System.out.printf("Running on port %d\n", port);
            while (true){
                new Client(server.accept()).start();
            }
        }

    }
}
