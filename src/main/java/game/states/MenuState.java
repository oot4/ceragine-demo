package game.states;

import game.systems.MenuOptionSystem;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;

public class MenuState extends GameState
{

    protected MenuState( StateChanger stateChanger ) { super( stateChanger ); }

    @Override
    protected void internalInitialize( ) { add( new MenuOptionSystem( ) ); }

    @Override
    protected void internalTerminate( ) { }

    @Override
    protected void internalPerform( ) { }

}