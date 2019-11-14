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
//            timer.cancel();
//            timer.scheduleAtFixedRate(task, 0, Speed);
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
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {

                broker.notifyObservers(EnumAddressName.GameModel,EnumRequest.NEXT_FRAME);
            }
        });
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
            case GAME_OVER:
                gameOver();
                break;
        }
    }

    public void gameOver()
    {
        timer.cancel();
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
