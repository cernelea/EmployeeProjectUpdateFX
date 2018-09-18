package com.cernelea;

import com.cernelea.employeeModel.Employee;
import com.cernelea.employeeModel.EmployeeData;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class mainController {

    @FXML

    private BorderPane mainPanel;

    @FXML
    private TableView<Employee> employeeTable;

    public void initialize() {

        employeeTable.setItems(EmployeeData.getInstance().getEmployeeList());

    }

    @FXML

    public void initiliazeAddDialog() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add a new employee");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("employeeDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException ex) {
            System.out.println("Couldn't load the dialog");

            return;

        }

        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());

        dialog.showAndWait();

        EmployeeController employeeController = fxmlLoader.getController();
        Employee employee = employeeController.createEmployee();
        EmployeeData.getInstance().addEmployee(employee);

    }

    @FXML

    public void initiliazeEditDialog() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No employee selected");
            alert.setContentText("Please select the contact you want to edit");
            alert.showAndWait();

        } else {
            Dialog<ButtonType> dialog = new Dialog<ButtonType>();
            dialog.initOwner(mainPanel.getScene().getWindow());
            dialog.setTitle("Edit Contact");

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("employeeDialog.fxml"));

            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());

            } catch (IOException ex) {

                System.out.println("Couldn't find the dialog");
                return;

            }

            EmployeeController employeeController = fxmlLoader.getController();
            employeeController.setEmployeeFields(selectedEmployee);
            int index = EmployeeData.getInstance().getEmployeeList().indexOf(selectedEmployee);

            Window window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());

            dialog.showAndWait();

            EmployeeData.getInstance().removeContact(selectedEmployee);
            Employee newEmployee = employeeController.createEmployee();
            EmployeeData.getInstance().getEmployeeList().add(index, newEmployee);

//            employeeController.updateEmployee(selectedEmployee);
        }

    }

    @FXML

    public void deleteAction() {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        if (employee == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No employee selected");
            alert.setContentText("Please select the contact you want to delete");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete employee");
            alert.setContentText("Are you sure you want to delete contact: " + employee.getFirstName() + " " + employee.getLastName());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                EmployeeData.getInstance().removeContact(employee);

            }

        }

    }

    @FXML

    public void exitAction() {
        Platform.exit();

    }

    @FXML

    public void deleteKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            deleteAction();

        }

    }

}
