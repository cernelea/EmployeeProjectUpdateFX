package com.cernelea;

import com.cernelea.employeeModel.EmployeeData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Employee List");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        try {
            EmployeeData.getInstance().loadTodoItems();


        } catch (IOException ex) {


        }
    }

    @Override
    public void stop() throws Exception {
        try {
            EmployeeData.getInstance().storeToDoItems();


        } catch (IOException ex) {


        }
        catch (NullPointerException ex){



        }
    }
}
