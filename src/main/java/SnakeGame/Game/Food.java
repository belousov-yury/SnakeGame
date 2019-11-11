package SnakeGame.Game;

import SnakeGame.Game.Interfaces.IFood;

import java.awt.*;
import java.util.Random;

public class Food implements IFood
{
    private Point coordinate;
    private Color color;

    public Food(Point point)
    {
        Color[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.RED,
                Color.CYAN };
        Random random = new Random();
        color = colors[random.nextInt(colors.length)];

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

    }

    public Color getColor()
    {
        return color;
    }
}
