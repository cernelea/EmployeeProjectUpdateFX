package com.cernelea.employeeModel;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Jobs job;
    private double salary;

    public Employee(String firstName, String lastName, LocalDate birthDay, Jobs job,double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.job = job;
        this.salary=salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return "Employee :" +this.firstName+" "+this.lastName+" Birthday: "+this.birthDay+ "Job: "+this.job+" Salary :" +this.salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(birthDay, employee.birthDay) &&
                job == employee.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDay, job, salary);
    }
}
