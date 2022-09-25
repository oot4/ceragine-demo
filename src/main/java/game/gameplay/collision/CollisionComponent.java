package game.gameplay.collision;

import ecs_architecture.standard.GameComponent;

import java.awt.*;
import java.awt.geom.Point2D;

public class CollisionComponent extends GameComponent
{

    private final double width;

    private final double height;

    private final Point2D offset;

    private boolean colliding;

    private CollisionComponent( double width , double height )
    {
        this.width = width;
        this.height = height;
        offset = new Point2D.Double( 0 , 0 );
        colliding = false;
    }

    private CollisionComponent( Point2D offset , double width , double height )
    {
        this.width = width;
        this.height = height;
        this.offset = offset;
        colliding = false;
    }

    public static CollisionComponent from( Point offset , int width , int height )
    {
        return new CollisionComponent( offset , width , height );
    }

    public static CollisionComponent from( int width , int height )
    {
        return new CollisionComponent( width , height );
    }

    double getWidth( )
    {
        return width;
    }

    double getHeight( )
    {
        return height;
    }

    Point2D getOffset( )
    {
        return offset;
    }

    public boolean isColliding( )
    {
        return colliding;
    }

    void setColliding( boolean colliding )
    {
        this.colliding = colliding;
    }

}