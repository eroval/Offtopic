package Week09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Factory implements Serializable{
    public static enum bottle{
        GLASS(0.5),
        PLASTIC(0.5);

        private double size;

        bottle(double size){
            this.size=size;
        }
        
        public double getSize(){
            return size;
        }

        public void setSize(double size){
            this.size = size;
        }
    }

    //Keeps the last invoiceNumber
    //and the location
    private static long invoiceNumber;
    private static String invoicesLocation;

    //All the bottles the current factory has:
    private ArrayList<Long> glassBottles;
    private ArrayList<Long> plasticBottles;

    Factory(long glass_emptyBottles, long glass_fullBottles, long plastic_emptyBottles, long plastic_fullBottles) throws IOException{


        String invoiceFileName = System.getProperty("user.dir");
        //For some reason relative path doesn't work(presents as "---/./Invoice/InvoiceNumber.txt" 
        //with a dot(.) as literal), that's why I used a workaround 
        invoicesLocation = invoiceFileName.concat("/Week09/Invoice/");
        File invoiceNumberFile = new File(invoicesLocation.concat("InvoiceNumber.txt"));

        try{
            if(!invoiceNumberFile.exists()){
                    throw new IOException("1");
                }
            if(invoiceNumberFile.length()==0){
                throw new IOException("2");
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            FileWriter invoiceNumber = new FileWriter(invoiceNumberFile);
            switch(e.getMessage()){
                case "1":
                    System.out.println("InvoiceNumber.txt doesn't exist.\nWe will create it for you.");
                    invoiceNumberFile.createNewFile();
                    invoiceNumber.write("0");
                    invoiceNumber.close();
                    break;
                case "2":
                    System.out.println("InvoiceNumber.txt doesn't contain any number.\nWe will start again from 0.");
                    invoiceNumber.write("0");
                    invoiceNumber.close();
                    break;

            }
        }
        Scanner scanner = new Scanner(invoiceNumberFile);
        invoiceNumber= scanner.nextInt();
        scanner.close();;

        this.glassBottles = new ArrayList<Long>();
        this.glassBottles.add(glass_emptyBottles);
        this.glassBottles.add(glass_fullBottles);

        this.plasticBottles = new ArrayList<Long>();
        this.plasticBottles.add(plastic_emptyBottles);
        this.plasticBottles.add(plastic_fullBottles);
    }


//PREPACKAGING
    public void prepackage(bottle typeBottle, int numberOfBottles) throws InsufficientNumberOfBottelsException{
        try{

            //GLASS BOTTLES
            if(typeBottle==bottle.GLASS){
                if(this.glassBottles.get(0)<numberOfBottles){
                    this.glassBottles.set(1, this.glassBottles.get(1)+this.glassBottles.get(0));
                    this.glassBottles.set(0, this.glassBottles.get(0)-this.glassBottles.get(0));
                    throw new InsufficientNumberOfBottelsException("glass");
                }
                else {
                    this.glassBottles.set(1, this.glassBottles.get(1)+numberOfBottles);
                    this.glassBottles.set(0, this.glassBottles.get(0)-numberOfBottles);
                }
            }

            //PLASTIC BOTTLES
            else {
                if(this.plasticBottles.get(0)<numberOfBottles){
                    this.plasticBottles.set(1, this.plasticBottles.get(1)+this.plasticBottles.get(0));
                    this.plasticBottles.set(0, this.plasticBottles.get(0)-this.plasticBottles.get(0));
                    throw new InsufficientNumberOfBottelsException("plastic");
                }
                else {
                    this.plasticBottles.set(1, this.plasticBottles.get(1)+numberOfBottles);
                    this.plasticBottles.set(0, this.plasticBottles.get(0)-numberOfBottles);
                }
            }
        }


        catch(InsufficientNumberOfBottelsException e){
            System.out.println("Not enough "+ e.getMessage() + " bottles to prepackage.");
            System.out.println("We prepackaged all available " + e.getMessage() + " bottles.");
        }
    }



//DELIVERY
    public void addBottles(bottle typeBottle, int numberOfBottles){
        //EXCEPTIONS THROWN AUTOMATICALLY FOR NO SUCH TYPE OF BOTTLE
        if(typeBottle==bottle.GLASS){
            this.glassBottles.set(0, this.glassBottles.get(0)+numberOfBottles);
        }
        else {
            this.plasticBottles.set(0, this.plasticBottles.get(0)+numberOfBottles);
        }
    }


//SELLING
    public void sellBottle(String manufacturer, String receivingCompany, double price, bottle typeBottle, int numberOfBottles) throws InsufficientNumberOfBottelsException, IOException{
        try{

            BigDecimal Sum = new BigDecimal("0");
            //GLASS BOTTLES SELLING
            if(typeBottle==bottle.GLASS){
                if(this.glassBottles.get(1)<numberOfBottles){
                    throw new InsufficientNumberOfBottelsException("glass");
                }
                else {
                    this.glassBottles.set(1, this.glassBottles.get(1)-numberOfBottles);
                    for(int i=0; i<numberOfBottles; i++){
                        Sum = new BigDecimal(Sum.add(BigDecimal.valueOf(price)).toString());
                    }
                    writeToFile(manufacturer, receivingCompany, numberOfBottles, price, Sum,bottle.GLASS);
                }
            }

            //PLASTIC BOTTLES SELLING
            else {
                if(this.plasticBottles.get(1)<numberOfBottles){
                    throw new InsufficientNumberOfBottelsException("plastic");
                }
                else{
                    this.plasticBottles.set(1, this.plasticBottles.get(1)-numberOfBottles);
                    for(int i=0; i<numberOfBottles; i++){
                        Sum = new BigDecimal(Sum.add(BigDecimal.valueOf(price)).toString());
                    }
                    writeToFile(manufacturer, receivingCompany, numberOfBottles, price, Sum,bottle.PLASTIC);
                }
            }
        }
        catch(InsufficientNumberOfBottelsException e){
            System.out.println("Not enough "+ e.getMessage() + " bottles to sell.");
        }
    }

    //WRITE TO FILE(NORMAL) AND SAVE THE SERIALIZED VERSION
    private void writeToFile(String manufacturer, String receivingCompany, long numberOfBottles, double price,  BigDecimal Sum, bottle typeBottle) throws IOException{
        try{
            File fileName = new File(getInvoicesLocation() + "invoice" + getInvoiceNumber() + ".txt");
            File invoiceFile = new File(getInvoicesLocation() + "InvoiceNumber.txt");
            if(!fileName.exists()){
                fileName.createNewFile();
            }
            FileWriter invoiceNumber = new FileWriter(invoiceFile);
            FileWriter invoice = new FileWriter(fileName);
            System.out.println("Invoice Number File : " + Factory.invoiceNumber);
            System.out.println("Invoice File: " + invoice.toString());

            saveSerFile(); //save serialized version
            
            invoice.write("Invoice: #" + getInvoiceNumber() + "\n");
            Factory.incrementInvoiceNumber();
            invoice.write("Manufacturer: " + manufacturer + "\n");
            invoice.write("Receiving Company: " + receivingCompany + "\n");
            invoice.write("Date: " + LocalDate.now() + "\n");
            invoice.write("Type of bottles: " + typeBottle + "\n");
            invoice.write("Number of sold bottles: " + numberOfBottles + "\n");
            invoice.write("Price of bottle: " + price + "\n");
            invoice.write("Total price: " + Sum + "\n");
            invoiceNumber.write(String.valueOf(getInvoiceNumber()));
            invoiceNumber.close();
            invoice.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readFile(int number) throws IOException{
        try{
            File fileName = new File(getInvoicesLocation() + "invoice" + number + ".txt");
            if(!fileName.exists()){
                throw new IOException(String.valueOf(number));
            }
            String line;
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            while((line=bf.readLine())!=null){
                System.out.println(line);
            }
            bf.close();
        }
        catch(IOException e){
            System.out.println("Invoice " + e.getMessage() + " doesn't exist.");
        }
    }

    public void saveSerFile() throws IOException{
        try{
            FileOutputStream file = new FileOutputStream(getInvoicesLocation()+getInvoiceNumber()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
                
            out.close();
            file.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void readSerFile(int number) throws IOException, ClassNotFoundException{
        try{
            FileInputStream file = new FileInputStream(getInvoicesLocation()+number+".ser");
            ObjectInputStream in = new ObjectInputStream(file);
              
            // Method for deserialization of object
            Factory tempFactory = (Factory) in.readObject();
            System.out.println("Deserialized object is: ");
            tempFactory.print();

            in.close();
            file.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static long getInvoiceNumber(){
        return invoiceNumber;
    }

    public static void incrementInvoiceNumber(){
        invoiceNumber++;
    }

    public static void setInvoiceNumber(int number){
        invoiceNumber = number;
    }

    public static String getInvoicesLocation(){
        return invoicesLocation;
    }
    
    public void print(){
        System.out.println("Number of available glass bottles: " + this.glassBottles.get(0) +" and prepackaged glass bottles: " + this.glassBottles.get(1));
        System.out.println("Number of available plastic bottles: " + this.plasticBottles.get(0) +" and prepackaged plastic bottles: " + this.plasticBottles.get(1));
    }

    public String toString(){
        return "Invoice number = " + getInvoiceNumber() + ", Glass Bottles Available = " + this.glassBottles.get(0) + ", Prepackaged Glass Bottles = " + this.glassBottles.get(1)
        + " \n\tPlastic Bottles Available = " + this.plasticBottles.get(0)+ ", Prepackaged Plastic Bottles = " + this.plasticBottles.get(1);
    }
}
