package game.gameplay.input;

import ecs_architecture.standard.GameComponent;

import java.awt.event.KeyEvent;

public class KeyComponent extends GameComponent
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

    final boolean[] currentKeyState = new boolean[ NUM_KEYS ];

    final boolean[] previousKeyState = new boolean[ NUM_KEYS ];

    public KeyComponent( String name ) { super( name ); }

    void setKeyStates( int key , boolean active )
    {
        setKeyState( key , active , currentKeyState );
        setKeyState( key , ! active , previousKeyState );
    }

    private void setKeyState( int key , boolean active , boolean[] keyStates )
    {
        if ( key == KeyEvent.VK_UP ) { keyStates[ UP ] = active; }
        else if ( key == KeyEvent.VK_LEFT ) { keyStates[ LEFT ] = active; }
        else if ( key == KeyEvent.VK_DOWN ) { keyStates[ DOWN ] = active; }
        else if ( key == KeyEvent.VK_RIGHT ) { keyStates[ RIGHT ] = active; }
        else if ( key == KeyEvent.VK_SPACE ) { keyStates[ SPACE ] = active; }
        else if ( key == KeyEvent.VK_ENTER ) { keyStates[ ENTER ] = active; }
        else if ( key == KeyEvent.VK_ESCAPE ) { keyStates[ ESCAPE ] = active; }
        else if ( key == KeyEvent.VK_F1 ) { keyStates[ F1 ] = active; }
    }

    public boolean isPressed( int i )
    {
        return currentKeyState[ i ] && ! previousKeyState[ i ];
    }

    public boolean isDown( int i )
    {
        return currentKeyState[ i ];
    }

    public boolean anyKeyDown( )
    {
        for ( int i = 0; i < NUM_KEYS; i++ )
        {
            if ( currentKeyState[ i ] ) { return true; }
        }
        return false;
    }

    public boolean anyKeyPress( )
    {
        for ( int i = 0; i < NUM_KEYS; i++ )
        {
            if ( currentKeyState[ i ] && ! previousKeyState[ i ] ) { return true; }
        }
        return false;
    }

}