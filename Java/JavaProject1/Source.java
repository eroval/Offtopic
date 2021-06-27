package JavaProject1;

import JavaProject1.Issue.TypeOfIssue;
import JavaProject1.PrintedIssue.TypeOfPaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashSet;

import JavaProject1.Issue.SizeOfPaper;

public class Source {
    public static void main(String[] args) throws IOException, InterruptedException {
        try{
        PrintShopController controller = new PrintShopController();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}