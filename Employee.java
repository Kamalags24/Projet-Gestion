public abstract class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;

    // Constructor
    public Employee(int id, String name, String position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }


    //Getter Setter
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract void displayDetails();
}


// Classe Abstraite Employee : Représente un employé avec des attributs de base. Elle aura une méthode abstraite qui sera définie en fonction du type d'employé à savoir un manager ou un développeur selon la classe appropriée.
