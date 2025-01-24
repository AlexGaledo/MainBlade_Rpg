package javacharfolder;
public class enemymoves{
    private final String name;
    private final int rawdamage;
    private final String skillasset;

    public enemymoves(String name,int rawdamage,String asset){
        this.name = name;
        this.rawdamage = rawdamage;
        this.skillasset = asset;
    }

    public String getmovename(){
        return name;
    }

    public int getrawdamage(){
        return rawdamage;
    }

    public String getasset(){
        return skillasset;
    }
    //test commit
}