package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.Random;

public class Monster implements Attacks, Serializable {
    private int critChance = 0;
    private boolean alive = true;
    private boolean vampChance = false;
    private boolean blur = false;
    private Integer HP = 0;
    private Integer AP = 0;
    private Integer sig = 0;
    private String perk;
    private String element;
    public Monster(){
    }
    public Monster(int a, int b){
        setPerk(a);
        setType(b);
    }
    public Integer[] strongAttack(){
        return new Integer[]{AP,sig};
    }

    public Integer[] lightAttack() {
        int hit = AP - (AP / 4);
        Random randy = new Random();
        int roll = randy.nextInt(6-this.critChance);
        if (roll == 1) {
            hit = (AP*2);
        }
        hit = (Integer) hit;
        return new Integer[]{hit, sig};
    }
    public Integer[] scratch(){
        int hit = (int) (AP*.9);
        return new Integer[] {hit, 0};
    }
    public Integer lifeSteal( Integer[] lost){
        if (vampChance) {
            this.HP += (lost[1]/3);
        }
        return lost[1];
    }
    public Integer[] registerHit(Integer[] attack){           //new attempt moves type num to subclasses for attack and hit reg
        int hit = attack[0];
        if (this.blur){
            Random randy = new Random();
            int a = randy.nextInt(6);
            if (a == 2){
                hit = 0;
            }
        }
        this.HP -= hit;
        return new Integer[]{this.HP, hit};
    }
    public void setType(int ap){
        if(ap == 1){
            this.HP=600;
            this.AP=60;
        }
        else if(ap == 2){
            this.HP=500;
            this.AP=70;
        }
        else{
            this.HP=300;
            this.AP=100;
        }
    }
    public void setPerk(int p) {
        if (p == 1) {
            vampChance = true;
            perk = " heals on hit";
        }
        else if (p == 3) {
            critChance = 5;
            perk = " is able to crit";
        }
        else  {
            blur = true;
            perk = " easy to miss";
        }
    }
    public Integer getAP(){
        return this.AP;
    }
    public Integer getHP(){
        return this.HP;
    }
    public void setElement(int a, String b){
        this.sig = a;
        this.element= b;
    }
    public String getElement(){
        return this.element;
    }
    public int getSig(){
        return this.sig;
    }


    public String getProperties(){
        return ("monster is a" + this.element +", deals \n"+ getAP()+ " damage, and"+ perk + ".");
    }
}
