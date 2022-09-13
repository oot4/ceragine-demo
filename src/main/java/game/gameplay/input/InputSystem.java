package game.gameplay.input;

import ceragine.Game;
import ecs_architecture.standard.GameSystem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputSystem extends GameSystem
{

    private final InputSystemAdapter adapter = new InputSystemAdapter( );

    @Override
    protected void internalInitialize( )
    {
        add( adapter.keys );
        Game.attach( adapter );
    }

    @Override
    protected void internalTerminate( ) { Game.detach( adapter ); }

    @Override
    protected void internalPerform( ) { adapter.keys.update( ); }

    private static class InputSystemAdapter extends KeyAdapter
    {

        private final KeysComponent keys = new KeysComponent( );

        @Override
        public void keyPressed( KeyEvent key )
        {
            keys.setKey( key.getKeyCode( ) , true );
        }

        @Override
        public void keyReleased( KeyEvent key )
        {
            keys.setKey( key.getKeyCode( ) , false );
        }

    }

}