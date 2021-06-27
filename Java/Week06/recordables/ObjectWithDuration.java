package recordables;

public class ObjectWithDuration implements Recordable{
    private int duration;

    public ObjectWithDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void record() {
        for (int i = 1; i <= this.duration; i++) {
            System.out.println("Recording " + i + " minute!");
        }
    }

    @Override
    public String toString() {
        return "ObjectWithDuration{" +
                "duration=" + duration +
                '}';
    }
}
