package game.states;

import game.creators.OldHunterEntity;
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
        add( OldHunterEntity.build( ) );
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