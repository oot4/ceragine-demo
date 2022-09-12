package demo;

import ceragine.Game;
import resource_manager.writable.api.WritableResource;
import resource_manager.writable.core.GameDataBundle;
import states.IntroState;

public final class Main
{

    @WritableResource( filename = "ceraginedemobundle" )
    private static final class CeragineDemoBundle extends GameDataBundle { }

    public static void main( String[] args )
    {
        Game.build( new CeragineDemoBundle( ) );
        Game.setInitialState( IntroState.class );
        Game.launch( );
    }

}