package recordables;

public class PracticalLecture extends Lecture {
    private int numberOfTasks;

    public PracticalLecture(int duration, String topic, int numberOfTasks) {
        super(duration, topic);
        this.numberOfTasks = numberOfTasks;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    @Override
    public String toString() {
        return "PracticalLecture{" +
                "numberOfTasks=" + numberOfTasks +
                '}';
    }
}
