package JavaProject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Manager extends Employee{
    private static int percentage=0;

    Manager() throws IOException{
        super();
    }

    Manager(String name) throws IOException{
        super(name);
    }

    Manager(File sourceFile){
        super(sourceFile);
    }

    public double salary(){
        return getBaseSalary() + getBaseSalary()*getPercentage()/100;
    }

    public static void setPercentage(int newPercentage){
        if(newPercentage<0){
            newPercentage=0;
        }
        percentage=newPercentage;
    }
    
    
    public static boolean setPercentage(String input){
    try{
        int newPercentage = Integer.parseInt(input);
        if(newPercentage<0){
            newPercentage=0;
        }
        percentage=newPercentage;
        return true;
    }
    catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for percentage.");
            return false;
        }
    }

    public static int getPercentage(){
        return percentage;
    }

    public String toString(){
        return this.getClass().getSimpleName() + ": " + this.getName() + " has an income of " + salary() + ".\n";
    }
}
