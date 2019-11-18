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
    private boolean updateSpeedCheck = false;
    private EnumAddressName name;
    private IObservable broker;
    private Timer timer;
    private int Speed;
    MyTimerTask task;

    public int getSpeed()
    {
        return Speed;
    }

    public void setSpeed(int speed)
    {
        if(Speed > 20)
        {
            Speed = speed;
            updateSpeedCheck = true;

        }
    }

    public MyTimerTask(int speed)
    {
        name = EnumAddressName.Timer;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        Speed = speed;

    }

    @Override
    public void run()
    {
        if(updateSpeedCheck)
        {
            updateSpeed();
        }
        else
        {
            broker.notifyObservers(EnumAddressName.GameModel,EnumRequest.NEXT_FRAME);
        }
    }

    private void updateSpeed()
    {
        updateSpeedCheck = false;
        timer.cancel();
        timer = null;
        task = null;

    }

    public void goGame(MyTimerTask timerTask)
    {
        task = timerTask;
        timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, Speed);
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {

        }
    }

    public void gameOver()
    {
        timer.cancel();
        timer = null;
        task = null;
        broker.removeObserver(this);
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
