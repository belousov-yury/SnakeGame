package SnakeGame.Game;

import SnakeGame.Game.Enums.EnumDirection;
import SnakeGame.GameSystem.Broker;
import SnakeGame.GameSystem.Enums.EnumAddressName;
import SnakeGame.GameSystem.Enums.EnumRequest;
import SnakeGame.GameSystem.Interfases.IObservable;
import SnakeGame.GameSystem.Interfases.IObserver;
import javafx.scene.paint.Color;
import javafx.application.Platform;

import java.awt.Point;
import java.util.Random;
import java.util.List;

public class GameModel implements IObserver
{
    IObservable broker;

    private boolean movementCheck = true;

    private EnumAddressName name;

    private EnumDirection direction;

    private Snake snake;

    private GameField gameField;

    private Food food;

    private MyTimerTask timer;

    private int speedGame;

    private boolean gameOver = false;


    public GameModel()
    {
        name = EnumAddressName.GameModel;
        broker = Broker.getInstance();
        broker.registerObserver(this);
        gameField = new GameField(18,18);

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
        gameOver = false;
        direction = EnumDirection.LEFT;
        snake = new Snake(new Point(gameField.getFieldWidth()/2,gameField.getFieldHeight()/2), gameField.getFieldWidth()/6);
        food = new Food(foodCoordinateCalculate());
        speedGame = 200;
        timer = new MyTimerTask(speedGame);
        timer.goGame(timer);
    }

    public void nextStepCalculate()
    {
        snakeMovement();
        if(snake.crossingCheck())
        {
            gameOver = true;
        }

        if(snake.getSnakeHeadCoordinate().getX() > gameField.getFieldWidth() || snake.getSnakeHeadCoordinate().getX() < 0 ||
                snake.getSnakeHeadCoordinate().getY() > gameField.getFieldHeight() || snake.getSnakeHeadCoordinate().getY() < 0)
        {
            gameOver = true;
        }

        if(snake.getSnakeHeadCoordinate().equals(food.getFoodCoordinate()))
        {
            snake.addSnakePart();

            food.setFoodCoordinate(foodCoordinateCalculate());

            speedGame -= 5;

            timer.setSpeed(speedGame);
            timer = new MyTimerTask(speedGame);
            timer.goGame(timer);

        }

        if(gameOver == false)
        {
            broker.notifyObservers(EnumAddressName.GameWindowController, EnumRequest.SCENE_UPDATE);
        }
        else
        {
            timer.gameOver();
            broker.notifyObservers(EnumAddressName.All, EnumRequest.GAME_OVER);
        }
    }

    public void changeDirection(EnumDirection direction)
    {
        if(movementCheck)
        {
            switch(direction)
            {
                case UP:
                    if(this.direction != EnumDirection.DOWN)
                    {
                        this.direction = direction;
                        movementCheck = false;
                    }
                    break;

                case DOWN:
                    if(this.direction != EnumDirection.UP)
                    {
                        this.direction = direction;
                        movementCheck = false;
                    }
                    break;

                case RIGTH:
                    if(this.direction != EnumDirection.LEFT)
                    {
                        this.direction = direction;
                        movementCheck = false;
                    }
                    break;

                case LEFT:
                    if(this.direction != EnumDirection.RIGTH)
                    {
                        this.direction = direction;
                        movementCheck = false;
                    }
                    break;
            }
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
                    break;
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
        movementCheck = true;
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
    public Point getFoodCoordinate()
    {
        return food.getFoodCoordinate();
    }
    public Color getFoodColor()
    {
        return food.getColor();
    }

    public int getFieldHeight()
    {
        return gameField.getFieldHeight();
    }

    public int getFieldWidth()
    {
        return gameField.getFieldWidth();
    }
}
