package recordables;

public class Lecture extends ObjectWithDuration {
    private String topic;

    public Lecture(int duration, String topic) {
        super(duration);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public void record() {
        System.out.println(this.topic);
        super.record();
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "topic='" + topic + '\'' +
                '}';
    }
}
