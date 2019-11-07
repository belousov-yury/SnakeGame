package SnakeGame.GameSystem;

import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainWindowController implements IObserver
{

        private IObservable broker;

        public MainWindowController()
        {
            broker = Broker.getInstance();
            broker.registerObserver(this);
        }

    @FXML
    public void clickStartButton(ActionEvent actionEvent) throws IOException
    {
        broker.notifyObservers(EnumRequest.START_THE_GAME);
    }

    public void clickExitButton(ActionEvent actionEvent) throws IOException
    {
        broker.notifyObservers(EnumRequest.EXIT_THE_GAME);
    }

    @Override
    public void notification(EnumRequest enumRequest) throws IOException
    {
        if(enumRequest == EnumRequest.THE_GAME_IS_RUNNING)
        {
            broker.removeObserver(this);
            broker = null;
        }
    }
}
