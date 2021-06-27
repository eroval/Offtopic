package Week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Week09.Factory.bottle;

public class HW {
    public static void addBottleTest(Factory factory) throws IOException{ 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the type of bottle which you wish to add: (GLASS/PLASTIC)");
        String typeBottle = bf.readLine();
        System.out.println("Enter how many bottles to add: ");
        int numberOfBottles = Integer.parseInt(bf.readLine());
        factory.addBottles(bottle.valueOf(typeBottle), numberOfBottles);
    }

    public static void prepackageTest(Factory factory) throws IOException, InsufficientNumberOfBottelsException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the type of bottle which you wish to prepackage: (GLASS/PLASTIC)");
        String typeBottle = bf.readLine();
        System.out.println("Enter how many bottles to add: ");
        int numberOfBottles = Integer.parseInt(bf.readLine());
        factory.prepackage(bottle.valueOf(typeBottle), numberOfBottles);
    }

    public static void sellBottlesTest(Factory factory) throws IOException, InsufficientNumberOfBottelsException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter manufacturer of the bottles: ");
        String manufacturer = bf.readLine();
        System.out.println("Enter the company which has ordered the delivery: ");
        String receivingCompany = bf.readLine();
        System.out.println("Enter price per bottle: ");
        double price = Double.parseDouble(bf.readLine());
        System.out.println("Enter the type of bottle the company has ordered: (GLASS/PLASTIC)");
        String typeBottle = bf.readLine();
        System.out.println("Enter how many bottles the company has ordered: ");
        int numberOfBottles = Integer.parseInt(bf.readLine());
        factory.sellBottle(manufacturer, receivingCompany, price, bottle.valueOf(typeBottle), numberOfBottles);
    }

    public static void readFile() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter which number of invoice you would like to read: ");
        int number = Integer.parseInt(bf.readLine());
        Factory.readFile(number);
    }
    public static void main(String[] args) throws IOException, InsufficientNumberOfBottelsException, ClassNotFoundException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int glassBottlesAvailable=0, prepackagedGlassBottles=0, plasticBottlesAvailable=0, prepackagedPlasticBottles=0;

        //----------------------------------------------------------------//
        
        System.out.println("Enter number of available glass bottles: ");
        glassBottlesAvailable = Integer.parseInt(bf.readLine());

        System.out.println("Enter number of prepackaged glass bottles: ");
        prepackagedGlassBottles = Integer.parseInt(bf.readLine());

        System.out.println("Enter number of available plastic bottles: ");
        plasticBottlesAvailable = Integer.parseInt(bf.readLine());

        System.out.println("Enter number of prepackaged plastic bottles: ");
        prepackagedPlasticBottles = Integer.parseInt(bf.readLine());

        //----------------------------------------------------------------//

        Factory.setInvoiceNumber(0); //SETS DEFAULT TO 0 IF InvoiceNumber.txt doesn't exist
        Factory factory = new Factory(glassBottlesAvailable, prepackagedGlassBottles, plasticBottlesAvailable, prepackagedPlasticBottles);
        factory.print();
        
        addBottleTest(factory);
        System.out.println("\n\n");
        prepackageTest(factory);
        System.out.println("\n\n");
        sellBottlesTest(factory);
        System.out.println("\n\n");
        Factory.readFile(0);
        System.out.println("\n\n");
        Factory.readSerFile(0);
    }
}