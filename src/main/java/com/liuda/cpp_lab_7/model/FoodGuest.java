package com.liuda.cpp_lab_7.model;

public class FoodGuest extends Guest {
    private boolean isFoodReady = false;

    public FoodGuest(Restaurant restaurant) {
        super(restaurant);
        updateThreadInfo("FoodGuest " + getName());
    }

    @Override
    protected void enterRestaurant() {
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Entering");
        System.out.println(Thread.currentThread().getName() + " enters the restaurant.");
    }

    @Override
    protected void exitRestaurant() {
        status = GuestStatus.LEAVING;
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Exiting");
        System.out.println(Thread.currentThread().getName() + " exits the restaurant.");
    }

    @Override
    protected void makeOrder() {
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Ready to order");
        System.out.println(Thread.currentThread().getName() + " is ready to order. Notifying the waiter.");
        setStatus(GuestStatus.WAITING_TO_ORDER);
        restaurant.addGuestToAcceptOrder(this);
        synchronized (this) {
            while (!isFoodReady) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            eat();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void eat() throws InterruptedException {
        setStatus(GuestStatus.EATING);
        updateThreadInfo(Thread.currentThread().getPriority() + "", "Eating");
        System.out.println(Thread.currentThread().getName() + " is eating.");
        sleep(3000);
        exitRestaurant();
    }

    public boolean isFoodReady() {
        return isFoodReady;
    }

    public void setFoodReady(boolean foodReady) {
        synchronized (this) {
            isFoodReady = foodReady;
            if (foodReady) {
                notifyAll();
            }
        }
    }
}

