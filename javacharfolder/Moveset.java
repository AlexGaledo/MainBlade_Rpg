package javacharfolder;
public class Moveset {
    private String name;
    private int rawdamage;
    private String skillasset;

    public Moveset(String name, int rawdamage, String skillasset){
        this.name = name;
        this.rawdamage = rawdamage;
        this.skillasset = skillasset;
    }

    public String getmovename(){
        return name;
    }
    
    public int getrawdamage(){
        return rawdamage;
    }

    public String getskillasset(){
        return skillasset;
    }

    public void setskillasset(String filepath){
        this.skillasset = filepath;
    }

}
