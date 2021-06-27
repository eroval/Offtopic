package Week07;

import java.math.BigDecimal;

public abstract class SportsClub{
    private final String name;
    private double tax;
    private double salaries;

    SportsClub(String name, double tax, double salaries){
        this.name=name;
        this.tax=tax;
        this.salaries=salaries;
    }

    public double allMoneySpent(){
        return this.tax+this.salaries;
    }

    public double getSalaries(){
        return this.salaries;
    }

    public double getTax(){
        return this.tax;
    }

    public String getName(){
        return this.name;
    }

    public abstract double income();
    public abstract double totalIncome();
    public abstract SportsClub greaterIncome(SportsClub obj);
}