package ru.artemiev.tstask1;

import java.math.BigDecimal;

class Employee {
    private final String name, surname;
    private final BigDecimal salary;
    private final int num;

    Employee(int num, String name, String surname, BigDecimal salary){
        this.num = num;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    BigDecimal getSalary() {
        return salary;
    }

    String getFullInfo(){
        return num + " " + name + " " + surname + " " + " " + salary;
    }
}
