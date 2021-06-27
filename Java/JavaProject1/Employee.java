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


public abstract class Employee {
    private static double baseSalary=0;
    private String name;
    private static final long serializableID = 521351L;

    Employee(String name){
        this.name=name;
    }

    Employee() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter name of " + this.getClass().getSimpleName() + ": ");
        this.name = bf.readLine();
    }
    
    Employee(File sourceFile){
        try(FileReader file = new FileReader(sourceFile)){
            BufferedReader bf = new BufferedReader(file);
            this.name=bf.readLine();
        }catch(Exception e){
            System.out.println("Invalid file.");
            e.printStackTrace();
        }
    }

    public void saveToFile(String outputFile){
        try(FileWriter file = new FileWriter(outputFile)){
            file.write(this.name + "\n");
            file.write(this.salary() + "\n");
        }catch(Exception e){
            System.out.println("Invalid file.");
            e.printStackTrace();
        }
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public static void setBaseSalary(double newSalary){
        if(newSalary<0){
            newSalary=0;
        }
        baseSalary=newSalary;
    }

    public static boolean setBaseSalary(String input){
        try{
            double newBaseSalary = Double.parseDouble(input);
            if(newBaseSalary<0){
                newBaseSalary=0;
            }
            baseSalary=newBaseSalary;
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for base salary.");
            return false;
        }
    }

    public static double getBaseSalary(){
        return baseSalary;
    }

    public abstract String toString();
    public abstract double salary();
}
