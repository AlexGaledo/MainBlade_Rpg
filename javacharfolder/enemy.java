package javacharfolder;

public class enemy{
    private final int health;
    private final String name;
    private enemymoves skill;
    private enemymoves ult;
    private String asset;

    public enemy(String name,int health, enemymoves skill,enemymoves ult,String asset){
        this.name = name;
        this.health = health;
        this.skill = skill;
        this.ult = ult;
        this.asset = asset;
    }

    public String getname(){
        return name;
    }

    public int gethealth(){
        return health;
    }

    public String getasset(){
        return asset;
    }

    public enemymoves getskill(){
        return skill;
    }

    public enemymoves getult(){
        return ult;
    }


}