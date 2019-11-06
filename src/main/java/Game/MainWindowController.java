package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainWindowController
{
    MainWindow mw = new MainWindow();

    @FXML
    public void clickStartButton(ActionEvent actionEvent)
    {
        System.out.println("Hello");
    }

    public void clickExitButton(ActionEvent actionEvent)
    {

    }
}
