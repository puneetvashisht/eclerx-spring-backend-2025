public class Manager extends Employee {
    // constructors

    public Manager() {
        super();
    }
    public Manager(int id, String name, double salary, int employeeToManage) {
        super(id, name, salary);
        this.employeeToManage = employeeToManage;
    }

    private int employeeToManage;

    public void setEmployeeToManage(int employeeToManage) {
        if(employeeToManage >= 0)
            this.employeeToManage = employeeToManage;
    }

    public int getEmployeeToManage() {
        return employeeToManage;
    }

    
    @Override
    public String toString() {
        return "Manager{" +
                "employeeToManage=" + employeeToManage +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
    


    
}
