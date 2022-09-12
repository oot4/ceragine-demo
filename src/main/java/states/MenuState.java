package states;

import game_debugger.Alert;
import game_debugger.GameDebugger;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;

public class MenuState extends GameState
{

    protected MenuState( StateChanger stateChanger )
    {
        super( stateChanger );
    }

    @Override
    protected void internalInitialize( )
    {
        GameDebugger.log( Alert.INFO , "Menu" );
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