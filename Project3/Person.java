package Project3;

abstract class Person {
    private String id;
    public void setId(String id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

