package com.cernelea;

import com.cernelea.employeeModel.Employee;
import com.cernelea.employeeModel.EmployeeData;
import com.cernelea.employeeModel.Jobs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EmployeeController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;


    @FXML
    private TextField salaryField;

    @FXML
    private DatePicker datePicker;

    @FXML

    private ComboBox comboBox;

    @FXML

    private Button okButton;

    @FXML

    private Button cancelButton;


    @FXML
    private Label exceptionLabel;


    public void initialize() {
        for (Jobs job : Jobs.values()) {

            comboBox.getItems().add(job);
            comboBox.getSelectionModel().selectFirst();


        }
        datePicker.setValue(LocalDate.now());
        okButton.setDisable(true);


    }

    public void setEmployeeFields(Employee employee) {
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());

        salaryField.setText(String.valueOf(employee.getSalary()));
        comboBox.getSelectionModel().select(employee.getJob());
        datePicker.setValue(employee.getBirthDay());


    }


    @FXML
    public Employee createEmployee() {
        try {
            String firstName = this.firstNameField.getText();
            String secondName = this.lastNameField.getText();
            Double salary = Double.parseDouble(this.salaryField.getText());
            LocalDate birthDay = this.datePicker.getValue();
            String jobText = this.comboBox.getSelectionModel().getSelectedItem().toString();
            Jobs job = Jobs.valueOf(jobText);


            Employee employee = new Employee(firstName, secondName, birthDay, job, salary);


            exceptionLabel.setText("");
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            return employee;
        } catch (NumberFormatException ex) {

            exceptionLabel.setText("You entered wrong salary input");

        }

        return null;
    }


    public void checkLabels() {
        String firstName = this.firstNameField.getText();
        String lastName = this.lastNameField.getText();
        String salary = this.salaryField.getText();

        Boolean isFirstNameFieldEmpty = firstName.isEmpty() || firstName.trim().isEmpty();
        Boolean isLastNameFieldEmpty = lastName.isEmpty() || lastName.trim().isEmpty();
        Boolean isSalaryFieldEmpty = salary.isEmpty() || salary.trim().isEmpty();


        if (isFirstNameFieldEmpty || isLastNameFieldEmpty || isSalaryFieldEmpty) {

            okButton.setDisable(true);

        } else {

            okButton.setDisable(false);
        }


    }


//    public void updateEmployee(Employee employee) {
//        employee.setFirstName(firstNameField.getText());
//        employee.setLastName(lastNameField.getText());
//        employee.setSalary(Double.parseDouble(salaryField.getText()));
//        employee.setBirthDay(datePicker.getValue());
//        employee.setJob(Jobs.valueOf(comboBox.getSelectionModel().getSelectedItem().toString()));
//
//
//    }


    @FXML
    public void setCancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();


    }


}
