package SnakeGame.Game.Interfaces;

import java.awt.*;
import java.util.List;

public interface ISnake
{
    public int getSnakeSize();

    public Point getSnakeHeadCoordinate();

    public List<Point> getAllSnakePartCoordinate();

    public void addSnakePart();

    public void movement(int x, int y);
}

