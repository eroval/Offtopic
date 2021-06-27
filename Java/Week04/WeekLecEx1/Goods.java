package WeekLecEx1;

public abstract class Goods implements CanBeSold{
    private double overcharge;

    Goods(double overcharge){
        this.overcharge=overcharge;
    }    
    
    @Override
    public double sellingPrice() {
        return productionPrice() + this.overcharge;
    }
}
