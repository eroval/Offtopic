package Week07;

import java.util.ArrayList;
import java.util.List;

import Week07.Dancer.DancerStyle;

public class DancingClub extends SportsClub{
    private double monthlyTax;
    List<Dancer> dancers;

    DancingClub(String name, double tax, double salaries, double monthlyTax){
        super(name,tax,salaries);
        this.monthlyTax=monthlyTax;
        this.dancers = new ArrayList<Dancer>();
    }

    public void addDancer(Dancer obj){
        this.dancers.add(obj);
    }

    public double income(){
        double Sum=this.monthlyTax*this.dancers.size();
        return Sum;
    }

    public double totalIncome(){
        double Sum=this.monthlyTax*this.dancers.size();
        return Sum-this.getSalaries()-this.getTax();
    }

    public double incomeBasedOnStyle(DancerStyle style){
        double Sum=0;
        for(Dancer obj : dancers){
            if(obj.getStyle()==style){
                Sum+=this.getMonthlyTax();
            }
        }
        return Sum;
    }

    public SportsClub greaterIncome(SportsClub obj){
        if(this.totalIncome()>obj.totalIncome()){
            return this;
        }
        else return obj;
    }

    public double getMonthlyTax(){
        return this.monthlyTax;
    }

    public int getNumberOfDancers(){
        return this.dancers.size();
    }

    public void print(){
        System.out.println(this.getName() + " has " + this.dancers.size() + " dancers with monthly tax of " + this.getMonthlyTax());
        System.out.println("pays salaries of " + this.getSalaries() + " and taxes equal to " + this.getTax());
        System.out.println("Income without Tax: " + this.income() + " ; Income with Tax: " + this.totalIncome());
        System.out.println("\n");
    }
}