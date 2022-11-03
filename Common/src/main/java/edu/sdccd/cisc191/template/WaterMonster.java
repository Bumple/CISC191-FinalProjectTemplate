package edu.sdccd.cisc191.template;

public class WaterMonster extends Monster {
    private int sig = 1;
    private String element = " Water Type";
    public WaterMonster(){
        super();
        setElement(sig, element);
    }
    public WaterMonster(int a, int b){
        super( a,  b);
        setElement(sig, element);
    }

    public Integer[] registerHit(Integer[] attack){
       int hit = attack[0];
        if (attack[1] == 2){
            hit = (int) (hit*.8);
        }
        else if (attack[1] == 3){
            hit = (int) (hit*1.2);
        }
      return  super.registerHit(new Integer[] {hit});
    }


}
