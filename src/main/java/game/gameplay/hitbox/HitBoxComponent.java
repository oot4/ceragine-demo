package game.gameplay.hitbox;

import ecs_architecture.standard.GameComponent;

public class HitBoxComponent extends GameComponent
{

    private final double width;

    private final double height;

    private boolean collidedWithTrigger;

    private boolean collidedWithStatic;

    private HitBoxComponent( double width , double height )
    {
        this.width = width;
        this.height = height;
        collidedWithTrigger = false;
        collidedWithStatic = false;
    }

    public static HitBoxComponent from( int width , int height )
    {
        return new HitBoxComponent( width , height );
    }

    public double getWidth( )
    {
        return width;
    }

    public double getHeight( )
    {
        return height;
    }

    public boolean isCollidedWithTrigger( )
    {
        return collidedWithTrigger;
    }

    void setCollidedWithTrigger( boolean collidedWithTrigger )
    {
        this.collidedWithTrigger = collidedWithTrigger;
    }

    public boolean isCollidedWithStatic( )
    {
        return collidedWithStatic;
    }

    void setCollidedWithStatic( boolean collidedWithStatic )
    {
        this.collidedWithStatic = collidedWithStatic;
    }

}