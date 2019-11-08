package SnakeGame.GameSystem.Interfases;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;


public interface IObserver
{
    void notification(EnumRequest enumRequest);
    EnumAddressName getName();
}
