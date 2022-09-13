package game;

import ceragine.Game;
import game.states.GameOverState;
import game.states.IntroState;
import game.states.MenuState;
import game.states.PauseState;
import game.states.PlayState;
import resource_manager.writable.api.WritableResource;
import resource_manager.writable.core.GameDataBundle;

public class Main
{

    @WritableResource( filename = "assets" )
    private static final class AssetData extends GameDataBundle { }

    public static void main( String[] args )
    {
        Game.build( new AssetData( ) );
        Game.setInitialState( IntroState.class );
        Game.attach( MenuState.class );
        Game.attach( PlayState.class );
        Game.attach( PauseState.class );
        Game.attach( GameOverState.class );
        Game.launch( );
    }

}