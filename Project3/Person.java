package Project3;

abstract class Person {
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void displayInfo(){
        System.out.println("ID: " + id);
        System.out.println("name: " + name);
        System.out.println("email: " + email);
    }
}

