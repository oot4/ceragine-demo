package states;

import state_machine.api.StateChanger;
import state_machine.standard.GameState;
import systems.MenuTransitionSystem;

public class IntroState extends GameState
{

    protected IntroState( StateChanger stateChanger )
    {
        super( stateChanger );
    }

    @Override
    protected void internalInitialize( )
    {
        add( new MenuTransitionSystem( ) );
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