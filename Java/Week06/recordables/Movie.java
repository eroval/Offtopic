package recordables;

import java.util.ArrayList;
import java.util.List;

public class Movie extends ObjectWithDuration {
    private String name;
    private List<Actor> actorList;

    public Movie(int duration, String name) {
        super(duration);
        this.name = name;
        this.actorList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void addActor(Actor actor) {
        this.actorList.add(actor);
    }

    @Override
    public void record() {
        System.out.println(this.name);
        super.record();
    }


    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                '}';
    }
}
