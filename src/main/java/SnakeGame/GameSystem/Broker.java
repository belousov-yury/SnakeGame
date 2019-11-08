package SnakeGame.GameSystem;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Broker implements IObservable
{
    private static Broker instance;

    private List<IObserver> observers;

    private Broker()
    {
        observers = new ArrayList<>();
    }

    public static Broker getInstance()
    {
        if(instance == null)
        {
            instance = new Broker();
        }
        return instance;
    }

    @Override
    public void registerObserver(IObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer)
    {
        int i = observers.indexOf(observer);
        if(i >= 0)
        {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers(EnumAddressName name, EnumRequest enumRequest)
    {
        var observerList = getObserverList();
        if(name == EnumAddressName.All)
        {
            for(IObserver a: observerList)
            {
                a.notification(enumRequest);
            }
        }
        else
        {
            for(IObserver a: observerList)
            {
                if(a.getName() == name)
                {
                    a.notification(enumRequest);
                }
            }
        }
    }
    private List<IObserver> getObserverList()
    {
        return List.copyOf(observers);
    }
}
