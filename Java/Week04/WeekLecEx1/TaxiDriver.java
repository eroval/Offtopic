package WeekLecEx1;

public class TaxiDriver extends RegisteredProvider{
    private double km;
    private double pricePerKm;

    TaxiDriver(String regNumber,double km, double pricePerKm){
        super(regNumber);
        this.km=km;
        this.pricePerKm=pricePerKm;
    }
    
    @Override
    public double income(){
        return this.pricePerKm*this.km;
    };
}
