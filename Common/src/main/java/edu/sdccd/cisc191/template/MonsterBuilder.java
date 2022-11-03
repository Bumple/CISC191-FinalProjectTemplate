package edu.sdccd.cisc191.template;

import java.util.HashMap;
import java.util.Random;

public class MonsterBuilder {
    public MonsterBuilder(){

    }
    public Monster customMonster(int a, int b, int c){
        Monster bigBoy = new Monster();
        if(a == 1){
            bigBoy = new WaterMonster(b,c);
        }
        else if(a == 2){
            bigBoy = new FireMonster(b,c);
        }
        else if(a == 3){
            bigBoy = new EarthMonster(b,c);
        }
        return bigBoy;
    }
    public Monster RandomMonster() {
        HashMap<Integer, Integer> builds = new HashMap<>();
        for (int i = 0; i < 3; i++){
            Random randy = new Random();
            int roll = randy.nextInt(3)+1;
            builds.put(i,roll);     //maps a random number 3 times to sequential keys and builds new monster with random traits
        }
        return customMonster(builds.get(0),builds.get(1),builds.get(2));
    }

}
