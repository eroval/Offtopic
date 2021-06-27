package Week10;

public class Tailor implements Runnable{
    private String name;
    private FashionShop associated;
    private int sewnClothes;

    Tailor(String name){
        this.name=name;
        sewnClothes=0;
    }

    public void sew(){
        this.sewnClothes++;
        this.associated.increaseSewnClothes();
        System.out.println(this.name + " has sewn the #" + this.associated.getNumberOfSewnClothes() +" piece.");
    }

    public void setAssociatedShop(FashionShop shop){
        this.associated=shop;
    }

    public int getNumberOfSewnClothes(){
        return this.sewnClothes;
    }

    public void print(){
        System.out.println(this.name + " has sewn " + this.getNumberOfSewnClothes() + " number of clothes.");
    }

    public void run(){
        while(this.associated.getNumberOfSewnClothes()<this.associated.getNumberOfClothes()){
            sew();
        }
    }
}
