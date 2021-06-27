package Test1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Test1.Swimmer.SwimmerStyle;


class Sportist{
    private String name;
    private final LocalDate cardDate;
    private static double baseSalary;

    Sportist(String name, LocalDate cardDate){
        this.name=name;
        this.cardDate=cardDate;
    }

    public static void increaseSalary(double number){
        if(number<=0){
            return;
        }
        baseSalary+=number;
    }


    public static void setBaseSalary(double number){
        baseSalary=number;
    }

    public static double getBaseSalary(){
        return baseSalary;
    }

    public LocalDate getCardDate() {
        return cardDate;
    }
    
    public String getName(){
        return this.name;
    }

    public boolean isEarlier(Sportist object){
        return this.cardDate.isBefore(object.getCardDate());
    }

    public double salary(){
        return this.baseSalary+(this.baseSalary*(LocalDate.now().getYear() - this.cardDate.getYear())*0.01);
    }

    public void print(){
        System.out.println(this.name + " has a salary of " + this.salary());
    }
}

class Swimmer extends Sportist{
    public static enum SwimmerStyle{
        FREE_STYLE, 
        BREASTSTROKE, 
        BUTTERFLY,
        BACKSTROKE;
    }

    private boolean inOpenWaters;
    private SwimmerStyle style;

    Swimmer(String name, LocalDate cardDate, boolean isOpenWaters, SwimmerStyle style){
        super(name, cardDate);
        this.inOpenWaters=isOpenWaters;
        this.style=style;
    }
    public boolean hasGreaterSalary(Swimmer obj2){
        return this.salary()>obj2.salary();
    }

    public boolean isFreeStyle(){
        return this.style==SwimmerStyle.FREE_STYLE;
    }

    @Override
    public double salary(){
        return this.getBaseSalary()+(this.getBaseSalary()*(LocalDate.now().getYear() - this. getCardDate().getYear())*0.01)
        + (this.getBaseSalary()+(this.getBaseSalary()*(LocalDate.now().getYear() - this. getCardDate().getYear())*0.01))*(inOpenWaters?0.05:0);
    }

    @Override
    public void print(){
        System.out.println(this.getName() + " has a salary of " + this.salary() + ", OpenWater = " + this.inOpenWaters + " and style = " + this.style);
    }

    public SwimmerStyle getStyle(){
        return this.style;
    }

    public boolean getOpenWaters(){
        return this.inOpenWaters;
    }

}

class SwimmingCompetition{
    private LocalDate dateOfCompetition;
    private boolean inOpenWaters;
    List<Swimmer> swimmers;

    SwimmingCompetition(LocalDate dateOfCometition, boolean inOpenWaters){
        this.dateOfCompetition=dateOfCometition;
        this.inOpenWaters=inOpenWaters;
        swimmers = new ArrayList<Swimmer>();
    }

    public void addSwimmer(Swimmer obj){
        if(obj.getOpenWaters()!=this.inOpenWaters){
            return;
        }
        this.swimmers.add(obj);
    }

    public double allWinnings(SwimmerStyle style){
        if(this.swimmers.isEmpty()){return 0;}
        double Sum=0;
        for (Swimmer obj : swimmers){
            if(obj.getStyle()==style){
                Sum+=obj.salary();
            }
        }
        return Sum;
    }

    //ITERATE THROUGH LIST
    public double averageWinnings(SwimmerStyle style){
        if(this.swimmers.isEmpty()){return 0;}
        double Sum=0;
        int k=0;
        for(Swimmer obj : swimmers){
            if(obj.getStyle()==style){
                k++;
                Sum+=obj.salary();
            }
        }
        if(k==0){
            return 0;
        }
        return Sum/k;
    }

    public void print(){
        if(this.swimmers.isEmpty()){
            System.out.println("No competetitors");
            return;
        }
        System.out.println("The competetitors are:");
        for(Swimmer obj : swimmers){
            obj.print();
        }
    }
}

public class Test1{
    public static void main(String args[]){
        Sportist.setBaseSalary(500);
        Sportist a = new Sportist("Tom", LocalDate.parse("2019-02-20"));
        Sportist b = new Sportist("Hank", LocalDate.parse("2021-01-04"));
        a.print();
        System.out.println(a.getBaseSalary());
        Sportist.increaseSalary(20);
        a.print();
        System.out.println(a.getBaseSalary());
        System.out.println(a.isEarlier(b));
        System.out.println(b.isEarlier(a));
        Swimmer c = new Swimmer("Lord", LocalDate.parse("2018-01-03"),true,SwimmerStyle.BREASTSTROKE);
        Swimmer d = new Swimmer("Lard", LocalDate.parse("2018-01-05"),true,SwimmerStyle.BREASTSTROKE);
        c.print();
        SwimmingCompetition comp = new SwimmingCompetition(LocalDate.parse("2021-01-04"), true);
        comp.print();
        comp.addSwimmer(c);
        System.out.println(comp.averageWinnings(SwimmerStyle.BREASTSTROKE));
        System.out.println(comp.allWinnings(SwimmerStyle.BREASTSTROKE));
        comp.addSwimmer(d);
        System.out.println(comp.averageWinnings(SwimmerStyle.BREASTSTROKE));
        System.out.println(comp.allWinnings(SwimmerStyle.BREASTSTROKE));
    }
}