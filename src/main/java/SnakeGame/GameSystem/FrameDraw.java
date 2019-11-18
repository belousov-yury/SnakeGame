package SnakeGame.GameSystem;

import SnakeGame.Game.GameModel;
import SnakeGame.Game.Snake;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameDraw
{
    private GameModel gameModel;
    private List<Point> snakeCollection;

    private int snakePartRadius = 20;
    private double distanceBetweenPoints = 1.5;


    public FrameDraw()
    {
        gameModel = new GameModel();
        snakeCollection = new ArrayList<>();
    }

    public Pane getFrame()
    {
        snakeCollection = gameModel.getSnake();

        Pane pane = new Pane();

        List<Ellipse> snake = new ArrayList<Ellipse>();

        Rectangle field = new Rectangle(coordinateTransformation(gameModel.getFieldWidth()) + snakePartRadius, coordinateTransformation(gameModel.getFieldHeight()) + snakePartRadius);
        field.setFill(Color.YELLOW);
        pane.getChildren().add(field);

        Ellipse food = new Ellipse(snakePartRadius, snakePartRadius);
        food.setCenterX(coordinateTransformation(gameModel.getFoodCoordinate().getX()));
        food.setCenterY(coordinateTransformation(gameModel.getFoodCoordinate().getY()));
        food.setFill(gameModel.getFoodColor());

        pane.getChildren().add(food);

        for(int i = 0; i < snakeCollection.size(); i ++)
        {
            Ellipse ell = new Ellipse(snakePartRadius,snakePartRadius);
            ell.setCenterX(coordinateTransformation(snakeCollection.get(i).getX()));
            ell.setCenterY(coordinateTransformation(snakeCollection.get(i).getY()));
            snake.add(ell);
        }

        pane.getChildren().addAll(snake);
        return pane;
    }

    public Pane getGameOverFrame()
    {
        Pane pane = new Pane();

        Text gameOver = new Text("GAME OVER");
        gameOver.setFont(new Font(44));
        gameOver.setLayoutX(70);
        gameOver.setLayoutY(170);
        gameOver.setFill(Color.RED);

        pane.getChildren().add(gameOver);
        return pane;
    }

    public double coordinateTransformation(double coord)
    {
        return coord + snakePartRadius + (snakePartRadius * distanceBetweenPoints * coord);
    }
}
