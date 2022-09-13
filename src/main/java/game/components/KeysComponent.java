package game.components;

import ecs_architecture.standard.GameComponent;

import java.awt.event.KeyEvent;

public class KeysComponent extends GameComponent
{


    public static int UP = 0;

    public static int LEFT = 1;

    public static int DOWN = 2;

    public static int RIGHT = 3;

    public static int SPACE = 4;

    public static int ENTER = 5;

    public static int ESCAPE = 6;

    public static int F1 = 7;

    public static final int NUM_KEYS = 8;

    private final boolean[] keyState = new boolean[ NUM_KEYS ];

    private final boolean[] prevKeyState = new boolean[ NUM_KEYS ];

    public void keySet( int i , boolean b )
    {
        if ( i == KeyEvent.VK_UP ) { keyState[ UP ] = b; }
        else if ( i == KeyEvent.VK_LEFT ) { keyState[ LEFT ] = b; }
        else if ( i == KeyEvent.VK_DOWN ) { keyState[ DOWN ] = b; }
        else if ( i == KeyEvent.VK_RIGHT ) { keyState[ RIGHT ] = b; }
        else if ( i == KeyEvent.VK_SPACE ) { keyState[ SPACE ] = b; }
        else if ( i == KeyEvent.VK_ENTER ) { keyState[ ENTER ] = b; }
        else if ( i == KeyEvent.VK_ESCAPE ) { keyState[ ESCAPE ] = b; }
        else if ( i == KeyEvent.VK_F1 ) { keyState[ F1 ] = b; }
    }

    public void update( )
    {
        System.arraycopy( keyState , 0 , prevKeyState , 0 , NUM_KEYS );
    }

    public boolean isPressed( int i )
    {
        return keyState[ i ] && ! prevKeyState[ i ];
    }

    public boolean isDown( int i )
    {
        return keyState[ i ];
    }

    public boolean anyKeyDown( )
    {
        for ( int i = 0; i < NUM_KEYS; i++ )
        {
            if ( keyState[ i ] ) { return true; }
        }
        return false;
    }

    public boolean anyKeyPress( )
    {
        for ( int i = 0; i < NUM_KEYS; i++ )
        {
            if ( keyState[ i ] && ! prevKeyState[ i ] ) { return true; }
        }
        return false;
    }

}