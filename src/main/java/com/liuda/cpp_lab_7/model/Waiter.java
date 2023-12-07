package com.liuda.cpp_lab_7.model;

import java.util.Random;

public class Waiter extends Thread {
    private String name;
    private Restaurant restaurant;
    private ThreadInfo threadInfo;

    public Waiter(String name, Restaurant restaurant, ThreadInfo threadInfo) {
        this.name = name;
        this.restaurant = restaurant;
        this.threadInfo = threadInfo;
    }

    @Override
    public void run() {
        this.threadInfo.update(Thread.currentThread());
        Thread.currentThread().setPriority(new Random().nextInt(MAX_PRIORITY) + 1);
        while (true) {
            acceptGuestOrder();
        }
    }

    public void acceptGuestOrder() {
        Guest guestWithOrder = restaurant.getNextGuestToAcceptOrder();
        if (guestWithOrder != null) {
            updateThreadInfo(Thread.currentThread().getPriority() + "", "Serving " + guestWithOrder.getName());
            System.out.println(name + " serves a guest: " + guestWithOrder.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            guestWithOrder.setStatus(GuestStatus.ORDER_PLACED);
            cookForGuest(guestWithOrder);
        }
    }

    private void cookForGuest(Guest guest) {
        if (guest instanceof FoodGuest) {
            updateThreadInfo(Thread.currentThread().getPriority() + "", "Cooking");
            System.out.println(name + " is cooking for " + guest.getName());
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " has finished cooking for " + guest.getName());
            updateThreadInfo(Thread.currentThread().getPriority() + "", "Finished cooking");
            restaurant.serveGuest(guest);
        }
    }

    private void updateThreadInfo(String priority, String state) {
        threadInfo.update(priority, state);
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
}
