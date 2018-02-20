package ru.artemiev.tstask1;

import java.math.BigDecimal;

class Employee {
    private String name;
    private BigDecimal salary;
    private int num;

    Employee(int num, BigDecimal salary, String name){
        this.num = num;
        this.name = name;
        this.salary = salary;
    }

    BigDecimal getSalary() {
        return salary;
    }

    String getFullInfo(){
        return num + " " + name + " " + " " + salary;
    }
}
