public class Manager extends Employee {

    private int numberOfEmployees;

    //Constructor
    public Manager(int id, String name, String position, double salary, int numberOfEmployees) {
        super(id, name, position, salary);
        this.numberOfEmployees = numberOfEmployees;
    }



    public int getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
  
    

    @Override
    public void displayDetails() {
        System.out.println("Manager ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Position: " + getPosition());
        System.out.println("Salary: " + getSalary());
        System.out.println("Number of Employees: " + numberOfEmployees);
        
        // throw new UnsupportedOperationException("Unimplemented method 'displayDetails'");
    }

    
    
}


// Classe Manager : Hérite de Employee et ajoute des spécificités pour les managers. Un responsable est à la tête d'un nombre donné de personnes.