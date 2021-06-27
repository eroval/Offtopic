package Week10;

import java.util.ArrayList;
import java.util.List;

public class FashionShop{
    private String name;
    private List<Tailor> allTailors;
    private int numberOfClothes;
    private int sewnClothes;

    FashionShop(String name, int numberOfClothes){
        this.name=name;
        this.numberOfClothes=numberOfClothes;
        this.sewnClothes=0;
        this.allTailors=new ArrayList<Tailor>();
    }

    public void addTailor(String name){
        Tailor tailor = new Tailor(name);
        this.addTailor(tailor);
    }

    public void addTailor(Tailor... tailor){
        for(Tailor t : tailor){
            this.allTailors.add(t);
            t.setAssociatedShop(this);
        }
    }

    public int getNumberOfClothes(){
        return this.numberOfClothes;
    }

    public void increaseNumberOfClothes(int number){
        this.numberOfClothes+=number;
    }

    public int getNumberOfSewnClothes(){
        return this.sewnClothes;
    }
    
    public void increaseSewnClothes(){
        this.sewnClothes++;
    }

    public String getName(){
        return this.name;
    }

    public int getNumberOfTailors(){
        return this.allTailors.size();
    }

    public void printAllTailors(){
        for(Tailor t : this.allTailors){
            t.print();
        }
    }

    public void startSewing() throws InterruptedException{
        Thread[] threads = new Thread[this.allTailors.size()];
        for(int i=0; i<this.allTailors.size(); i++){
            System.out.println("Starting Thread #" + (i+1));
            threads[i] = new Thread(this.allTailors.get(i));
            threads[i].start();
        }

        for(Thread thread : threads){
            thread.join();
        }
    }
}
