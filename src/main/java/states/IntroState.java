package states;

import ceragine.Game;
import creators.CopyrightLogoEntity;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;

public class IntroState extends GameState
{

    protected IntroState( StateChanger stateChanger )
    {
        super( stateChanger );
    }

    @Override
    protected void internalInitialize( )
    {
        add( CopyrightLogoEntity.build( ) );
        Game.centerViewportAt( 0   , 0 );
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