package javacharfolder;

public class characterpool {
    character[] characterpool = new character[10];
    
    public characterpool() {
        charactermoveset cms = new charactermoveset();
        characterpool[0] = new character("Albert", 1, 1000, "Warrior",
        cms.movesetlist[0], cms.movesetlist[1],false,"javacharfolder/assets/Albert.png");
        characterpool[1] = new character("Luna",1,500,"Mage",
        cms.movesetlist[2],cms.movesetlist[3],false,"javacharfolder/assets/Luna.png");
        characterpool[2] = new character("Balroth",1,500,"Vanguard",
        cms.movesetlist[4],cms.movesetlist[5],false,"javacharfolder/assets/Balroth.png");
        characterpool[3] = new character("Placidussax",1,500,"Beast",
        cms.movesetlist[6],cms.movesetlist[7],false,"javacharfolder/assets/Placidussax.png");
        characterpool[4] = new character("Cu Chulainn",1,500,"Warrior",
        cms.movesetlist[8],cms.movesetlist[9],false,"javacharfolder/assets/CuChulainn.png");
        characterpool[5] = new character("Artemis",1,500,"Vanguard",
        cms.movesetlist[10],cms.movesetlist[11],false,"javacharfolder/assets/Artemis.png");
        characterpool[6] = new character("Ezekiel",1,500,"Trickster",
        cms.movesetlist[12],cms.movesetlist[13],false,"javacharfolder/assets/Ezekiel.png");
        characterpool[7] = new character("Cerberus",1,500,"Beast",
        cms.movesetlist[14],cms.movesetlist[15],false,"javacharfolder/assets/Cerberus.png");
        characterpool[8] = new character("Chrysarix",1,500,"Mage",
        cms.movesetlist[16],cms.movesetlist[17],false,"javacharfolder/assets/Chrysarix.png");
        characterpool[9] = new character("Epsilon",1,500,"Trickster",
        cms.movesetlist[18],cms.movesetlist[19],false,"javacharfolder/assets/Epsilon.png");
    }

    public character[] getcpool(){
        return characterpool;
    }
}
