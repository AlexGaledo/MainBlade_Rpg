package javacharfolder;

public class characterpool {
    character[] characterpool = new character[10];
    
    public characterpool() {
        charactermoveset cms = new charactermoveset();
        characterpool[0] = new character("Albert", 1, 1000, "Warrior",
        cms.movesetlist[0], cms.movesetlist[1],false,"javacharfolder/assets/Albert.png");
        characterpool[1] = new character("Luna",1,500,"Mage",
        cms.movesetlist[2],cms.movesetlist[3],false,"Luna.png");
        characterpool[2] = new character("Balroth",1,500,"Vanguard",
        cms.movesetlist[4],cms.movesetlist[5],false,"javacharfolder/assets/Balroth.png");
    }
}
