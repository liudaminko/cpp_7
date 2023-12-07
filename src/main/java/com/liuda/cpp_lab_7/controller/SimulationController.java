package com.liuda.cpp_lab_7.controller;

import com.liuda.cpp_lab_7.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    @FXML
    private Button btnResume;
    @FXML
    private Button btnSuspend;
    @FXML
    private TableView<ThreadInfo> tableView;
    private ObservableList<ThreadInfo> threadInfoList;
    private SimulationData simulationData;

    @FXML public void onBtnResumeClick() {
        System.out.println("Thread resumed");
        tableView.getSelectionModel().getSelectedItem().getThread().resume();
    }

    @FXML public void onBtnSuspendClick() {
        System.out.println("Thread suspended");
        tableView.getSelectionModel().getSelectedItem().getThread().suspend();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        threadInfoList = FXCollections.observableArrayList();

        TableColumn<ThreadInfo, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setPrefWidth(200);

        TableColumn<ThreadInfo, String> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());
        priorityColumn.setPrefWidth(25);


        TableColumn<ThreadInfo, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        stateColumn.setPrefWidth(250);

        tableView.getColumns().addAll(nameColumn, priorityColumn, stateColumn);
        tableView.setItems(threadInfoList);
    }

    public void startSimulation() {
        Restaurant restaurant = new Restaurant(simulationData.getTables());

        for (int i = 1; i <= simulationData.getWaiters(); i++) {
            ThreadInfo waiterThreadInfo = createThreadInfo("Waiter-" + i);
            Waiter waiter = new Waiter("Waiter-" + i, restaurant, waiterThreadInfo);
            restaurant.addWaiter(waiter, waiterThreadInfo);
        }

        for (int i = 1; i <= simulationData.getTableGuests(); i++) {
            ThreadInfo tableGuestThreadInfo = createThreadInfo("TableGuest" + i);
            TableGuest tableGuest = new TableGuest(restaurant);
            restaurant.addGuest(tableGuest, tableGuestThreadInfo);
        }

        for (int i = 1; i <= simulationData.getFoodGuests(); i++) {
            ThreadInfo foodGuestThreadInfo = createThreadInfo("FoodGuest" + i);
            FoodGuest foodGuest = new FoodGuest(restaurant);
            restaurant.addGuest(foodGuest, foodGuestThreadInfo);
        }

        restaurant.startRestaurant();
    }

    private ThreadInfo createThreadInfo(String name) {
        ThreadInfo threadInfo = new ThreadInfo(name, "", "");
        threadInfoList.add(threadInfo);
        return threadInfo;
    }


    public void setSimulationData(SimulationData simulationData) {
        this.simulationData = simulationData;
    }
}
