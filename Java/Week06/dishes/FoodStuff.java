package dishes;

public class FoodStuff extends DeliverableObject implements Eatable {
    private String name;
    private FoodProductType foodProductType;
    private boolean isSpicy;

    public FoodStuff(int maxDeliveryTime, String name, FoodProductType foodProductType, boolean isSpicy) {
        super(maxDeliveryTime);
        this.name = name;
        this.foodProductType = foodProductType;
        this.isSpicy = isSpicy;
    }

    @Override
    public void eat() {
        Eatable.super.eat();
        System.out.println((isSpicy ? "But is" : " And is not") + " Spicy!!!");
    }

    @Override
    public String toString() {
        return "FoodStuff{" +
                "name='" + name + '\'' +
                ", foodProductType=" + foodProductType +
                ", isSpicy=" + isSpicy +
                '}';
    }
}
