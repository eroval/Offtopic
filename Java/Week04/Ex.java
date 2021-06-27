import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class DeliverableItem{
    boolean deliver;
    public static double additionalPrice=0;

    DeliverableItem(boolean deliver){
        this.deliver=deliver;
    }

    DeliverableItem(final DeliverableItem obj){
        this.deliver=obj.deliver;
    }
    
    public boolean getDelivery(){
        return this.deliver;
    }

    public static double getAdditionalPrice(){
        return additionalPrice;
    }

    public static void setAdditionalPrice(double newAdditionalPrice){
        if(newAdditionalPrice<=0){
            newAdditionalPrice=1;
        }
        additionalPrice=newAdditionalPrice;
    }
}

class Material{
    private String name;
    private boolean breakable;
    
    Material(String name, boolean breakable){
        this.name=name;
        this.breakable=breakable;
    }

    Material(final Material obj){
        this.name=obj.name;
        this.breakable=obj.breakable;
    }
    
    public boolean getBreakable(){
        return this.breakable;
    }
}

abstract class Deliverable{
    DeliverableItem deliverableitem;
    abstract double deliveryPrice();

    Deliverable(boolean deliver){
        this.deliverableitem=new DeliverableItem(deliver);
    }

    DeliverableItem gItem(){
        return this.deliverableitem;
    }
}

class Document extends Deliverable{
    private double minPrice;
    
    Document(boolean deliver, double minPrice){
        super(deliver);
        if(minPrice<=0) minPrice=1;
        this.minPrice=minPrice;
    }
    
    @Override
    public double deliveryPrice(){
        return this.gItem().getDelivery()?this.minPrice+this.gItem().getAdditionalPrice():this.minPrice;
    }
    
}

class WeightedItem extends Deliverable{
    private Material material;
    private double pricePerKilo;
    private double kilo;

    WeightedItem(Material material, boolean deliver, double pricePerKilo, double weight){
        super(deliver);
        if(pricePerKilo<=0) pricePerKilo=1;
        if(weight<=0) weight=1;
        this.material=new Material(material);
        this.pricePerKilo=pricePerKilo;
        this.kilo=weight;
    }

    @Override
    public double deliveryPrice(){
        double price = this.pricePerKilo*kilo+(this.gItem().getDelivery()?this.gItem().getAdditionalPrice():0);
        return this.material.getBreakable()?price+price*0.01:price;
    }
}


public class Ex {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String materialName = bufferedReader.readLine();

        boolean isFragile = Integer.parseInt(bufferedReader.readLine().trim()) != 0;

        boolean toClientsAddress = Integer.parseInt(bufferedReader.readLine().trim()) != 0;

        double minPrice = Double.parseDouble(bufferedReader.readLine().trim());

        double weight = Double.parseDouble(bufferedReader.readLine().trim());
        
        double pricePerKg = Double.parseDouble(bufferedReader.readLine().trim());

        double additionalPrice = Double.parseDouble(bufferedReader.readLine().trim());

        // Use the set method to give the value additionalPrice to the additional price when the shipment has to be delivered to the client's address.
        DeliverableItem.setAdditionalPrice(additionalPrice);
        // Create an object of type Material using the constructor with two parameters. Pass materialName and isFragile as arguments.
        Material material = new Material(materialName,isFragile);
        // Create a reference of type Deliverable.
        Deliverable obj;
        // Use the reference of type Deliverable to create an object of type Document. Use toClientsAddress and minPrice to the constructor of the Document.
        obj = new Document(toClientsAddress,minPrice);
        // Print on the console the result of calling the shippingPrice() method: the method that has to return the total shipping price for the delivery.
        System.out.println(obj.deliveryPrice());
        // Use the reference of type Deliverable to create an object of type WeightedItem. Use weight and pricePerKg to pass them to the constructor of the WeightedItem.
        obj = new WeightedItem(material, toClientsAddress, pricePerKg, weight);
        // Print on the console the result of calling the shippingPrice() method: the method that has to return the total shipping price for the delivery.
        System.out.println(obj.deliveryPrice());
        bufferedReader.close();
    }
}
