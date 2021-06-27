package JavaProject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

public class PrintedIssue {
    //ENUMERATOR
    public static enum TypeOfPaper{
        RECYCLED(0.01),
        GLOSS(0.05),
        BOND(0.02);

        private double price;

        TypeOfPaper(double price){
            this.setPrice(price);
        }
        
        public double getPrice(){
            return this.price;
        }

        public void setPrice(double price){
            if(price<0){
                price=0;
            }
            this.price = price;
        }
    }
    //END OF ENUMERATOR

    private static final long serializableID = 474562L;
    private Issue linkedIssue;
    private TypeOfPaper typeOfPaper;

    private int numberOfPrintedIssues;
    private int maxNumberOfPrintedIssues;
    private static int minimumPrintedIssues; 
    private static int percentageBonus;

    private BigDecimal pricePerPage;
    private BigDecimal pricePerIssue;

    private BigDecimal sellingPricePerPage;
    private BigDecimal sellingPricePerIssue;

    



    //------------------------------------------------------CONSTRUCTORS------------------------------------------------------//


    PrintedIssue(Issue linkedIssue, int maxNumberOfPrintedIssues) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        this.linkedIssue=linkedIssue;

        do{
            System.out.print("Enter type of paper (recycled, bond, gloss): ");
        } while(!this.setTypeOfPaper(bf.readLine()));


        this.setMaxNumberOfPrintedIssues(maxNumberOfPrintedIssues);
        
        do{
            System.out.print("Enter number of desired printed issues: ");
        } while(!this.setNumberOfPrintedIssues(bf.readLine()));

        this.setPricePerPage();

