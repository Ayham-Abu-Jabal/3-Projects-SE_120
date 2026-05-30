
//All Patients and Doctors will be a Person.
public abstract class Person {
    
    //Data fields shared by all
    private String id,name,phone;
    private int age;
    

    //Constructor
    public Person(String id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }
    
    //

    //To be overidden by subclasses
    public abstract void displayInfo();

    //Shared by all so have one implementation here
    protected void displayBasicInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone);
    }
}
