package SnakeGame.GameSystem;

import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameWindowController implements IObserver
{
    @FXML
    Pane pane;

    @FXML
    VBox vbox;

    @FXML
    MenuItem Go;

    private EnumAddressName name;
    private IObservable broker;
    FrameDraw frameDraw;

    public GameWindowController()
    {
        name = EnumAddressName.GameWindowController;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        frameDraw = new FrameDraw();
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
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                vbox.getChildren().remove(pane);
                pane = frameDraw.getFrame();
                vbox.getChildren().add(pane);
            }
        });
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }

    @FXML
    public void goGame(ActionEvent actionEvent)
    {
        Go.setDisable(true);
        broker.notifyObservers(EnumAddressName.GameModel, EnumRequest.GO_GAME);
    }



}
