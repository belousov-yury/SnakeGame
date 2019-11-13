package SnakeGame.GameSystem;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainWindowController implements IObserver
{
    @FXML
    private VBox box;
        private EnumAddressName name;
        private IObservable broker;

        public MainWindowController()
        {
            name = EnumAddressName.MainWindow;
            broker = Broker.getInstance();
            broker.registerObserver(this);
        }

    @FXML
    public void clickStartButton(ActionEvent actionEvent)
    {
        broker.notifyObservers(EnumAddressName.All, EnumRequest.START_THE_GAME);
    }

    public void clickExitButton(ActionEvent actionEvent)
    {
        broker.notifyObservers(EnumAddressName.All, EnumRequest.EXIT_THE_GAME);

    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {
            case THE_GAME_IS_RUNNING:
                broker.removeObserver(this);
                broker = null;
                break;
        }

    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
