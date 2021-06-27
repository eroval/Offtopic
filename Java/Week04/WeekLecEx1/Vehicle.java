package WeekLecEx1;

public class Vehicle extends Goods{
    private double materialCosts;
    private double salariesCosts;

    Vehicle(double overchage, double materialCosts, double salariesCosts){
        super(overchage);
        this.materialCosts=materialCosts;
        this.salariesCosts=salariesCosts;   
    }

    @Override
    public double productionPrice() {
        return this.materialCosts + this.salariesCosts;
    }
}
