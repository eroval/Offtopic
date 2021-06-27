package JavaProject1;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;




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

import JavaProject1.PrintedIssue.TypeOfPaper;
import jdk.javadoc.internal.doclets.toolkit.FieldWriter;
import jdk.jfr.events.FileWriteEvent;


import javax.naming.InsufficientResourcesException;
import javax.naming.NameNotFoundException;
import javax.sql.rowset.spi.SyncResolver;

import JavaProject1.PrintedIssue.TypeOfPaper;
import Week02.Ex;

public class Machine implements Runnable{
    private PrintShop linkedShop;
    private int maxPages;
    private int pagesPerMinute;
    private int machineNumber;
    private boolean running=false;

    //weird variables
    private int numberOfPrintedIssuesCounter;
    private int totalPagesOfIssueCounter;
    private String issueToBePrinted;

    Machine(PrintShop shop) throws NumberFormatException, IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        this.linkedShop = shop;
        
        System.out.print("Enter maximum number of pages the machine can print: ");
        this.maxPages=Integer.parseInt(bf.readLine());
        System.out.print("Enter number of pages the machine can print per minute: ");
        this.pagesPerMinute=Integer.parseInt(bf.readLine());
        this.machineNumber=linkedShop.getMachineIdNumber();
        this.linkedShop.incrementMachineIdNumber();
    }

    Machine(PrintShop shop, int maxPages, int pagesPerMinute){
        this.linkedShop=shop;
        this.maxPages=maxPages;
        this.pagesPerMinute=pagesPerMinute;
        this.machineNumber=linkedShop.getMachineIdNumber();
        this.linkedShop.incrementMachineIdNumber();
    }

    Machine(Machine object){
         this.linkedShop=object.linkedShop;
         this.maxPages=object.maxPages;
         this.pagesPerMinute=object.pagesPerMinute;
         this.machineNumber=object.machineNumber;
         this.linkedShop.incrementMachineIdNumber();
    }


    //LOAD from file constructor
    Machine(PrintShop linkedShop, File fileLocation){
        try (FileReader file = new FileReader(fileLocation)) {
            this.linkedShop=linkedShop;
            BufferedReader bf = new BufferedReader(file);
            String input = bf.lines().collect(Collectors.joining(System.lineSeparator())); 
            this.machineNumber = Integer.parseInt(input.split("id: ")[1].split("\\r?\\n")[0]);
            this.maxPages = Integer.parseInt(input.split("Max pages: ")[1].split("\\r?\\n")[0]);
            this.pagesPerMinute = Integer.parseInt(input.split("Pages per minute: ")[1].split("\\r?\\n")[0]);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Invalid machine file location.");
        }
    }

    public void printWithReadData(Issue issue, TypeOfPaper typeOfPaper, int numberOfPrintedIssues, double sellingPrice){
        PrintedIssue printIssue = new PrintedIssue(issue, typeOfPaper, this.maxPages/issue.getNumberOfPages(), numberOfPrintedIssues, sellingPrice);
        this.maxPages = this.maxPages - printIssue.getNumberOfPrints()*issue.getNumberOfPages();
        issue.add(printIssue);
    }

    public int getMachineNumber(){
        return this.machineNumber;
    }

    public int getMaxPages(){
        return this.maxPages;
    }

    public int getPagesPerMinute(){
        return this.pagesPerMinute;
    }


    public boolean empty(){
        return this.maxPages==0;
    }


    //save to file
    public void saveToFile(String outputFile){
        try(FileWriter file = new FileWriter(outputFile + this.machineNumber + ".txt")){
            file.write(this.toString());
        }catch(Exception e){
            System.out.println("Invalid file.");
            e.printStackTrace();
        }
    }


    public String toString(){
        return "id: " + this.machineNumber + "\nMax pages: " + this.maxPages + "\nPages per minute: " + this.pagesPerMinute + "\n";
    }


    public void stopMachine(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input;
        if(!this.running){
            System.out.println("Would you like to stop the machine?");
            input = bf.readLine().toLowerCase();
            while(!input.equals("yes")&&!input.equals("no")){
                System.out.println("You've entered: " + input);                                         //guidance output
                System.out.print("Invalid input. Please enter \"yes/no\": ");                           //guidance output
                input = bf.readLine().toLowerCase();                                                    //input again
            }
        }
        else System.out.println("Machine is already stopped.");
    }

    private void machineTurnOn(){
        if(!this.running){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println("Would you like to turn on machine #" + this.machineNumber + "?");
            try {
                input = bf.readLine().toLowerCase();
                while(!input.equals("yes")&&!input.equals("no")){
                    System.out.println("You've entered: " + input);                                         //guidance output
                    System.out.print("Invalid input. Please enter \"yes/no\": ");                           //guidance output
                    input = bf.readLine().toLowerCase();                                                    //input again
                }
                if(input.equals("yes")){
                    this.running=true;
                }
                else this.running=false;
            } 
            catch (IOException e) {
                System.out.println(e.getMessage());
                this.running=false;
            }
        }
    }

    private void chooseIssueForPrinting() throws NameNotFoundException, IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        if(!this.linkedShop.getIssues().isEmpty()){
            linkedShop.printIssues();
            System.out.print("Choose an issue catalogue number to be printed: ");
            BigInteger catNumber = new BigInteger(bf.readLine());
            Issue issueToBePrinted = null;
            boolean exists=false;
            try{
                for(Issue a : this.linkedShop.getIssues()){
                    if(catNumber.equals(a.getIdNumber())){
                        issueToBePrinted=a;
                        exists=true;
                        break;
                    }
                }
                if(!exists){
                    throw new NameNotFoundException("No such issue exists.");
                }
                enterPrintingData(issueToBePrinted);
            }
            catch(NameNotFoundException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Unknown exception in \"chooseIssueForPrinting()\" method.");
            }
        }
        else {
            System.out.println("No issues...");
            this.running=false;
        }
    }


    private void enterPrintingData(Issue issue) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        TypeOfPaper typeOfPaper = null;
        int numberOfPrintedIssues;
        double sellingPrice;
        int totalPages;
        boolean input = true;


        try{
            //Checks for paper
            if(this.empty()){
                throw new InsufficientResourcesException("No paper in the machine.");
            }

            //Resets input if bad
            while(input)
            {
                try{
                    System.out.print("Enter type of paper (recycled, bond, gloss): ");
                    typeOfPaper = TypeOfPaper.valueOf(bf.readLine().toUpperCase());
                    input=false;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    input=true;
                }
            }
            //number of printed issues handling
            System.out.print("Enter number of desired printed issues: ");
            numberOfPrintedIssues = Integer.parseInt(bf.readLine());
            if(numberOfPrintedIssues<0){
                throw new Exception("Number of printed issues cannot be negative.");
            }

            totalPages = issue.getNumberOfPages()*numberOfPrintedIssues;

            if(totalPages>this.maxPages){
                throw new InsufficientResourcesException("Not enough paper in the machine.\nMax pages of paper: " + this.maxPages);
            }

            System.out.print("Enter selling price per page: ");
            sellingPrice = Double.parseDouble(bf.readLine());
            if(sellingPrice<0){
                throw new Exception("Selling price cannot be a negative.");
            }

            this.numberOfPrintedIssuesCounter = numberOfPrintedIssues;
            this.totalPagesOfIssueCounter = issue.getNumberOfPages();
            this.issueToBePrinted = issue.getName();

            PrintedIssue printIssue = new PrintedIssue(issue,typeOfPaper, this.maxPages, numberOfPrintedIssues, sellingPrice);
            this.maxPages-=totalPages;
            issue.add(printIssue);
            //this.print();
            
        } 
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void printing(int pageStart, int pageEnd){
        System.out.println("\t\tCurrently printing pages #" + pageStart + "-"+pageEnd);
    }

    private void startPrinting() {
        try{
            System.out.println("\nMachine " + this.getMachineNumber() + " will start printing:");
            System.out.println("Issue: " + this.issueToBePrinted + "\n-------------------------");

            Thread.sleep(2000);
            for(int i=0; i<this.numberOfPrintedIssuesCounter; i++){
                System.out.println("\tEdition: " + (i+1));
                int j=1;
                    System.out.println("Number of pages: " + this.totalPagesOfIssueCounter);
                    if(pagesPerMinute<totalPagesOfIssueCounter){
                        for(;j<=totalPagesOfIssueCounter-pagesPerMinute; j+=pagesPerMinute){
                            this.printing(j, j+pagesPerMinute-1);
                            Thread.sleep(50);
                        }
                    }
                    this.printing(j, totalPagesOfIssueCounter);
                Thread.sleep(250);
            }
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Problem in \"startPrinting()\" method.");
        }
    }

    public void EnterData(){
        try{
            this.machineTurnOn();
            if(this.running){
                this.chooseIssueForPrinting();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public int getIssueCounter(){
        return this.totalPagesOfIssueCounter;
    }


    public void print(){
        System.out.println("Machine #" + this.machineNumber);
        System.out.println("Available Pages for printing: " + this.getMaxPages());
        System.out.println("Printed pages per minute: " + this.getPagesPerMinute());
    }

    public void run(){
        try{
            if (this.running){
                startPrinting();
            }
            else {
                System.out.println("Machine #" + this.machineNumber + " has been stopped.");
            }
        }catch(Exception e){
            this.running=false;
            e.printStackTrace();
            System.out.println("Machine" + this.getMachineNumber() + " has run into an undefined problem.");
        }
    }


}
