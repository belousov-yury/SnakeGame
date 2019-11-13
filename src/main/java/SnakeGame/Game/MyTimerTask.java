package SnakeGame.Game;

import SnakeGame.GameSystem.Broker;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask implements IObserver
{
    private EnumAddressName name;
    private IObservable broker;
    private Timer timer;
    private TimerTask timerTask;

    public MyTimerTask()
    {
        name = EnumAddressName.Timer;
        broker = Broker.getInstance();
        broker.registerObserver(this);

    }

    @Override
    public void run()
    {
        broker.notifyObservers(EnumAddressName.GameModel,EnumRequest.NEXT_FRAME);
    }

    public void goGame()
    {
        timerTask = new MyTimerTask();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {

        }
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
