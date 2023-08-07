import server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            Server myServer = new Server(8888);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}