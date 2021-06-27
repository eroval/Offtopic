package Week07;

import java.time.LocalDate;

public class Dancer{
    public static enum DancerStyle{
        LATINO,
        BALLET, 
        TRADITIONAL;
    }
    private String name;
    private LocalDate cardDate;
    private static int neededYears;
    private DancerStyle style;

    Dancer(String name,LocalDate cardDate, DancerStyle style){
        this.name=name;
        this.cardDate=cardDate;
        this.style=style;
    }

    public boolean isProfessional(){
        return LocalDate.now().getYear()-getCardDate().getYear()>=neededYears;
    }

    public int getYearsOfExperience(){
        return LocalDate.now().getYear()-this.getCardDate().getYear();
    }

    public boolean withGreaterExperience(Dancer obj){
        if(this.getCardDate().getYear()<obj.getCardDate().getYear()){
            return true;
        }
        else {
            if(this.getCardDate().getYear()>obj.getCardDate().getYear()){
                return false;
            }
            else return this.getCardDate().getDayOfYear()<obj.getCardDate().getDayOfYear();
        }
    }

    public static void setNeededYears(int number){
        neededYears=number;
    }

    public static void increaseNeededYears(int number){
        neededYears+=number;
    }

    public LocalDate getCardDate(){
        return this.cardDate;
    }

    public DancerStyle getStyle(){
        return this.style;
    }

    public String getName(){
        return this.name;
    }

    static public int staticgetNeededYears(){
        return neededYears;
    }

    public void print(){
        System.out.println(this.getName() + " with years of experience: " + this.getYearsOfExperience() + " is professional = " + this.isProfessional());
    }
}
