package dishes;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String name;
    private List<FoodStuff> foodStuffList;

    public Shop(String name) {
        this.name = name;
        this.foodStuffList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<FoodStuff> getFoodStuffList() {
        return foodStuffList;
    }

    public void addFoodStuff(FoodStuff foodStuff) {
        this.foodStuffList.add(foodStuff);
    }
    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", foodStuffList=" + foodStuffList +
                '}';
    }
}
