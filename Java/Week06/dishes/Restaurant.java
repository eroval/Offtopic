package dishes;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Dish> dishesList;

    public Restaurant(String name) {
        this.name = name;
        this.dishesList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Dish> getDishesList() {
        return dishesList;
    }

    public void addDish(Dish dish) {
        this.dishesList.add(dish);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", dishesList=" + dishesList +
                '}';
    }
}
