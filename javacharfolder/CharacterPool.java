package javacharfolder;

public class CharacterPool {
    Character[] characterpool = new Character[10];
    
    public CharacterPool() {
        CharacterMoveset cms = new CharacterMoveset();
        characterpool[0] = new Character("Albert", 1, 1000, "Warrior",
        cms.movesetlist[0], cms.movesetlist[1]);
        characterpool[1] = new Character("Luna",1,500,"Mage",
        cms.movesetlist[2],cms.movesetlist[3]);
        characterpool[2] = new Character("Gideon",1,500,"Vanguard",
        cms.movesetlist[4],cms.movesetlist[5]);
    }
}
