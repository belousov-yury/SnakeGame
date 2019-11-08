package SnakeGame.GameSystem;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameWindowController implements IObserver
{
    private EnumAddressName name;
    private IObservable broker;

    public GameWindowController()
    {
        name = EnumAddressName.GameWindowController;
        broker = Broker.getInstance();
        broker.registerObserver(this);
    }

    @FXML
    public void exitGame(ActionEvent actionEvent)
    {
        broker.notifyObservers(EnumAddressName.All, EnumRequest.EXIT_THE_GAME);

    }

    @Override
    public void notification(EnumRequest enumRequest)
    {

    }

    @Override
    public EnumAddressName getName()
    {
        return null;
    }
}
