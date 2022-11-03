package edu.sdccd.cisc191.template;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class FightServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerSocket monstSocket;
    private Socket monsterReciever;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String[] clientAttTypes;
    private Monster clientMonster;
    private Monster serverMonster;
    private Integer[] servHit;
    private updatedInfo upInfo;

    private MonsterBuilder builder = new MonsterBuilder();


    public void getMonster(int monstPort) throws Exception {

        monstSocket = new ServerSocket(monstPort);
        monsterReciever = monstSocket.accept();
        monstSocket.close();
       ObjectInputStream monstIn = new ObjectInputStream(monsterReciever.getInputStream());
        clientMonster = (Monster) monstIn.readObject();
        monstIn.close();
        System.out.println(clientMonster.getProperties());
        System.out.println(clientMonster.getElement());
        clientAttTypes =  new String[]{"Strong","light"};
        if(clientMonster.getSig()== 2) {
            clientAttTypes[0] = "Fire Breath";
            clientAttTypes[1] = "Lava Spit";
        } else if (clientMonster.getSig()== 1) {
            clientAttTypes[0] = "Splash";
            clientAttTypes[1] = "Water Jet";
        } else if (clientMonster.getSig()== 3) {
            clientAttTypes[0] = "Boulder Throw";
            clientAttTypes[1] = "Obsidian Quills";
        }
        System.out.println(clientAttTypes[0] + clientAttTypes[1]);
    }

    public void start(int host) throws Exception{
        serverSocket = new ServerSocket(host);
        clientSocket = serverSocket.accept();
        serverSocket.close();
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        serverMonster = builder.RandomMonster();
        out.writeObject(clientAttTypes);  //renames the strong and light attacks to appropriate names for element
        out.flush();
        out.writeObject(serverMonster.getProperties()); //sends info to set label to enemy stats
        out.flush();
        out.writeObject(clientMonster.getHP().toString());
        out.writeObject(clientMonster.getAP().toString());
        out.writeObject(serverMonster.getHP().toString());
        //System.out.println("sent");
    }

    public void fight() throws IOException, ClassNotFoundException {
        while(serverMonster.getHP() > 0 && clientMonster.getHP() > 0){
            Integer randNum = new Random().nextInt(3)+1;
            Integer hitSelected = (Integer) in.readObject();
            Integer clientResults=  chooseHit(clientMonster,serverMonster, hitSelected);
            Integer serverResults = chooseHit(serverMonster, clientMonster, randNum);
            updatedInfo info = new updatedInfo(clientMonster.getHP(),clientResults,
                    serverMonster.getHP(),serverResults);
            out.writeObject(info);
            out.flush();
            if(serverMonster.getHP() > 0 && clientMonster.getHP() > 0){
                out.writeObject(new Integer[]{0, 0});
                out.flush();
            }
        }
        if (clientMonster.getHP() > serverMonster.getHP()){
            out.writeObject(new Integer[]{1,1});
            out.flush();
        }
        else{
            out.writeObject(new Integer[]{1,0});
            out.flush();
        }
    }
    public Integer chooseHit(Monster hittingMonster, Monster victim, Integer roll){
        Integer landed = new Integer(0);
       if (roll == 1){
            landed = hittingMonster.lifeSteal(victim.registerHit(hittingMonster.strongAttack()));
       }
        else if (roll == 2) {
           landed = hittingMonster.lifeSteal(victim.registerHit(hittingMonster.lightAttack()));
       }
        else  if (roll == 3) {
           landed = hittingMonster.lifeSteal(victim.registerHit(hittingMonster.scratch()));
       }
        return landed;
    }


    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        FightServer server = new FightServer();
        try {
            server.getMonster(1099);
            server.start(1077);
            server.fight();

           // server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Server
