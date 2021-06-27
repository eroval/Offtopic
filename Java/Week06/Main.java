package Week06;

import dishes.*;
import recordables.Actor;
import recordables.Lecture;
import recordables.Movie;
import recordables.PracticalLecture;

public class Main {

    public static void main(String[] args) {

        // Recordable Task
        System.out.println("-------------------------- Task 1 ------------------------");
        {
            Actor actor = new Actor("Johny Depp");
            Lecture lecture = new Lecture(40, "Arrays");
            PracticalLecture practicalLecture = new PracticalLecture(30, "Interfaces", 10);
            Movie movie = new Movie(90, "Pirates of the Caribbean");

            movie.addActor(actor);

            System.out.println(lecture);
            System.out.println(practicalLecture);
            System.out.println(movie);
            System.out.println(movie.getActorList());

            movie.record();
            lecture.record();
            practicalLecture.record();



        }

        // Dishes Task
        System.out.println("-------------------------- Task 2 ------------------------");
        {
            FoodStuff tomato = new FoodStuff(100,"Tomato", FoodProductType.VEGETABLE, false);
            FoodStuff chickenMeat = new FoodStuff(50,"Chicken", FoodProductType.MEAT, false);
            FoodStuff garlic = new FoodStuff(150,"Garlic", FoodProductType.VEGETABLE, true);

            System.out.println(tomato);
            System.out.println(chickenMeat);
            System.out.println(garlic);
            garlic.eat();
            System.out.println("Is " + chickenMeat + " delivered " + chickenMeat.delivered(200));

            Dish pasta = new Dish(10,"Pasta", 15);
            pasta.addFoodProduct(tomato);
            pasta.addFoodProduct(chickenMeat);
            pasta.addFoodProduct(garlic);

            System.out.println(pasta);
            pasta.eat();
            System.out.println("Is " + pasta + " delivered " + pasta.delivered(9));


            Shop shop = new Shop("Billa");
            shop.addFoodStuff(tomato);
            shop.addFoodStuff(chickenMeat);

            Restaurant restaurant = new Restaurant("Happy");
            restaurant.addDish(pasta);


        }

    }
}