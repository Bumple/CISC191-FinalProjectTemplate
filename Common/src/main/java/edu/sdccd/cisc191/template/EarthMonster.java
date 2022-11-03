package edu.sdccd.cisc191.template;

public class EarthMonster extends Monster {
    private int sig = 3;
    private String element = "n Earth type";
    public EarthMonster(){
        super();
        setElement(sig, element);
    }
    public EarthMonster(int a, int b){
        super( a,  b);
        setElement(sig, element);
    }
    public Integer[] registerHit(Integer[] attack){
        int hit = attack[0];
        if (attack[1] == 1){
            hit = (int) (hit*.8);
        }
        else if (attack[1] == 2){
            hit = (int) (hit*1.2);
        }

        return  super.registerHit(new Integer[]{hit});
    }


}