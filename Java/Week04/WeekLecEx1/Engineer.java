package WeekLecEx1;

import java.util.ArrayList;
import java.util.List;

public class Engineer implements Provider{
    private double salaryPerHour;
    private double hours;
    List<CanBeSold> canBeSoldList;

    Engineer(double salaryPerHour,  double hours){
        this.salaryPerHour=salaryPerHour;
        this.hours=hours;
        this.canBeSoldList=new ArrayList<>();
    }
    
    @Override
    public double income(){
        return this.hours*salaryPerHour;
    };
}
