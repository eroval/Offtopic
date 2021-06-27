package Week02;

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

class Studio{private final long id;
    private int maxWorkingHours;
    private BigDecimal minPricePerHours;
    private BigDecimal currencyEuro;
    private BigDecimal pricePerHour;
    private int workedHours;

    Studio(long id, int maxWorkingHours){
        this.id=id;
        if(maxWorkingHours>24){
            maxWorkingHours=24;
        }
        if(maxWorkingHours<0){
            maxWorkingHours=24;
        }
        this.maxWorkingHours=maxWorkingHours;
    }
    
    //setters
    //price mustn's be a negative number
    void setMinPricePerHours(double minPricePerHours){
        if(minPricePerHours<0){
            minPricePerHours= 0;
        }
        this.minPricePerHours=BigDecimal.valueOf(minPricePerHours);
    }
    
    void setPricePerHour(double pricePerHour){
        if(pricePerHour<this.minPricePerHours.doubleValue()){
            pricePerHour=this.minPricePerHours.doubleValue();
        }
        this.pricePerHour=BigDecimal.valueOf(pricePerHour);
    }
    
    void setCurrency(double currencyEuro){
        this.currencyEuro=BigDecimal.valueOf(currencyEuro);
    }
    
    void setWorkedHours(int workedHours){
        if(workedHours>maxWorkingHours){
            workedHours=maxWorkingHours;
        }
        if(workedHours<0){
            workedHours=0;
        }
        this.workedHours=workedHours;
    }
    

    //getters
    long getID(){
        return id;
    }
    
    BigDecimal getMinPricePerHours(){
        return this.minPricePerHours;
    }
    
    BigDecimal getPricePerHour(){
        return this.pricePerHour;
    }
    
    BigDecimal getCurrency(){
        return this.currencyEuro;
    }
    
    int getWorkedHours(){
        return this.workedHours;
    }
    
    int getMaxWorkingHours(){
        return this.maxWorkingHours;
    }
    
    //Method that returns sum in lv
     BigDecimal toLv(){
        return this.pricePerHour.multiply(BigDecimal.valueOf(workedHours));
    }
    
    //Method that returns sum in euro
    BigDecimal toEuro(){
        return this.pricePerHour.multiply(this.currencyEuro).multiply(BigDecimal.valueOf(workedHours));
    }
    
    //Method return the studio with bigger price per hour
    long studioWithLargerPrice(Studio obj2){
        if(this.pricePerHour.compareTo(obj2.pricePerHour)==1){
            return this.id;
        }
        else return obj2.id;
    }
    
    //Method for comparison of two studios. Return true if first is more expensive
    boolean isFirstStudioWithGreaterTurnover(Studio obj2){
        return this.toLv().compareTo(obj2.toLv())==1;
    }
}


public class HW2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        long id = Long.parseLong(bufferedReader.readLine().trim());

        int maxWorkingHours = Integer.parseInt(bufferedReader.readLine().trim());

        double minPricePerHour = Double.parseDouble(bufferedReader.readLine().trim());

        double currencyEuro = Double.parseDouble(bufferedReader.readLine().trim());

        double pricePerHour = Double.parseDouble(bufferedReader.readLine().trim());

        int workedHours = Integer.parseInt(bufferedReader.readLine().trim());

        // Create an object of type Studio using the constructor with two parameters. Pass id and maxWorkingHours as arguments.
        Studio obj = new Studio(id,maxWorkingHours);
        // Use set method to set the minimum price per hour. User variable minPricePerHour to pass it as a method argument.
        obj.setMinPricePerHours(minPricePerHour);
        // Use set method to set the Euro currency. Pass currencyEuro to the set method.
        obj.setCurrency(currencyEuro);
        // Use set method to set the price for renting the studio for one hour. Pass pricePerHour to the set method.
        obj.setPricePerHour(pricePerHour);
        // Use set method to set the number of hours for the day that the studio was rented.
        obj.setWorkedHours(workedHours);
        // Print on the console the turnover in lv., calculated by using workedHours and pricePerHour.
        System.out.println(obj.toLv());
        // Print on the console the turnover in Euro., calculated by using workedHours and pricePerHour.
        System.out.println(obj.toEuro());
        long id2 = Long.parseLong(bufferedReader.readLine().trim());

        int maxWorkingHours2 = Integer.parseInt(bufferedReader.readLine().trim());

        double pricePerHour2 = Double.parseDouble(bufferedReader.readLine().trim());

        int workedHours2 = Integer.parseInt(bufferedReader.readLine().trim());

        // Create the second object of type Studio, using the constructor with 2 parameters. Use id2 and maxWorkingHours2 to pass them as arguments
        Studio obj2 = new Studio(id2,maxWorkingHours2);
        // Use set method to set the price for renting the second studio for one hour. Pass pricePerHour2 to the set method.
        obj2.setMinPricePerHours(minPricePerHour);
        obj2.setPricePerHour(pricePerHour);
        // Use set method to set the number of hours for the day that the second studio was rented (workedHours2)
        obj2.setWorkedHours(workedHours2);
        // Print on the console the result of getting the id of the studio that is returned as a result of the studioWithLargerPrice() method. The first studio object is used to call the method.
        System.out.println(obj.studioWithLargerPrice(obj2));
        // Print on the console the result of calling the method isFirstStudioWithGreaterTurnover() method. The first studio object is used to call the method.
        System.out.println(obj.isFirstStudioWithGreaterTurnover(obj2));
        bufferedReader.close();
    }
}
