package dishes;

public interface Eatable {
    default void eat() {
        System.out.println("Delicious!!!");
    }
}
