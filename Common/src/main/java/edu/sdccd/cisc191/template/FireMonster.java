package edu.sdccd.cisc191.template;

public class FireMonster extends Monster {
    private int sig = 2;
    private String element = " Fire type";
    public FireMonster(){
        super();
        setElement(sig, element);
    }
    public FireMonster(int a, int b){
        super( a,  b);
        setElement(sig, element);
    }
    public Integer[] registerHit(Integer[] attack){
        int hit = attack[0];
        if (attack[1] == 3){
            hit = (int) (hit*.8);
        }
        if (attack[1] == 1){
            hit = (int) (hit*1.2);
        }
        return  super.registerHit(new Integer[] {hit});
    }
 /* public int registerHit(int[] attack){
      double a = attack[0];
      double b = attack[1];
      if (attack[1] == 3){
          b = .8;
      }
      else if (attack[1] == 1){
          b= 1.2;
      }
      else{
          b = 1;
      }
      Double[] hit = new Double[]{a,b};
      return super.registerHit(hit);
  }


 /* public int registerHit(int[] attack){
      int a = attack[0];
      int b = attack[1];
      if (attack[1] == 1){
          b = 8;
      }
      else if (attack[1] == 3){
          b= 0;
      }
      else{
          b = 1;
      }
      int[] hit = new int[]{a,b};
      return super.registerHit(hit);
  } */

}