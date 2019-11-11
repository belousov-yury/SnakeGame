package SnakeGame.Game;

import SnakeGame.Game.Interfaces.IGameField;

public class GameField implements IGameField
{
    private int fieldHeight;
    private int fieldWidth;


    public GameField(int fieldWidth, int fieldHeight)
    {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
    }

    @Override
    public int getFieldHeight()
    {
        return this.fieldHeight;
    }

    @Override
    public void setFieldHeight(int fieldHeight)
    {
        this.fieldHeight = fieldHeight;
    }

    @Override
    public int getFieldWidth()
    {
        return this.fieldWidth;
    }

    @Override
    public void setFieldWidth(int fieldWidth)
    {
        this.fieldWidth = fieldWidth;
    }
}
