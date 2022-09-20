package game.gameplay.collision;

import ecs_architecture.standard.GameComponent;

public class CollisionComponent extends GameComponent
{

    private final double width;

    private final double height;

    private boolean colliding;

    private CollisionComponent( double width , double height )
    {
        this.width = width;
        this.height = height;
        colliding = false;
    }

    public static CollisionComponent from( int widthFromCenter , int heightFromCenter )
    {

        return new CollisionComponent( widthFromCenter , heightFromCenter );
    }

    public double getWidth( )
    {
        return width;
    }

    public double getHeight( )
    {
        return height;
    }

    boolean isColliding( )
    {
        return colliding;
    }

    void setColliding( boolean colliding )
    {
        this.colliding = colliding;
    }

}