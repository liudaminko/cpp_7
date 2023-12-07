package com.liuda.cpp_lab_7.model;

import javafx.application.Platform;

import java.util.Random;

public abstract class Guest extends Thread {
    protected Restaurant restaurant;
    protected ThreadInfo threadInfo;
    protected GuestStatus status;

    public Guest(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.status = GuestStatus.ENTERING;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setPriority(new Random().nextInt(MAX_PRIORITY) + 1);
            this.threadInfo.update(Thread.currentThread());
            enterRestaurant();
            sleep(3000);
            makeOrder();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void enterRestaurant();

    protected abstract void exitRestaurant();

    protected abstract void makeOrder();

    protected void updateThreadInfo(String name) {
        Platform.runLater(() -> threadInfo.update(name));
    }

    protected void updateThreadInfo(String priority, String state) {
        Platform.runLater(() -> threadInfo.update(priority, state));
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ThreadInfo getThreadInfo() {
        return threadInfo;
    }

    public void setThreadInfo(ThreadInfo threadInfo) {
        this.threadInfo = threadInfo;
    }

    public GuestStatus getStatus() {
        return status;
    }

    public void setStatus(GuestStatus status) {
        this.status = status;
    }
}
