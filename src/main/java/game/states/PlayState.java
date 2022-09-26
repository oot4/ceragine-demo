package game.states;

import game.gameplay.hitbox.HitBoxSystem;
import game.gameplay.input.InputSystem;
import game.gameplay.map.MapSystem;
import game.gameplay.player.PlayerSystem;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;

public class PlayState extends GameState
{

    protected PlayState( StateChanger stateChanger )
    {
        super( stateChanger );
    }

    @Override
    protected void internalInitialize( )
    {
        add( new MapSystem( ) );
        add( new HitBoxSystem( ) );
        add( new InputSystem( ) );
        add( new PlayerSystem( ) );
    }

    @Override
    protected void internalTerminate( )
    {
    }

    @Override
    protected void internalPerform( )
    {

    }

}