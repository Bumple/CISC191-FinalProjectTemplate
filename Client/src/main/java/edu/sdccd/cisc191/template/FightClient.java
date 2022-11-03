package edu.sdccd.cisc191.template;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

public class FightClient {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection() throws IOException {
        clientSocket = new Socket("127.0.0.1", 1077);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public updatedInfo updateAll() throws Exception {
        updatedInfo newInfo = (updatedInfo) in.readObject();
        return newInfo;
    }
    public String readStats() throws IOException, ClassNotFoundException {
        return (String)in.readObject();
    }
    public String[] readElements() throws Exception {        //used to set names of attacks to more specific names
        String[] newAttNames = (String[]) in.readObject();   // tailored to the element of the monster
        return newAttNames;
    }
    public Integer[] checkEnding() throws Exception{
        Integer[] note = (Integer[]) in.readObject();
        return note;
    }
    public void sendHit(Integer hitInstruction) throws Exception {
        out.writeObject(hitInstruction);
        out.flush();
    }


    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

} //end class FightClient
