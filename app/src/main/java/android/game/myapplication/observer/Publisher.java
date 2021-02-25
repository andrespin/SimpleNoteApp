package android.game.myapplication.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Observer> observers;


    public Publisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    public void notify(Boolean isEditFunctionTurned) {
        for (Observer observer : observers) {
            observer.updateBoolean(isEditFunctionTurned);
        }
    }
}