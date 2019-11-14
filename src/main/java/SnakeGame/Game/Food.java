package SnakeGame.Game;

import SnakeGame.Game.Interfaces.IFood;

import java.awt.Point;
import java.util.Random;
import javafx.scene.paint.Color;

public class Food implements IFood
{
    private Point coordinate;
    private Color color;

    public Food(Point point)
    {

        color = generateColor();

        coordinate = point;
    }

    @Override
    public Point getFoodCoordinate()
    {
        return coordinate;
    }

    @Override
    public void setFoodCoordinate(Point coordinate)
    {
        this.coordinate = coordinate;
        this.color = generateColor();
    }

    private Color generateColor()
    {
        Color[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED, Color.CYAN };

        Random random = new Random();

        return colors[random.nextInt(colors.length)];
    }

    public Color getColor()
    {
        return color;
    }
}
