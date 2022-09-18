package game.components;

import ecs_architecture.standard.GameComponent;

public class DirectionComponent extends GameComponent
{

    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST
    }

    private Direction current;

    public DirectionComponent( Direction current )
    {
        this.current = current;
    }

    public Direction getCurrent( )
    {
        return current;
    }

    public void setNorth( )
    {
        current = Direction.NORTH;
    }

    public void setSouth( )
    {
        current = Direction.SOUTH;
    }

    public void setEast( )
    {
        current = Direction.EAST;
    }

    public void setWest( )
    {
        current = Direction.WEST;
    }

}