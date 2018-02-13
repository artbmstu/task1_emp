package ru.artemiev.tstask1;

import java.math.BigDecimal;

class Employee {
    private String name, surname, department;
    private BigDecimal salary;
    private int num;

    Employee(int num, String name, String surname, String department, BigDecimal salary){
        this.num = num;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    String getDepartment() {
        return department;
    }

    BigDecimal getSalary() {
        return salary;
    }

    String getFullInfo(){
        return num + " " + name + " " + surname + " " + department + " " + salary;
    }
}
