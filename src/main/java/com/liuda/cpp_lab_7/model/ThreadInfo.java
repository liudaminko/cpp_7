package com.liuda.cpp_lab_7.model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThreadInfo {
    private final StringProperty name;
    private final StringProperty priority;
    private final StringProperty state;
    private Thread thread;

    public ThreadInfo(String name, String priority, String state) {
        this.name = new SimpleStringProperty(name);
        this.priority = new SimpleStringProperty(priority);
        this.state = new SimpleStringProperty(state);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void update(String priority, String state) {
        Platform.runLater(() -> {
            this.priority.set(priority);
            this.state.set(state);
        });
    }

    public void update(String name) {
        Platform.runLater(() -> {
            this.name.set(name);
        });
    }

    public void update(Thread thread) {
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }
}
