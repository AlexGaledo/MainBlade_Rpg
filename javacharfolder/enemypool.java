package javacharfolder;
public class enemypool {
    enemy[] enemypool = new enemy[5];

    public enemypool(){
        enemymoveset ems = new enemymoveset();
        enemypool[0] = new enemy("bandit",1000,ems.enemymoveset[0],ems.enemymoveset[1],"bandit.png");
        enemypool[1] = new enemy("dark worshipper", 2000,ems.enemymoveset[2],ems.enemymoveset[3],"darkworship.png");
        enemypool[2] = new enemy("Uriel",4000,ems.enemymoveset[4],ems.enemymoveset[5],"uriel.png");
        enemypool[3] = new enemy("Giant slime",1500,ems.enemymoveset[6],ems.enemymoveset[7],"giantslime.png");
        enemypool[4] = new enemy("Satanael",5000,ems.enemymoveset[8],ems.enemymoveset[9],"satanael.png");
    }
}
