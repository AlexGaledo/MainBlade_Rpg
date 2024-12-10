package javacharfolder;

public class charactermoveset {
    moveset[] movesetlist = new moveset[10];

    public charactermoveset(){
        movesetlist[0] = new moveset("Hyperslash", 50, ".png");
        movesetlist[1] = new moveset("Red Knight Assault", 100, ".png");
        movesetlist[2] = new moveset("Magic Impact", 200, ".png");
        movesetlist[3] = new moveset("Arcana Burst", 300, ".png");
        movesetlist[4] = new moveset("Fissure", 50, ".png");
        movesetlist[5] = new moveset("Asterius Hell", 100, ".png");
    }
}
