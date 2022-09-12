package systems;

import ceragine.Game;
import creators.CopyrightLogoEntity;
import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import states.MenuState;
import utility_library.gameplay.effects.FadeComponent;
import utility_library.gameplay.gameclock.TimerComponent;

import java.util.Map;

public final class MenuTransitionSystem extends GameSystem
{

    @Override
    protected void internalInitialize( )
    {
        add( CopyrightLogoEntity.build( ) );
        Game.centerViewportAt( 0 , 0 );
    }

    @Override
    protected void internalTerminate( )
    {

    }

    @Override
    protected void internalPerform( )
    {
        Map< String, GameComponent > copyRightTags = get( CopyrightLogoEntity.CopyRightLogoTag.class );
        Map< String, GameComponent > fadeEffects = get( FadeComponent.class );
        Map< String, GameComponent > timers = get( TimerComponent.class );
        copyRightTags.forEach( ( entity , component ) -> {
            if ( component.getName( ).equals( "copyright-logo" ) )
            {
                if ( ! fadeEffects.containsKey( entity ) )
                {
                    TimerComponent timer = ( TimerComponent ) timers.get( entity );
                    if ( timer != null && timer.getSecondsLeft( ) <= 0 )
                    {
                        change( MenuState.class );
                    }
                }
            }
        } );
    }

}