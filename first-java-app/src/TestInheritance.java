public class TestInheritance {
    public static void main(String[] args) {
        Manager mgr = new Manager(201, "Alice", 80000, 5);
        // mgr.employeeToManage = -100;
        mgr.setEmployeeToManage(20);
        System.out.println(mgr.toString());
    }
}
