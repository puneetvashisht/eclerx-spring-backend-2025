// Manager is an EMployee

public class Employee extends Object{
    // constructors

    public Employee() {
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(int id, String name, double salary) {
        // this.id = id;
        // this.name = name;
        this(id, name); // constructor chaining
        this.salary = salary;
    }
    
    // fields
    int id;
    String name;
    double salary;

    // methods
    void incrementSalary(double percentage) {
        salary += salary * percentage / 100;
    }


    // string representation

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }


    public static void main(String[] args) {
        Employee e = new Employee(102, "Bob");
    
        Employee emp = new Employee(1, "John Doe", 50000);
        System.out.println(emp.toString());
        emp.incrementSalary(10);
        System.out.println("After increment: " + emp);
    }
}
