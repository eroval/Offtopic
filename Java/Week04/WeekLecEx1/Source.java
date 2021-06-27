package WeekLecEx1;

public class Source {
    public static void main(String args[]){
        Goods goods;
        Vehicle vehicle = new Vehicle(1000, 20, 30);

        System.out.println(vehicle.sellingPrice());
        goods = vehicle;
        System.out.println(goods.productionPrice());
        System.out.println(goods.sellingPrice());
    
        CanBeSold canBeSold=vehicle;
        System.out.println(canBeSold.productionPrice());
        System.out.println(canBeSold.sellingPrice());

        Engineer engineer = new Engineer(200,40);
        TaxiDriver taxiDriver = new TaxiDriver("T678",2,300);

        Provider provider = engineer;
        System.out.println(provider.income());
        provider=taxiDriver;
        System.out.println(provider.income());
    }
}
