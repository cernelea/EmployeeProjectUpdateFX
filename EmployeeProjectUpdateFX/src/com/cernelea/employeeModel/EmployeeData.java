package com.cernelea.employeeModel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;


public class EmployeeData {

    private static EmployeeData instance = new EmployeeData();


    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private static String filename = "EmployeeList.txt";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }

    public static EmployeeData getInstance() {
        return instance;
    }


    public void addEmployee(Employee employee) {
        if (!this.employeeList.contains(employee)) {

            employeeList.add(employee);
        }
    }

    public void removeContact(Employee employee) {
        this.employeeList.remove(employee);

    }

    public void updateContact(Employee employee,String firstName,String lastName,LocalDate birthDay,Jobs job,Double salary){
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setJob(job);
        employee.setBirthDay(birthDay);
        employee.setSalary(salary);

    }




    public void loadTodoItems() throws IOException {

        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String firstName = itemPieces[0];
                String lastName = itemPieces[1];
                String dateString = itemPieces[2];
                String job = itemPieces[3];
                Double salary = Double.parseDouble(itemPieces[4]);

                LocalDate date = LocalDate.parse(dateString, formatter);
                Employee employee = new Employee(firstName, lastName, date, Jobs.valueOf(job), salary);
                employeeList.add(employee);
            }

        } finally {
            if (br != null) {
                br.close();

            }

        }

    }









    public void storeToDoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Employee> iter = employeeList.iterator();
            while (iter.hasNext()) {

                Employee item = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s",
                        item.getFirstName(),
                        item.getLastName(),
                        item.getBirthDay().format(formatter),
                        item.getJob().toString(),
                        String.valueOf(item.getSalary())));

                bw.newLine();

            }

        } finally {
            if (bw != null) {

                bw.close();
            }

        }

    }


}
