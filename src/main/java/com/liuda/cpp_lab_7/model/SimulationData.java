package com.liuda.cpp_lab_7.model;

public class SimulationData {
    private int tables;
    private int waiters;
    private int tableGuests;
    private int foodGuests;

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getWaiters() {
        return waiters;
    }

    public void setWaiters(int waiters) {
        this.waiters = waiters;
    }

    public int getTableGuests() {
        return tableGuests;
    }

    public void setTableGuests(int tableGuests) {
        this.tableGuests = tableGuests;
    }

    public int getFoodGuests() {
        return foodGuests;
    }

    public void setFoodGuests(int foodGuests) {
        this.foodGuests = foodGuests;
    }
}
