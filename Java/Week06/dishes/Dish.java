package dishes;

import java.util.ArrayList;
import java.util.List;

public class Dish extends DeliverableObject implements Eatable {
    private String name;
    private double price;
    private List<FoodStuff> foodStuffList;

    public Dish(int maxDeliveryTime, String name, double price) {
        super(maxDeliveryTime);
        this.name = name;
        this.price = price;
        this.foodStuffList = new ArrayList<>();
    }

    public void addFoodProduct(FoodStuff foodStuff) {
        this.foodStuffList.add(foodStuff);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
