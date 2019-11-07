package SnakeGame.GameSystem.Interfases;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;

import java.io.IOException;

public interface IObservable
{
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(EnumAddressName name, EnumRequest enumRequest);
}
