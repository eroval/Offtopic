package dishes;

public class DeliverableObject implements Deliverable{

    private int maxDeliveryTime;

    public DeliverableObject(int maxDeliveryTime) {
        this.maxDeliveryTime = maxDeliveryTime;
    }

    @Override
    public boolean delivered(int deliveryTime) {
        return deliveryTime <= this.maxDeliveryTime;
    }

    @Override
    public String toString() {
        return "DeliverableObject{" +
                "maxDeliveryTime=" + maxDeliveryTime +
                '}';
    }
}
