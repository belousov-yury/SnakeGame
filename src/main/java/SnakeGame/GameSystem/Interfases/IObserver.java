package SnakeGame.GameSystem.Interfases;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;

import java.io.IOException;

public interface IObserver
{
    void notification(EnumRequest enumRequest);
    EnumAddressName getName();
}
