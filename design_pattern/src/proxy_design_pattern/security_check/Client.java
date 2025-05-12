package 
proxy_design_pattern.security_check;

public class Client {

    public static void main(String[] args) {

        IEmployee employee = new EmployeeTable();
        System.out.println(employee.getEmployee());

        IEmployee employee1 = new EmployeeTableProxy("ADMIN");
        System.out.println(employee1.getEmployee());

        IEmployee employee2 = new EmployeeTableProxy("USER");
        System.out.println(employee2.getEmployee());

    }
}
