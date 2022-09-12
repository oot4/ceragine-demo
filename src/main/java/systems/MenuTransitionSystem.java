package systems;

import ceragine.Game;
import creators.CopyrightLogoEntity;
import ecs_architecture.standard.GameComponent;
import ecs_architecture.standard.GameSystem;
import states.MenuState;
import utility_library.gameplay.effects.FadeComponent;

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
        copyRightTags.forEach( ( entity , component ) -> {
            if ( component.getName( ).equals( "copyright-logo" ) )
            {
                if ( ! fadeEffects.containsKey( entity ) )
                {
                    change( MenuState.class );
                }
            }
        } );
    }

}