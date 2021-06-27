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

class Manufacturer{
    private String name;
    private boolean warranty;
    
    Manufacturer(String name, boolean warranty){
        this.name=name;
        this.warranty=warranty;
    }
    
    Manufacturer(final Manufacturer obj){
        this.name=obj.name;
        this.warranty=obj.warranty;
    }
    
    boolean getWarranty(){
        return this.warranty;
    }
}

class ElectricDevice{
    private Manufacturer manf;
    private int months;
    
    ElectricDevice(Manufacturer obj, int months){
        this.manf=new Manufacturer(obj);
        if(months<6){
            months=6;
        }
        this.months=months;
    }
    int warranty(){
        if(this.getManf().getWarranty()){
            return this.months+12;
        }
        else return this.months;
    }
    
    Manufacturer getManf(){
        return this.manf;
    }
    
    int getMonths(){
        return this.months;
    }
}

class Cooker extends ElectricDevice{
    private boolean gas;
    
    Cooker(Manufacturer manf, int months, boolean gas){
        super(manf,months);
        this.gas=gas;
    }
    
    int warranty(){
        if(this.getManf().getWarranty()&&this.gas){
            return this.getMonths()+24;
        }
        else {
            if(this.getManf().getWarranty()||this.gas){
                return this.getMonths()+12;
            }
            else return this.getMonths();
        }
    }
}

class WashingMachine extends ElectricDevice{
    private boolean dryer;
    
    WashingMachine(Manufacturer manf, int months, boolean dryer){
        super(manf,months);
        this.dryer=dryer;
    }
    
    int warranty(){
        if(this.getManf().getWarranty()&&this.dryer){
            return (int)(this.getMonths()+this.getMonths()*0.5+12);
        }
        else {
            if(this.getManf().getWarranty()){
                return this.getMonths()+12;
            }
            else if(this.dryer){
                return (int)(this.getMonths()+this.getMonths()*0.5);
            }
            else return this.getMonths();
        }
    }
}


public class Ex {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String manufacturerName = bufferedReader.readLine();

        boolean isLongTermWarranty = Integer.parseInt(bufferedReader.readLine().trim()) != 0;

        int minWarranty = Integer.parseInt(bufferedReader.readLine().trim());

        boolean isGas = Integer.parseInt(bufferedReader.readLine().trim()) != 0;

        boolean isDryer = Integer.parseInt(bufferedReader.readLine().trim()) != 0;

        // Create object of type Manufacturer using the constructor with two parameters. Pass manufacturerName and isLongTermWarranty as arguments
        Manufacturer manf = new Manufacturer(manufacturerName, isLongTermWarranty);
        // Create object of type ElectricDevice using the constructor with 2 parameters. Pass manufacturer and minWarranty as arguments: ElectricDevice electricDevice = new ElectricDevice(manufacturer, minWarranty);
        ElectricDevice el = new ElectricDevice(manf,minWarranty);
        // Print on the console the warranty of the the object electricDevice, by calling warranty() method
        System.out.println(el.warranty());
        // Assign the electricDevice a new object of type Cooker using the constructor with 3 parameters. Pass manufacturer, price and minWarranty as arguments: electricDevice = new Cooker(manufacturer, minWarranty, isGas);
        el=new Cooker(manf,minWarranty,isGas);
        // Print on the console the warranty of the object electricDevice, by calling warranty() method
        System.out.println(el.warranty());
        // Assign the electricDevice a new object of type WashingMachine using the constructor with 3 parameters. Pass manufacturer, price and minWarranty as arguments: electricDevice = new WashingMachine(manufacturer, minWarranty, isDryer);
        el = new WashingMachine(manf,minWarranty,isDryer);
        // Print on the console the warranty of the the object electricDevice, by calling warranty() method
        System.out.println(el.warranty());
        bufferedReader.close();
    }
}
