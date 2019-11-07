package SnakeGame.GameSystem;

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
    public void start(Stage stage) throws Exception
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

            primaryStage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void startGame() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/GameFXML.fxml"));

        Scene scene = new Scene(root, 1920, 1080);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake SnakeGame.Game");

        broker.notifyObservers(EnumRequest.THE_GAME_IS_RUNNING);
    }


    @Override
    public void notification(EnumRequest enumRequest) throws IOException
    {
        if(enumRequest == EnumRequest.START_THE_GAME)
        {
            startGame();
        }
    }

    @Override
    public void setName(EnumAddressName name)
    {
        this.name = name;
    }


}
