public interface EmployeeOperations {

    // Ajout d'employee
    public void addEmployee(Employee employee);

    //Suppression d'un employee par l'ID
    public void removeEmployee(int id);

    //Afficher tous les employé(e)s
    public void displayAllEmployees();

    //Afficher un employé(e) spécifique par l'id
    public void displayEmployeeById(int id);

    //Modification de l'employé
    public void updateEmployee(int id);

}