        do{
            System.out.print("Enter selling price per page: ");
        } while(!this.setSellingPrice(bf.readLine()));
    }



    PrintedIssue(Issue linkedIssue, TypeOfPaper typeOfPaper, int maxNumberOfPrintedIssues, int numberOfPrintedIssues, double sellingPrice){
        this.linkedIssue=linkedIssue;
        this.typeOfPaper=typeOfPaper;
        this.setMaxNumberOfPrintedIssues(maxNumberOfPrintedIssues);
        this.setNumberOfPrintedIssues(numberOfPrintedIssues);
        this.setPricePerPage();
        this.setSellingPrice(sellingPrice);
    }



    //Reading Constructor (doesn't use setters (exception))
    PrintedIssue(Issue linkedIssue, File ReadFile) throws IOException{
        this.linkedIssue=linkedIssue;

        try (FileReader file = new FileReader(ReadFile)) {
            BufferedReader bf = new BufferedReader(file);
            String input = bf.lines().collect(Collectors.joining(System.lineSeparator()));
            this.setTypeOfPaper(input.split("Type of paper: ")[1].split("\\r?\\n")[0]);
            this.numberOfPrintedIssues = Integer.parseInt(input.split("Number of printed issues: ")[1].split("\\r?\\n")[0]);
            this.pricePerPage = new BigDecimal(input.split("Cost per page: ")[1].split("\\r?\\n")[0]).setScale(2,RoundingMode.HALF_UP);
            this.pricePerIssue = new BigDecimal(input.split("Cost per issue: ")[1].split("\\r?\\n")[0]).setScale(2,RoundingMode.HALF_UP);
            this.sellingPricePerPage = new BigDecimal(input.split("Profit per page: ")[1].split("\\r?\\n")[0]).setScale(2, RoundingMode.HALF_UP);
            this.sellingPricePerIssue = new BigDecimal(input.split("Profit per issue: ")[1].split("\\r?\\n")[0]).setScale(2, RoundingMode.HALF_UP);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Invalid data for issue.");
        }
    }

    

    //------------------------------------------------------END OF CONSTRUCTORS------------------------------------------------------//


    //Calculate Price
    public void calculatePrice(){
        this.pricePerIssue = this.pricePerPage.multiply(BigDecimal.valueOf(this.linkedIssue.getNumberOfPages())).setScale(2,RoundingMode.HALF_UP);
    }

    //Calculate Selling Price
    public void calculateSellingPrice(){   
        this.sellingPricePerIssue = this.pricePerIssue.add(this.sellingPricePerPage.multiply(BigDecimal.valueOf(this.linkedIssue.getNumberOfPages()))).setScale(2,RoundingMode.HALF_UP);
        //Change selling price with discount(or don't)
        int tmpPercentage = getPercentage();
        if(this.numberOfPrintedIssues<getMinimumNumberOfPrintedIssues()){
            setPercentageBonus(0);
        }
        this.sellingPricePerIssue = this.sellingPricePerIssue.subtract(this.sellingPricePerIssue.multiply(BigDecimal.valueOf((double)getPercentage()/100))).setScale(2,RoundingMode.HALF_UP);
        setPercentageBonus(tmpPercentage);

        if(this.sellingPricePerIssue.compareTo(this.pricePerIssue)==-1){
            this.sellingPricePerIssue=this.pricePerIssue;
        }
    }

    //Static variables setter/getters
    public static void setPercentageBonus(int percentage){
        if(percentage<0){
            percentage=0;
        }
        percentageBonus=percentage;
    }

    public static void setMinimumNumberOfPrintedIssues(int numberOfIssues){
        if(numberOfIssues<0){
            numberOfIssues=0;
        }
        minimumPrintedIssues = numberOfIssues;
    }

    public static int getPercentage(){
        return percentageBonus;
    }

    public static int getMinimumNumberOfPrintedIssues(){
        return minimumPrintedIssues;
    }


    //Normal setters/getters
    public void setLinkedIssue(Issue issue){
        this.linkedIssue=issue;
    }

    public Issue getLinkedIssue(){
        return this.linkedIssue;
    }

    public int getNumberOfPrints(){
        return this.numberOfPrintedIssues;
    }

    //Sets for manual inputting from String
    public boolean setMaxNumberOfPrintedIssues(String input){
        try{
            int maxNumberOfPrintedIssues = Integer.parseInt(input);
            if(maxNumberOfPrintedIssues<0){
                maxNumberOfPrintedIssues=0;
            }
            this.maxNumberOfPrintedIssues=maxNumberOfPrintedIssues;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for maximum number of printed issues.");
            return false;
        }
    }

    public boolean setNumberOfPrintedIssues(String input){
        try{
            int numberOfPrintedIssues=Integer.parseInt(input);
            if(numberOfPrintedIssues<0){
                numberOfPrintedIssues=0;
            }

            if(numberOfPrintedIssues>this.maxNumberOfPrintedIssues){
                numberOfPrintedIssues=this.maxNumberOfPrintedIssues;
            }

            this.numberOfPrintedIssues=numberOfPrintedIssues;
            return true;
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Invalid input for desired number of printed issues.");
                return false;
            }
    }

    public boolean setSellingPrice(String input){
        try{
            double sellingPrice = Double.parseDouble(input);
            if(sellingPrice<0){
                sellingPrice=0;
            }
            this.sellingPricePerPage = BigDecimal.valueOf(sellingPrice).setScale(2, RoundingMode.HALF_UP);
            this.calculateSellingPrice();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for selling price.");
            return false;
        }
    }

    public boolean setTypeOfPaper(String input){
        try{
            TypeOfPaper typeOfPaper = TypeOfPaper.valueOf(input.toUpperCase());
            this.typeOfPaper = typeOfPaper;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for the type of paper.");
            return false;
        }
    }

    public void setMaxNumberOfPrintedIssues(int maxNumberOfPrintedIssues){
        if(maxNumberOfPrintedIssues<0){
            maxNumberOfPrintedIssues=0;
        }
        this.maxNumberOfPrintedIssues=maxNumberOfPrintedIssues;
    }

    public void setNumberOfPrintedIssues(int numberOfPrintedIssues){
        if(numberOfPrintedIssues<0){
            numberOfPrintedIssues=0;
        }

        if(numberOfPrintedIssues>this.maxNumberOfPrintedIssues){
            numberOfPrintedIssues=this.maxNumberOfPrintedIssues;
        }

        this.numberOfPrintedIssues=numberOfPrintedIssues;
    }

    public void setSellingPrice(double sellingPrice){
        if(sellingPrice<0){
            sellingPrice=0;
        }
        this.sellingPricePerPage = BigDecimal.valueOf(sellingPrice).setScale(2, RoundingMode.HALF_UP);
        this.calculateSellingPrice();
    }

    public void setPricePerPage(){
        this.pricePerPage = BigDecimal.valueOf(this.linkedIssue.getPaperSize().getPrice()+this.typeOfPaper.getPrice()).setScale(2,RoundingMode.HALF_UP);
        this.calculatePrice();
    }

     //Sets for manual inputting from String
    public static boolean setPercentageBonus(String input){
        try{
            int percentage = Integer.parseInt(input);
            if(percentage<0){
                percentage=0;
            }
            percentageBonus=percentage;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for percentage bonus of printed issues.");
            return false;
        }
    }

    public static boolean setMinimumPrintedIssues(String input){
        try{
            int printedIssues = Integer.parseInt(input);
            if(printedIssues<0){
                printedIssues=0;
            }
            minimumPrintedIssues=printedIssues;
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid input for minimum number of printed issues for discount.");
            return false;
        }
    } 

    //Save Issue to File (handled by the shop)
    public void saveToFile(String fileName){
        try (FileWriter printFile = new FileWriter(fileName)) {
            printFile.write(this.toString());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public BigDecimal Cost(){
        return this.pricePerIssue;
    }

    public BigDecimal TotalCost(){
        return this.pricePerIssue.multiply(BigDecimal.valueOf(this.numberOfPrintedIssues));
    }

    public BigDecimal Sells(){
        return this.sellingPricePerIssue;
    }

    public BigDecimal TotalSells(){
        return this.sellingPricePerIssue.multiply(BigDecimal.valueOf(this.numberOfPrintedIssues));
    }

    public BigDecimal Profit(){
        return TotalSells().subtract(TotalCost());
    }

    public void print(){
        System.out.println("\tType of paper: " + this.typeOfPaper);
        System.out.println("\tNumber of printed issues: " + this.numberOfPrintedIssues);
        System.out.println("\tCost per page: " + this.pricePerPage);
        System.out.println("\tCost per issue: " + this.pricePerIssue);
        System.out.println("\tProfit per page: " + this.sellingPricePerPage);
        System.out.println("\tProfit per pssue: " + this.sellingPricePerIssue);
        System.out.println("\tTotal cost: " + this.TotalCost());
        System.out.println("\tTotal pells: " + this.TotalSells());
        System.out.println("\tTotal profit: " + this.Profit());
    }

    public String toString(){
        String str = "";
        str+="Print of issue: " + this.linkedIssue.getName() + "-" + this.linkedIssue.nameIndex();
        str+="\nType of paper: " + this.typeOfPaper;
        str+="\nNumber of printed issues: " + this.numberOfPrintedIssues;
        str+="\nCost per page: " + this.pricePerPage;
        str+="\nCost per issue: " + this.pricePerIssue;
        str+="\nProfit per page: " + this.sellingPricePerPage;
        str+="\nProfit per issue: " + this.sellingPricePerIssue;
        str+="\nTotal cost: " + this.TotalCost();
        str+="\nTotal sells: " + this.TotalSells();
        str+="\nTotal profit: " + this.Profit();
        return str;
    } 

    public String toCatalogue(){
        
        String str = "";
        str+="\n\tPrint of issue: " + this.linkedIssue.getName() + "-" + this.linkedIssue.nameIndex();
        str+="\n\tType of paper: " + this.typeOfPaper;
        str+="\n\tNumber of printed issues: " + this.numberOfPrintedIssues;
        str+="\n\tCost per page: " + this.pricePerPage;
        str+="\n\tCost per issue: " + this.pricePerIssue;
        str+="\n\tProfit per page: " + this.sellingPricePerPage;
        str+="\n\tProfit per issue: " + this.sellingPricePerIssue;
        str+="\n\tTotal cost: " + this.TotalCost();
        str+="\n\tTotal sells: " + this.TotalSells();
        str+="\n\tTotal profit: " + this.Profit();
        return str;
    }
}



