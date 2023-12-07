package com.liuda.cpp_lab_7.controller;

import com.liuda.cpp_lab_7.HelloApplication;
import com.liuda.cpp_lab_7.model.SimulationData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationInfoController{
    @FXML
    private Button submitBtn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tablesTxt;
    @FXML
    private TextField waitersTxt;
    @FXML
    private TextField tableGuestsTxt;
    @FXML
    private TextField foodGuestsTxt;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void onBtnSubmitClick(ActionEvent event) throws IOException {
        SimulationData simulationData = new SimulationData();
        simulationData.setTables(Integer.parseInt(tablesTxt.getText()));
        simulationData.setWaiters(Integer.parseInt(waitersTxt.getText()));
        simulationData.setTableGuests(Integer.parseInt(tableGuestsTxt.getText()));
        simulationData.setFoodGuests(Integer.parseInt(foodGuestsTxt.getText()));

        System.out.println("opening simulation window");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("simulation.fxml"));
        Parent root = fxmlLoader.load();
        SimulationController simulationController = fxmlLoader.getController();
        simulationController.setSimulationData(simulationData);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        simulationController.startSimulation();
    }

}
