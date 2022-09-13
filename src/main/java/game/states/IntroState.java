package game.states;

import game.systems.SplashScreenSystem;
import state_machine.api.StateChanger;
import state_machine.standard.GameState;

public class IntroState extends GameState
{

    protected IntroState( StateChanger stateChanger ) { super( stateChanger ); }

    @Override
    protected void internalInitialize( ) { add( new SplashScreenSystem( ) ); }

    @Override
    protected void internalTerminate( ) { }

    @Override
    protected void internalPerform( ) { }

}