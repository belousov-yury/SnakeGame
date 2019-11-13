package SnakeGame.Game;

import SnakeGame.Game.Enums.EnumDirection;
import SnakeGame.GameSystem.Broker;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;

import java.awt.*;
import java.util.Random;
import java.util.List;

public class GameModel implements IObserver
{
    IObservable broker;

    private EnumAddressName name;

    private EnumDirection direction;

    private Snake snake;

    private GameField gameField;

    private Food food;
    private MyTimerTask timer;


    public GameModel()
    {
        name = EnumAddressName.GameModel;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        direction = EnumDirection.LEFT;
        gameField = new GameField(400,400);
        snake = new Snake(new Point(gameField.getFieldWidth()/2,gameField.getFieldHeight()/2));
        food = new Food(foodCoordinateCalculate());
    }

    @Override
    public void notification(EnumRequest enumRequest)
    {
        switch(enumRequest)
        {
            case EXIT_THE_GAME:
                break;
            case NEXT_FRAME:
                nextStepCalculate();
                break;
            case KEY_UP:
                changeDirection(EnumDirection.UP);
                break;
            case KEY_DOWN:
                changeDirection(EnumDirection.DOWN);
                break;
            case KEY_LEFT:
                changeDirection(EnumDirection.LEFT);
                break;
            case KEY_RIGHT:
                changeDirection(EnumDirection.RIGTH);
                break;
            case GO_GAME:
                goGame();
                break;
        }
    }

    public void goGame()
    {
        timer = new MyTimerTask();
        timer.goGame();
    }

    public void gameOver()
    {
        broker.notifyObservers(EnumAddressName.All, EnumRequest.GAME_OVER);
    }

    public void nextStepCalculate()
    {
        snakeMovement();
        if(snake.crossingCheck())
        {
            gameOver();
        }

        if(snake.getSnakeHeadCoordinate().equals(food.getFoodCoordinate()))
        {
            snake.addSnakePart();
            food.setFoodCoordinate(foodCoordinateCalculate());
        }

        broker.notifyObservers(EnumAddressName.GameWindowController, EnumRequest.SCENE_UPDATE);
    }

    public void changeDirection(EnumDirection direction)
    {
        switch(direction)
        {
            case UP:
                if(this.direction != EnumDirection.DOWN)
                {
                    this.direction = direction;
                }
                break;

            case DOWN:
                if(this.direction != EnumDirection.UP)
                {
                    this.direction = direction;
                }
                break;

            case RIGTH:
                if(this.direction != EnumDirection.LEFT)
                {
                    this.direction = direction;
                }
                break;

            case LEFT:
                if(this.direction != EnumDirection.RIGTH)
                {
                    this.direction = direction;
                }
                break;
        }
    }

    private Point foodCoordinateCalculate()
    {
        boolean coincidence = true;
        Random rand = new Random();
        Point foodCoordinate = new Point();

        while(coincidence)
        {
            foodCoordinate.x = rand.nextInt(gameField.getFieldWidth());
            foodCoordinate.y = rand.nextInt(gameField.getFieldHeight());
            for(Point a: snake.getAllSnakePartCoordinate())
            {
                if(a.equals(foodCoordinate))
                {
                    coincidence = true;
                }
                else
                {
                    coincidence = false;
                }
            }
        }


        return foodCoordinate;
    }

    @Override
    public EnumAddressName getName()
    {
        return name;
    }

    public void snakeMovement()
    {
        switch(direction)
        {
            case UP:
                snake.movement(0,-1);
                break;

            case DOWN:
                snake.movement(0,1);
                break;

            case LEFT:
                snake.movement(-1,0);
                break;

            case RIGTH:
                snake.movement(1,0);
                break;
        }
    }

    public List<Point> getSnake()
    {
        return snake.getAllSnakePartCoordinate();
    }
    public Point getFood()
    {
        return food.getFoodCoordinate();
    }
}
