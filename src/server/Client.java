package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A thread that handles connection to clients.
 * @author TG Chipoyera 220150124
 * @version P02
 * @see Socket
 */
public class Client extends Thread{
    // Properties
    private final Socket ClientSocket;
    private final PrintWriter Out;
    private final Scanner In;

    private enum Commands {
        START,
        REQUEST,
        DONE
    }

    /**
     * @param ClientSocket The connection socket to the client.
     * @throws IOException IOException – if an I/O error occurs when creating the output stream or if the socket is not connected.
     */
    public Client(Socket ClientSocket) throws IOException {
        // Saving the client socket
        this.ClientSocket = ClientSocket;

        // Creating the output communication channel to the client
        this.Out = new PrintWriter(this.ClientSocket.getOutputStream(), true);

        // Creating the input communication channel from the client
        this.In = new Scanner(
            new InputStreamReader(
                new BufferedInputStream(this.ClientSocket.getInputStream()),
                StandardCharsets.UTF_8
            )
        );

        // Notifying the console of the new client
        System.out.println(String.format(
                "New Client : %s",
                this.ClientSocket.getInetAddress().getHostName()
        ));
    }

    /**
     * Writes and automatically flushes a message to the client
     * @param message the message to be sent
     */
    private void WriteMessage(String message){
        this.Out.println(message);
        this.Out.flush();
    }

    /**
     * Get's the message from the client
     * @return
     */
    private String GetMessage(){return this.In.nextLine();}

    /**
     * @return the client's connection socket
     */
    public Socket getSocket(){
        return this.ClientSocket;
    }

    /**
     *
     * @param response the response from the client
     * @return Commands
     * @throws IllegalArgumentException if the command from the response is unidentified.
     */
    private Commands GetCommand(String response) throws IllegalArgumentException{
        return Commands.valueOf(response.split(" ")[0]);
    }

    @Override
    public void run(){
        this.WriteMessage("HELLO - you may make 4 requests and I’ll try to detect your language");

        boolean cont = true;
        int queriesAnswered = 0;
        int maxQueries = 4;
        while (cont && queriesAnswered < maxQueries){
            //Making the response lowercase to make checks easier
            String response = this.GetMessage().trim();

            Commands command;
            try{
                command = this.GetCommand(response);
            }catch (IllegalArgumentException e){
                this.WriteMessage(String.format("'%s' | COMMAND UNIDENTIFIED", response));
                // Moving to the next iteration
                continue;
            }

            switch (command){
                case START -> this.WriteMessage("REQUEST or DONE");

                case REQUEST -> {
                    System.out.println(response.toLowerCase().split(" ")[1]);

                    // Checking for IsiZulu
                    if(response.toLowerCase().contains("ngiyabonga") || response.toLowerCase().contains("mina")){
                        this.WriteMessage("I detect some Zulu here.");
                    }
                    // Checking for English/Afrikaans
                    else if(response.toLowerCase().split(" ")[1].equals("is")){
                        this.WriteMessage("Anglais?");
                    }
                    // Checking for SeSotho
                    else if(response.toLowerCase().contains("dumela")){
                        this.WriteMessage("I greet you in Sotho!");
                    }else{
                        this.WriteMessage("I'm still learning");
                    }

                    queriesAnswered++;
                }

                case DONE -> cont = false;
            }
        }

        // Finished the session
        this.WriteMessage(String.format("GOOD BYE - %d queries answered", queriesAnswered));
    }
}
