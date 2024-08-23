public class Developer extends Employee {
    private String specializedLanguage;
    
    //Constructor 
    public Developer(int id, String name, String position, double salary, String specializedLanguage) {
        super(id, name, position, salary);
        this.specializedLanguage = specializedLanguage;
    }

    //Getters Setters
    public String getSpecializedLanguage() {
        return this.specializedLanguage;
    }

    public void setSpecializedLanguage(String specializedLanguage) {
        this.specializedLanguage = specializedLanguage;
    }



    @Override
    public void displayDetails() {
        System.out.println("Developer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Position: " + getPosition());
        System.out.println("Salary: " + getSalary());
        System.out.println("Specialized Language: " + specializedLanguage);
    
        // throw new UnsupportedOperationException("Unimplemented method 'displayDetails'");
    }
    
    

}
// Classe Developer : Hérite de Employee et ajoute des spécificités pour les développeurs. Un développeur est spécialisé dans un langage en particulier.
