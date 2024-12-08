package javacharfolder;

public class CharacterMoveset {
    Moveset[] movesetlist = new Moveset[10];

    public CharacterMoveset(){
        movesetlist[0] = new Moveset("Hyperslash", 50, ".png");
        movesetlist[1] = new Moveset("Holy Radiance", 100, ".png");
        movesetlist[2] = new Moveset("Magic Impact", 200, ".png");
        movesetlist[3] = new Moveset("Arcana Burst", 300, ".png");
        movesetlist[4] = new Moveset("Ground Slam", 50, ".png");
        movesetlist[5] = new Moveset("Bulldoze", 100, ".png");
    }
}
