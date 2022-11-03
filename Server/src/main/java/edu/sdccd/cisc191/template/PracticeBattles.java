package edu.sdccd.cisc191.template;

public class PracticeBattles {
    // use to make any monster combos you want fight eachother
    public static void spar(Monster first, Monster second) {
        System.out.println("First " + first.getProperties() + " and has " + first.getHP() + " health");
        System.out.println("Second " + second.getProperties() + " and has " + second.getHP() + " health");
        while (first.getHP() > 0 && second.getHP() > 0){
             first.lifeSteal(second.registerHit(first.strongAttack()));
            System.out.println("First monster attack resulted in second having" + second.getHP() + " health" );
            second.lifeSteal(first.registerHit(second.strongAttack()));
            System.out.println("Second monster attack resulted in first having" + first.getHP() + " health" );
        }
    }

}
