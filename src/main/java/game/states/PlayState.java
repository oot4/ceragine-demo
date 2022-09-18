package game.states;

import game.creators.OldHunterEntity;
import game.gameplay.input.InputSystem;
import game.gameplay.player.PlayerSystem;
import render_engine.core.RenderLayer;
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