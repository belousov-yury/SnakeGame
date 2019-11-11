package SnakeGame.GameSystem;

import SnakeGame.Game.GameModel;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GameWindowController implements IObserver
{
    @FXML
    Pane pane;

    private EnumAddressName name;
    private IObservable broker;
    private GameModel gameModel;

    public GameWindowController()
    {
        name = EnumAddressName.GameWindowController;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        gameModel = new GameModel((int)pane.getHeight(), (int)pane.getWidth());
        //        box.getChildren().add(new Button("Hello"));
    }

    @FXML
    public void exitGame(ActionEvent actionEvent)
    {
        broker.notifyObservers(EnumAddressName.All, EnumRequest.EXIT_THE_GAME);
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {
            case SCENE_UPDATE:
                updateScene();
                break;
            case THE_GAME_IS_RUNNING:
                break;
        }
    }

    public void updateScene()
    {

    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }
}
