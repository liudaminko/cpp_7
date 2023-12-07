package com.liuda.cpp_lab_7.model;

public class TableGuest extends Guest {
    public TableGuest(Restaurant restaurant) {
        super(restaurant);
        updateThreadInfo("TableGuest " + getName());
        setStatus(GuestStatus.WAITING_FOR_TABLE);
    }

    @Override
    protected void enterRestaurant() {
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Entering");
        System.out.println(Thread.currentThread().getName() + " enters the restaurant.");
    }

    @Override
    protected void exitRestaurant() {
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Exiting");
        System.out.println(Thread.currentThread().getName() + " exits the restaurant.");
    }

    @Override
    protected void makeOrder() {
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Ready to reserve a table");
        System.out.println(Thread.currentThread().getName() + " is ready to reserve a table. Waiting for available table...");
        while (!restaurant.reserveTable()) {}
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Table reserved. Available tables: " + restaurant.getAvailableTables());
        System.out.println(Thread.currentThread().getName() + " has reserved a table. Available tables: " + restaurant.getAvailableTables());
        setStatus(GuestStatus.AT_THE_TABLE);
        restaurant.addGuestToAcceptOrder(this);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        releaseTable();
    }

    public void releaseTable() {
            updateThreadInfo(Thread.currentThread().getPriority() + "", "Releasing the table");
            System.out.println(Thread.currentThread().getName() + " is releasing the table.");
            restaurant.releaseTable();
            updateThreadInfo(Thread.currentThread().getPriority() + "", "Table released. Available tables: " + restaurant.getAvailableTables());
            System.out.println("Table released. Available tables: " + restaurant.getAvailableTables());
    }
}
