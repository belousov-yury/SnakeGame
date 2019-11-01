package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application
{
    public static void goLaunch()
    {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));

            Scene scene = new Scene(root, 400, 400);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Snake Game");

            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
