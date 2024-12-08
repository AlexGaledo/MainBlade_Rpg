package javacharfolder;

public class MainBlade {
    public static void main(String[] args) {
        CharacterPool charlist = new CharacterPool();
        System.out.println(charlist.characterpool[0].getname());
        System.out.println(charlist.characterpool[0].getlevel());
        for(var x = 0; x < 100 ; x++){charlist.characterpool[0].levelup();}
        System.out.println(charlist.characterpool[0].getlevel());

    }
}
