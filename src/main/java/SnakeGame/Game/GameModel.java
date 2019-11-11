package SnakeGame.Game;

import SnakeGame.GameSystem.Broker;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;

import java.awt.*;
import java.util.Timer;

public class GameModel implements IObserver
{
    IObservable broker;

    private EnumAddressName name;

    private Snake snake;

    private GameField gameField;

    public GameModel(int height, int width)
    {
        name = EnumAddressName.GameModel;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        goGame(height, width);
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {
            case EXIT_THE_GAME:
                break;
        }
    }

    public void goGame(int height, int width)
    {
        gameField = new GameField(width,height);
        snake = new Snake(new Point(gameField.getFieldWidth()/2,gameField.getFieldHeight()/2));
        broker.notifyObservers(EnumAddressName.GameWindowController, EnumRequest.SCENE_UPDATE);

    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
