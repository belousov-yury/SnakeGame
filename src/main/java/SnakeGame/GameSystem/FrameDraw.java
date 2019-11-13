package SnakeGame.GameSystem;

import SnakeGame.Game.GameModel;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameDraw
{
    private GameModel gameModel;



    public FrameDraw()
    {
        gameModel = new GameModel();
    }

    public Pane getFrame()
    {
        Pane pane = new Pane();
        List<Ellipse> snake = new ArrayList<Ellipse>();
        for(Point a:gameModel.getSnake())
        {
            Ellipse ell = new Ellipse(3,3);
            ell.setLayoutX(a.x);
            ell.setLayoutY(a.y);
            snake.add(ell);
        }
        pane.getChildren().addAll(snake);

        return pane;
    }
}
