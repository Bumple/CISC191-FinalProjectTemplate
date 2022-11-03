package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class updatedInfo implements Serializable {
    private Integer enemyHealth;
    private Integer myHealth;
    private Integer enemyAttack;
    private Integer myAttack;

    public updatedInfo(Integer health, Integer myHit, Integer baddyHealth, Integer badHit){
        myHealth = health;
        enemyHealth = baddyHealth;
        myAttack = myHit;
        enemyAttack = badHit;
    }

    public Integer getEnemyHealth() {
        return enemyHealth;
    }

    public Integer getEnemyAttackResults() {
        return enemyAttack;
    }

    public Integer getMyHPandAP() {
        return myHealth;
    }

    public Integer getMyAttackResults() {
        return myAttack;
    }

}
