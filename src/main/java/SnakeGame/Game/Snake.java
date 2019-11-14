package SnakeGame.Game;

import SnakeGame.Game.Interfaces.ISnake;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Snake implements ISnake
{
    private int snakeSize;

    public Color getSnakeColor()
    {
        return snakeColor;
    }

    public void setSnakeColor(Color snakeColor)
    {
        this.snakeColor = snakeColor;
    }

    private Color snakeColor = Color.black;

    private List<Point> allSnakePartCoordinate;


    public Snake(Point startCoordinate)
    {
        allSnakePartCoordinate = new ArrayList<Point>();
        allSnakePartCoordinate.add(startCoordinate);

        while(allSnakePartCoordinate.size() < 3)
        {
            allSnakePartCoordinate.add(new Point((int)allSnakePartCoordinate.get(allSnakePartCoordinate.size() - 1).getX() + 1, (int)allSnakePartCoordinate.get(allSnakePartCoordinate.size() - 1).getY()));
        }

        updateSnakeSize();
    }

    @Override
    public int getSnakeSize()
    {
        return this.snakeSize;
    }

    @Override
    public Point getSnakeHeadCoordinate()
    {
        return this.allSnakePartCoordinate.get(0);
    }

    @Override
    public List<Point> getAllSnakePartCoordinate()
    {
        return allSnakePartCoordinate;
    }

    @Override
    public void addSnakePart()
    {
        allSnakePartCoordinate.add(new Point());
        updateSnakeSize();
    }

    @Override
    public void movement(int x, int y)
    {
        for(int i = snakeSize - 1; i > 0; i --)
        {
            allSnakePartCoordinate.get(i).setLocation(allSnakePartCoordinate.get(i - 1));
        }
        Point point = new Point((int)allSnakePartCoordinate.get(0).getX() + x, (int)allSnakePartCoordinate.get(0).getY() + y);
        allSnakePartCoordinate.get(0).setLocation(point);
    }

    public boolean crossingCheck()
    {
        for(int i = 1; i < snakeSize; i ++)
        {
            if(allSnakePartCoordinate.get(i).getX() == allSnakePartCoordinate.get(0).getX() && allSnakePartCoordinate.get(i).getY() == allSnakePartCoordinate.get(0).getY())
            {
                return true;
            }
        }

        return false;
    }

    private void updateSnakeSize()
    {
        snakeSize = allSnakePartCoordinate.size();
    }
}
