package Week01;

class Person{

    private String name;
    private int age;

    Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    Person(){
        this.name="Empty";
        this.age=560;
    }

    Person(Person object){
        this.name=object.name;
        this.age=object.age;
    }

    Person(String name){
        this.name=name;
        this.age=560;
    }

    Person(int age){
        this.name="Empty";
        this.age=age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public void setName(String name){
        this.name=name;
    }

    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    public void out(){
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
    }
}

class Example{
    public static void main(String args[]) {
        Person John=new Person("John", 25);
        John.out();
    }
}