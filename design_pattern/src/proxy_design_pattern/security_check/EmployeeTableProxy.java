package 
proxy_design_pattern.security_check;

public class EmployeeTableProxy implements IEmployee{

    String role;
    EmployeeTable employeeTable;
    
    public EmployeeTableProxy(String role) {
        this.role = role;
        // here I'm creating new Employee.... use-case is different...
        employeeTable = new EmployeeTable();
    }

    @Override
    public String getEmployee() {
        // adding condition before making real request to other object.
        if(role.equals("ADMIN")) {
            return employeeTable.getEmployee();
        }
        return "Access Denied";
    }
}
