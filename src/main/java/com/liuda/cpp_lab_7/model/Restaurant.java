package com.liuda.cpp_lab_7.model;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private List<Guest> guests;
    private List<Waiter> waiters;
    private int availableTables;

    public Restaurant(int tables) {
        this.guests = new LinkedList<>();
        this.waiters = new LinkedList<>();
        this.availableTables = tables;
    }

    public synchronized void addGuestToAcceptOrder(Guest guest) {
        guests.add(guest);
    }

    public synchronized void serveGuest(Guest guest) {
        System.out.println("Guest served: " + guest.getName());
        guests.remove(guest);
        if (guest instanceof FoodGuest foodGuest) {
            foodGuest.setFoodReady(true);
        }
    }

    public synchronized Guest getNextGuestToAcceptOrder() {
        Guest result = guests.stream()
                .filter(guest -> guest.getStatus().equals(GuestStatus.WAITING_TO_ORDER))
                .findFirst()
                .orElse(null);
        if (result != null) {
            result.setStatus(GuestStatus.ORDER_BEING_TAKEN);
        }
        return result;
    }

    public synchronized boolean reserveTable() {
        if (availableTables > 0) {
            availableTables--;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void releaseTable() {
        availableTables++;
    }

    public int getAvailableTables() {
        return availableTables;
    }

    public void startRestaurant() {
        if (!waiters.isEmpty()) {
            waiters.forEach(Waiter::start);
        } else {
            System.out.println("Waiter not set. Cannot start the restaurant.");
        }
    }

    public void addGuest(Guest guest, ThreadInfo threadInfo) {
        guest.setThreadInfo(threadInfo);
        guest.start();
    }

    public void addWaiter(Waiter waiter, ThreadInfo threadInfo) {
        this.waiters.add(waiter);
        waiter.setThreadInfo(threadInfo);
    }
}
