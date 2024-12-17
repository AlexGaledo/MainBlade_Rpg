package javacharfolder;
public class enemymoveset {
    enemymoves[] enemymoveset = new enemymoves[10];
    public enemymoveset(){
        enemymoveset[0] = new enemymoves("Robbery",100, ".png");
        enemymoveset[1] = new enemymoves("Dark Prince", 500,".png");
        enemymoveset[2] = new enemymoves("Cursed Incantation", 150, ".png");
        enemymoveset[3] = new enemymoves("Dark Lord Summon", 0, ".png");
        enemymoveset[4] = new enemymoves("Spear of Light", 200, ".png");
        enemymoveset[5] = new enemymoves("Fallen Angel", 700, ".png");
        enemymoveset[6] = new enemymoves("Slime Ball", 20, ".png");
        enemymoveset[7] = new enemymoves("Slime Barrage",200, ".png");
        enemymoveset[8] = new enemymoves("Dark Lord Judgement", 200, ".png");
        enemymoveset[9] = new enemymoves("Sinful Shell", 2000, ".png");
    }
}
