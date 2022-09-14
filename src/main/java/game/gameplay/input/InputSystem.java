package game.gameplay.input;

import ceragine.Game;
import ecs_architecture.standard.GameSystem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class InputSystem extends GameSystem
{

    public static final String STANDARD_KEYS = "standard-keys";

    private static final KeyComponent KEYS = new KeyComponent( STANDARD_KEYS );

    private final InputSystemAdapter adapter = new InputSystemAdapter( );

    @Override
    protected void internalInitialize( )
    {
        add( KEYS );
        Game.attach( adapter );
    }

    @Override
    protected void internalTerminate( ) { Game.detach( adapter ); }

    @Override
    protected void internalPerform( ) { }

    private static class InputSystemAdapter extends KeyAdapter
    {

        @Override
        public void keyPressed( KeyEvent key )
        {
            KEYS.setKeyStates( key.getKeyCode( ) , true );
            System.out.println( Arrays.toString( KEYS.currentKeyState ) );
            System.out.println( Arrays.toString( KEYS.previousKeyState ) );
        }

        @Override
        public void keyReleased( KeyEvent key )
        {
            KEYS.setKeyStates( key.getKeyCode( ) , false );
            System.out.println( Arrays.toString( KEYS.currentKeyState ) );
            System.out.println( Arrays.toString( KEYS.previousKeyState ) );
        }

    }

}