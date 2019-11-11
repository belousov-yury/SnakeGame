package SnakeGame.GameSystem;

import SnakeGame.Game.GameModel;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application implements IObserver
{
    private EnumAddressName name;
    private IObservable broker;
    private Stage primaryStage;

    public MainWindow()
    {
        name = EnumAddressName.MainWindow;
        broker = Broker.getInstance();
        broker.registerObserver(this);
    }


    public static void goLaunch()
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        try
        {
            primaryStage = stage;

            Parent root = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));

            Scene scene = new Scene(root, 400, 400);

            primaryStage.setScene(scene);
            primaryStage.setMaxHeight(1080);
            primaryStage.setMaxWidth(1920);
            primaryStage.setTitle("Snake SnakeGame.Game");
            primaryStage.centerOnScreen();


            primaryStage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void startGame()
    {
        Parent root = null;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/GameFXML.fxml"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 1920, 1080);



        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.centerOnScreen();


        broker.notifyObservers(EnumAddressName.All, EnumRequest.THE_GAME_IS_RUNNING);
    }
    public void exitGame()
    {
        primaryStage.close();
        broker = null;
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {
            case START_THE_GAME:
                startGame();
                break;
            case EXIT_THE_GAME:
                exitGame();
                break;
            case THE_GAME_IS_RUNNING:
                break;
        }
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }


}
