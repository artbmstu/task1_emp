package artemiev;

import java.math.BigDecimal;

public class Employee {
    private String name, surname, department;
    private BigDecimal salary;
    private int num;

    public Employee(int num, String name, String surname, String department, BigDecimal salary){
        this.num = num;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getFullInfo(){
        return num + " " + name + " " + surname + " " + department + " " + salary;
    }
}
