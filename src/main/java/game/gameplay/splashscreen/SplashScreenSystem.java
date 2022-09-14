package game.gameplay.splashscreen;

import ceragine.Game;
import ecs_architecture.standard.GameSystem;
import game.creators.CopyrightLogoEntity;
import game.states.MenuState;

public class SplashScreenSystem extends GameSystem
{

    private static final int MILLISECONDS = 0;

    @Override
    protected void internalInitialize( )
    {
        add( CopyrightLogoEntity.build( ) );
        Game.runAfter( MILLISECONDS , ( ) -> change( MenuState.class ) );
    }

    @Override
    protected void internalTerminate( ) { }

    @Override
    protected void internalPerform( ) { }

}